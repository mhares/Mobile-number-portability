package com.telecom.numberportability.controllers;

import com.telecom.numberportability.entity.Request;
import com.telecom.numberportability.entity.Organization;
import com.telecom.numberportability.entity.User;
import com.telecom.numberportability.services.RequestService;
import com.telecom.numberportability.services.OrganizationService;
import com.telecom.numberportability.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.ValidationMessage;
import com.networknt.schema.SpecVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class RequestController {

	@Autowired
	private RequestService requestService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private UserService userService;

	//  Return All Accepted Requests
	@GetMapping("/acceptedRequests")
	public List<Request> getAllAcceptedRequests() {
			return  requestService.getAllAcceptedRequests();
	}

	// Return All Pending Requests that needs an action (Accept & Reject) from Donor organization
	@GetMapping("/pendingRequests")
	public List<Request> getAllPendingRequestsFromOrganization(Principal principal){
		User user = userService.getUserByUserName(principal.getName());
		Organization organization = user.getOrganization();
		return 	requestService.getAllPendingRequestsFromOrganization(organization.getId());
	}

	// Return All pending Requests that my organization Make
	@GetMapping("/sentRequests")
	public List<Request> getAllPendingRequestsSent(Principal principal){
		User user = userService.getUserByUserName(principal.getName());
		Organization organization = user.getOrganization();
		return 	requestService.getAllPendingRequestsToOrganization(organization.getId());
	}

	// Add New Request
	@PostMapping("/requests/{organization}")
	public Request addRequest(@RequestBody String requestStr, @PathVariable("organization") String organization, Principal principal) throws ValidationException,IOException{
		ObjectMapper om = new ObjectMapper();
		Request request= om.readValue(requestStr,Request.class); // Convert String Input To Request Class
		request.setRequestDate(Calendar.getInstance().getTime());  // Assign Request Date

		// Assign this request's Organization Maker
		User user  = userService.getUserByUserName(principal.getName());
		request.setToOrganization(user.getOrganization());

		//Check if Inputs is valid
		if(checkForInvalidInputs(requestStr)){
			request.setStatus("Rejected");
			return requestService.addRequest(request);
		}

		// Check If Current number's Organization is valid and exist
		Optional<Organization> fromOrganization = organizationService.getOrganizationByName(organization);
		if(fromOrganization.isEmpty()){
			request.setStatus("Rejected");
			return requestService.addRequest(request);
		}
		// Assign Request'S current Organization
		request.setFromOrganization(fromOrganization.get());

		// Check if Request Is already exists
		if(requestService.checkIFRequestExists(request.getPhoneNumber()).isPresent()){
			request.setStatus("Rejected");
		}else{
			request.setStatus("Pending");
		}
		return requestService.addRequest(request);
	}

	// Accept Request if Donor
	@PostMapping("/acceptRequest")
	public Request acceptRequest(Principal principal , @RequestBody Request request){
		User user = userService.getUserByUserName(principal.getName());
		// check if he is donor
		if(user.getOrganization().getName().equals(request.getFromOrganization().getName()) ){
			request.setStatus("Accepted");
			requestService.addRequest(request);
		}
		return request;
	}


	@PostMapping("/rejectRequest")
	public Request rejectRequest(Principal principal , @RequestBody Request request ){
		User user = userService.getUserByUserName(principal.getName());
		// check if he is donor
		if(user.getOrganization().getName().equals(request.getFromOrganization().getName()) ){
			request.setStatus("Rejected");
			requestService.addRequest(request);
		}
		return request;
	}


	// User Json Schema To validate Inputs
	private boolean checkForInvalidInputs(String input) throws JsonProcessingException {
		InputStream inputStream =RequestController.class.getClassLoader().getResourceAsStream("model/request.schema.json");
		JsonSchema schema = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4).getSchema(inputStream);
		ObjectMapper om = new ObjectMapper();
		JsonNode jsonNode = om.readTree(input);
		Set<ValidationMessage> errors = schema.validate(jsonNode);
		return errors.size() >= 1;
	}

}

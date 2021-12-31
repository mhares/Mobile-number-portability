package com.telecom.numberportability.services;

import com.telecom.numberportability.entity.Request;
import com.telecom.numberportability.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

	@Autowired
	private RequestRepository requestRepository;

	public List<Request> getAllAcceptedRequests(){
		return requestRepository.findAllAcceptedRequests();
	}

	public List<Request> getAllRequests(){
		return requestRepository.findAll();
	}
    public Request addRequest(Request request){
		return requestRepository.save(request);
    }

    public List<Request> getAllPendingRequests(){
		return requestRepository.findAllPendingRequests();
    }
	public Optional<Request> checkIFRequestExists(String phoneNumber){
		return requestRepository.existsByPhoneNumberAndStatus(phoneNumber);
	}
	public List<Request> getAllPendingRequestsFromOrganization(int id){
		return requestRepository.findAllPendingRequestsFromOrganization(id);
	}
	public List<Request> getAllPendingRequestsToOrganization(int id){
		return requestRepository.findAllPendingRequestsToOrganization(id);
	}

}

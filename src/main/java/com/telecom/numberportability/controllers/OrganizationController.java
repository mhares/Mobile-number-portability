package com.telecom.numberportability.controllers;

import com.telecom.numberportability.entity.Organization;
import com.telecom.numberportability.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
public class OrganizationController {

	@Autowired
	private OrganizationService organizationService;

	@GetMapping("/organizations")
	public List<Organization> organizationList(){
		return organizationService.getAllOrganizations();
	}
}

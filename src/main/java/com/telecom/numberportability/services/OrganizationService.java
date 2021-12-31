package com.telecom.numberportability.services;

import com.telecom.numberportability.entity.Organization;
import com.telecom.numberportability.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {

	@Autowired
	private OrganizationRepository organizationRepository;

	public List<Organization> getAllOrganizations(){
		return  organizationRepository.findAll();
	}
	public Optional<Organization> getOrganizationByName(String organization){
		return organizationRepository.findOrganizationByName(organization);
	}

	public Optional<Organization> getOrganization(int id){
		return organizationRepository.findById(id);
	}


}

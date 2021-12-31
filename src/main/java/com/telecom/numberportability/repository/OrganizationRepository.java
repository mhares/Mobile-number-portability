package com.telecom.numberportability.repository;

import com.telecom.numberportability.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization,Integer> {

	// Fetch Single Organization from its name
	public Optional<Organization> findOrganizationByName(String name);


}

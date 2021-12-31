package com.telecom.numberportability.repository;

import com.telecom.numberportability.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request,Integer> {


	// Fetch All My Accepted Requests
	@Query(value = "SELECT * FROM  request WHERE status = 'Accepted'",nativeQuery = true)
	public List<Request> findAllAcceptedRequests();

	// Fetch All My Pending Requests
	@Query(value = "SELECT * FROM  request WHERE status = 'pending'",nativeQuery = true)
	public List<Request> findAllPendingRequests();

	// Fetch All Pending Requests That An Organization Needs to perform An Action (DONOR)
	@Query(value = "SELECT * FROM  request WHERE status = 'pending' AND from_organiztion_id= ?1",nativeQuery = true)
	public  List<Request> findAllPendingRequestsFromOrganization(int id);

	// Fetch All Pending Requests that an Organization Made ( Recipient )
	@Query(value = "SELECT * FROM  request WHERE status = 'pending' AND to_organiztion_id= ?1",nativeQuery = true)
	public  List<Request> findAllPendingRequestsToOrganization(int id);

	// Check if I have a Pending Request For the same Number
	@Query(value = "SELECT * FROM  request WHERE status = 'pending' AND phone_number= ?1",nativeQuery = true)
	public Optional<Request> existsByPhoneNumberAndStatus(String phoneNumber);



}

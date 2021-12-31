package com.telecom.numberportability.repository;

import com.telecom.numberportability.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

	 // Fetch Single user From his user name And password
	 Optional<User> findByUserNameAndPassword(String userName,String password);

	// Fetch Single user From his user name
	Optional<User> findByUserName(String userName);

}

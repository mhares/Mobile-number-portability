package com.telecom.numberportability.services;

import com.telecom.numberportability.entity.User;
import com.telecom.numberportability.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User getUser(String userName, String password){
		Optional<User> user =  userRepository.findByUserNameAndPassword(userName,password);
		if (user.isPresent()){
			return user.get();
		}
		return null;
	}
	public User getUserByUserName(String userName){
		Optional<User> user =  userRepository.findByUserName(userName);
		if (user.isPresent()){
			return user.get();
		}
		return null;
	}


}

package com.telecom.numberportability.controllers;

import com.telecom.numberportability.entity.User;
import com.telecom.numberportability.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200/")
public class HomeController {

	@Autowired
	UserService userService;

	@PostMapping("/login")
	public User authenticateUser(@RequestBody User user){
		User authenticatedUser = userService.getUser(user.getUserName(),user.getPassword());
		if(authenticatedUser != null)
		  return authenticatedUser;
		else
			throw new RuntimeException();
	}

}

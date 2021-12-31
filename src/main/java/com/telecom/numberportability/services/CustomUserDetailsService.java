package com.telecom.numberportability.services;
import com.telecom.numberportability.configurations.CustomUserDetails;
import com.telecom.numberportability.entity.User;
import com.telecom.numberportability.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser = userRepository.findByUserName(username);
		optionalUser.orElseThrow(() -> new UsernameNotFoundException("Not Found" + username));
		return new CustomUserDetails(optionalUser.get());
	}
}

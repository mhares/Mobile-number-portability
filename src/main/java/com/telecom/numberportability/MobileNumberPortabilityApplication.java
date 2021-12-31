package com.telecom.numberportability;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MobileNumberPortabilityApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileNumberPortabilityApplication.class, args);
	}

}

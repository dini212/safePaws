package com.FinalProject.SafePaws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SafePawsRestApiAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafePawsRestApiAppApplication.class, args);
	}

}

package com.example.airlinereservation.config;


import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@TestConfiguration
@EnableJpaRepositories(basePackages = "com.example.airlinereservation.data.repositories")
public class TestConfigurations {
	

}

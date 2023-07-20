package com.example.airlinereservation.config;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.web.servlet.MockMvc;

@TestConfiguration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.example.airlinereservation.data.repositories")
public class TestConfigurations {
	
	@Bean
	public MockMvc mockMvc() throws InstantiationException, IllegalAccessException {
		return null;
//		return MockMvc.class.newInstance();
	}
}

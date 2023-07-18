package com.example.airlinereservation.config;

import com.example.airlinereservation.utils.mycustomannotations.ValidEmailDomain;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.airlinereservation.utils.mycustomannotations",
				basePackageClasses = {
					com.example.airlinereservation.utils.mycustomannotations.EmailPattern.class,
					com.example.airlinereservation.utils.mycustomannotations.ValidEmailDomain.class
				})
@EnableAutoConfiguration
public class EmailValidationConfig {
	
	@Bean
	public ValidEmailDomain validEmailDomain() {
		return new ValidEmailDomain();
	}
}

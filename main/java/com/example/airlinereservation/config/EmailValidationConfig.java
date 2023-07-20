package com.example.airlinereservation.config;

import com.example.airlinereservation.config.mycustomannotations.EmailPattern;
import com.example.airlinereservation.config.mycustomannotations.ValidEmailDomain;
import com.example.airlinereservation.utils.appUtils.FieldValidator;
import com.example.airlinereservation.utils.appUtils.Validator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.airlinereservation.config.mycustomannotations",
				basePackageClasses = {
					EmailPattern.class,
					ValidEmailDomain.class
				})
@EnableAutoConfiguration
public class EmailValidationConfig {
	
	@Bean
	public ValidEmailDomain validEmailDomain() {
		return new ValidEmailDomain();
	}
	
	@Bean
	public Validator getValidator(){
		return new FieldValidator();
	}
}

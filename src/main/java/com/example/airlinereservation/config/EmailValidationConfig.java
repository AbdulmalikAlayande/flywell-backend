package com.example.airlinereservation.config;

import com.example.airlinereservation.data.model.annotations.EmailPattern;
import com.example.airlinereservation.data.model.annotations.EmailDomainValidator;
import com.example.airlinereservation.services.notifications.FieldValidator;
import com.example.airlinereservation.services.notifications.Validator;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.context.Context;


@Configuration
@ComponentScan(basePackages = "com.example.airlinereservation.data.model.annotations",
				basePackageClasses = {
					EmailPattern.class,
					EmailDomainValidator.class
				})
@EnableAutoConfiguration
@Getter
public class EmailValidationConfig {
	@Value("${totp.secret.key}")
	private String totpSecret;
	
	@Value("${mail.api.key}")
	private String brevoApiKey;
	
	@Bean
	public EmailDomainValidator validEmailDomain() {
		return new EmailDomainValidator();
	}
@Bean
	public Context context() {
		return new Context();
	}
	
	
	
	@Bean
	public Validator getValidator(){
		return new FieldValidator();
	}
	@Bean
	public EmailValidationConfig validationConfig(){
		return new EmailValidationConfig();
	}
	
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	
}

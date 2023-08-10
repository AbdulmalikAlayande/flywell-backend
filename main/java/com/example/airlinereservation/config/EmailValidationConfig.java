package com.example.airlinereservation.config;

import com.example.airlinereservation.data.model.annotations.EmailPattern;
import com.example.airlinereservation.data.model.annotations.EmailDomainValidator;
import com.example.airlinereservation.services.notifications.FieldValidator;
import com.example.airlinereservation.services.notifications.Validator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;


@Configuration
@ComponentScan(basePackages = "com.example.airlinereservation.data.model.annotations",
				basePackageClasses = {
					EmailPattern.class,
					EmailDomainValidator.class
				})
@EnableAutoConfiguration
public class EmailValidationConfig {
	
	public static String PRIVATE_API_KEY = System.getenv("BOLA_AIR_2_BREVO_API_KEY");
	
	@Bean
	public EmailDomainValidator validEmailDomain() {
		return new EmailDomainValidator();
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
	public String brevoApiKey(){
		return PRIVATE_API_KEY;
	}
	
	@Bean
	public TemplateEngine templateEngine(){
		return new TemplateEngine();
	}
	
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	
	@Bean
	public ResourceLoader resourceLoader(){
		return new DefaultResourceLoader();
	}
}

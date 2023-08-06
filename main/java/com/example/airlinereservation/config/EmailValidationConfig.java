package com.example.airlinereservation.config;

import com.example.airlinereservation.config.mycustomannotations.EmailPattern;
import com.example.airlinereservation.config.mycustomannotations.ValidEmailDomain;
import com.example.airlinereservation.utils.appUtils.FieldValidator;
import com.example.airlinereservation.utils.appUtils.Validator;
import com.mailgun.api.v3.MailgunMessagesApi;
import com.mailgun.api.v4.MailgunEmailVerificationApi;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.verification.AddressValidationResponse;
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
	
	public static String PRIVATE_API_KEY = System.getenv("MAIL_GUN_PRIVATE_API_KEY");
	
	@Bean
	public ValidEmailDomain validEmailDomain() {
		return new ValidEmailDomain();
	}
	
	@Bean
	public Validator getValidator(){
		return new FieldValidator();
	}
	
	@Bean
	public MailgunMessagesApi mailgunMessagesApi() {
		return MailgunClient.config(PRIVATE_API_KEY)
				       .createApi(MailgunMessagesApi.class);
	}
}

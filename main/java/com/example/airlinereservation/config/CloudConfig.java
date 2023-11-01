package com.example.airlinereservation.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;


@Getter
@Configuration
@EnableAutoConfiguration
public class CloudConfig {
	
	@Value("${cloud.api.key}")
	private String cloudApiKey;
	@Value("${cloud.api.secret}")
	private String cloudApiSecret;
	@Value("${cloud.api.name}")
	private String cloudApiName;
}

package com.example.airlinereservation.services.notifications.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.*;

@Slf4j
public class SendInBlueContactImportService {
	
	private static final String SENDINBLUE_API_KEY_HEADER = "api-key";
	private static final String SENDINBLUE_CONTACTS_IMPORT_URL = "https://api.brevo.com/v3/contacts/import";
	
	private final RestTemplate restTemplate;
	private final String sendinblueApiKey;
	private final TemplateEngine templateEngine;
		
	public SendInBlueContactImportService(RestTemplateBuilder restTemplateBuilder, String sendinblueApiKey, TemplateEngine templateEngine) {
		this.restTemplate = restTemplateBuilder.build();
		this.sendinblueApiKey = sendinblueApiKey;
		this.templateEngine = templateEngine;
	}
	
	public ResponseEntity<String> importContacts(List<Contact> contacts) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		headers.set(SENDINBLUE_API_KEY_HEADER, sendinblueApiKey);
		
		HttpEntity<List<Contact>> request = new HttpEntity<>(contacts, headers);
		return restTemplate.postForEntity(SENDINBLUE_CONTACTS_IMPORT_URL, request, String.class);
	}
	
	public void sendReservationConfirmationEmail(Contact contact) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		Map<String, Object> requestBody = new HashMap<>();
		
		HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
		
String sendEmailUrl = "https://api.brevo.com/v3/smtp/email";
		ResponseEntity<String> response = restTemplate.postForEntity(sendEmailUrl, requestEntity, String.class);
		
		if (response.getStatusCode().is2xxSuccessful())
			log.info("email sent successfully");
	}	
	
	public static void main(String[] args) {
		String apiKey = "";
		try{
			RestTemplateBuilder builder = new RestTemplateBuilder();
			TemplateEngine templateEngine1 = new TemplateEngine();
			SendInBlueContactImportService importService = new SendInBlueContactImportService(builder, apiKey, templateEngine1);
//			List<Contact> contacts = new ArrayList<>();
//			contacts.add(new Contact("Jide", "farindebabajide@gamil.com"));
//			ResponseEntity<String> response = importService.importContacts(contacts);
//			System.out.println(response.toString());
			
			importService.sendReservationConfirmationEmail(new Contact("Jide", "farindebabajide@gamil.com"));
		} catch (Exception exception){
			System.out.println(exception.getMessage());
			System.out.println(exception.getLocalizedMessage());
		}
	}
}
	

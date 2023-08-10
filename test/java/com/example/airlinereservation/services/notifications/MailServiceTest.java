package com.example.airlinereservation.services.notifications;

import com.example.airlinereservation.dtos.Request.NotificationRequest;
import com.example.airlinereservation.dtos.Response.NotificationResponse;
import com.example.airlinereservation.services.notifications.mail.MailService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class MailServiceTest {
	
	@Autowired
	private MailService mailService;
	private NotificationRequest notificationRequest;
	@BeforeEach void beforeEachTestStartWith(){
		notificationRequest = new NotificationRequest();
		notificationRequest.setEmail("alayandezainab64@gmail.com");
		notificationRequest.setUsername("Zee");
	}
	
	@SneakyThrows
	@Test void sendEmailTest(){
	
		ResponseEntity<NotificationResponse> response = mailService.sendAccountActivationEmail(notificationRequest);
		assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
		assertThat(response.getBody()).isNotNull();
	}
	
	@Test void testThatEmailContainsHtmlContent(){
	
	}
	
	@Test void importContactTest(){
		mailService.importContacts(notificationRequest);
	}
}

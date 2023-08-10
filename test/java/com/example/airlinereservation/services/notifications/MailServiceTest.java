package com.example.airlinereservation.services.notifications;

import com.example.airlinereservation.services.notifications.mail.MailService;
import com.example.airlinereservation.services.notifications.mail.Mailer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MailServiceTest {
	
	@Autowired
	private MailService mailService;
	@Test void sendEmailTest(){
		mailService.sendAccountActivationEmail();
	}
	
	@Test void testThatEmailContainsHtmlContent(){
	
	}
	
	@Test void importContactTest(){
		
	}
}

package com.example.airlinereservation.services.notifications.mail;

import com.example.airlinereservation.data.model.notifications.*;
import com.example.airlinereservation.dtos.Request.NotificationRequest;
import com.example.airlinereservation.dtos.Response.NotificationResponse;
import com.example.airlinereservation.exceptions.InvalidRequestException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;

import java.util.Objects;

import static com.example.airlinereservation.utils.Constants.*;
import static com.example.airlinereservation.utils.TemplateLoader.loadTemplateContent;

@Slf4j
@Service
@AllArgsConstructor
public class Mailer implements MailService{
	
	private final String brevoApiKey;
	private final ResourceLoader resourceLoader;
	private final RestTemplate restTemplate;
	private final TemplateEngine templateEngine;
	private final ModelMapper modelMapper;
	
	@Override
	public ResponseEntity<NotificationResponse> importContacts(NotificationRequest notificationRequest) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		headers.set(API_KEY, brevoApiKey);
		HttpEntity<NotificationRequest> request = new HttpEntity<>(notificationRequest, headers);
		return restTemplate.postForEntity(BREVO_CONTACTS_IMPORT_URL, request, NotificationResponse.class);
	}
	
	@Override
	public ResponseEntity<NotificationResponse> sendReservationConfirmationEmail(NotificationRequest notificationRequest) {
		return null;
	}
	
	@Override
	public ResponseEntity<NotificationResponse> sendAccountActivationEmail(NotificationRequest notificationRequest) throws InvalidRequestException {
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(API_KEY, brevoApiKey);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		Resource foundTemplateResource = resourceLoader.getResource(ACCOUNT_ACTIVATION_EMAIL_TEMPLATE_URL);
		String templateContent = loadTemplateContent(foundTemplateResource);

		Notification notification = new Notification();
		modelMapper.map(notificationRequest, notification);
		notification.setSender(Sender.builder().name(SENDER_FULL_NAME).email(SENDER_EMAIL).build());
		notification.setSubject("Activate Your Account");
		notification.setHtmlContent(templateContent);
		HttpEntity<Notification> requestEntity = new HttpEntity<>(notification, headers);
		ResponseEntity<NotificationResponse> response = restTemplate.postForEntity(
				BREVO_SEND_EMAIL_API_URL,
				requestEntity, NotificationResponse.class
		);                                                               
		if (response.getStatusCode().is2xxSuccessful())
			log.info("{} response body:: {}", MESSAGE_SUCCESSFULLY_SENT, Objects.requireNonNull(response.getBody()));
		else log.error("{} response body:: {}", MESSAGE_FAILED_TO_SEND, Objects.requireNonNull(response.getBody()));
		return response;
	}
	
	@Override
	public ResponseEntity<NotificationResponse> sendFlightFormAsPdf(NotificationRequest notificationRequest) {
		return null;
	}
}

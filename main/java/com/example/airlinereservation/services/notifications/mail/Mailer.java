package com.example.airlinereservation.services.notifications.mail;

import com.example.airlinereservation.data.model.notifications.Email;
import com.example.airlinereservation.data.model.notifications.Notification;
import com.example.airlinereservation.data.model.notifications.Sender;
import com.example.airlinereservation.dtos.Request.NotificationRequest;
import com.example.airlinereservation.dtos.Response.NotificationResponse;
import com.example.airlinereservation.utils.exceptions.InvalidRequestException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.airlinereservation.utils.appUtils.Constants.*;
import static com.example.airlinereservation.utils.appUtils.TemplateLoader.loadTemplateContent;

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
		headers.set("api-key", brevoApiKey);
		
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
		headers.set("api-key", brevoApiKey);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		Resource foundTemplateResource = resourceLoader.getResource(ACCOUNT_ACTIVATION_EMAIL_TEMPLATE_URL);
		String templateContent = loadTemplateContent(foundTemplateResource);
		
		Notification notification = new Email();
		modelMapper.map(notificationRequest, notification);
		notification.setMailSender(Sender.builder().senderEmail("noreply@gmail.com").build());
		List<Notification> notifications = new ArrayList<>();
		notification.setContent(templateContent);
		notifications.add(notification);
		
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("user", notifications);
		requestBody.put("template_id", BREVO_MAIL_TEMPLATE_ID);
		
		HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
		ResponseEntity<NotificationResponse> response = restTemplate.exchange(
				BREVO_SEND_EMAIL_API_URL, HttpMethod.POST,
				requestEntity, NotificationResponse.class
		);
		if (response.getStatusCode().is2xxSuccessful())
			log.info(MESSAGE_SUCCESSFULLY_SENT);
		else log.error(MESSAGE_FAILED_TO_SEND);
		return response;
	}
	
	@Override
	public ResponseEntity<NotificationResponse> sendFlightFormAsPdf(NotificationRequest notificationRequest) {
		return null;
	}
}

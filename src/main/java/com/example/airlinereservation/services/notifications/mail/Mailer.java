package com.example.airlinereservation.services.notifications.mail;

import com.example.airlinereservation.config.EmailValidationConfig;
import com.example.airlinereservation.dtos.Request.NotificationRequest;
import com.example.airlinereservation.dtos.Response.NotificationResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.example.airlinereservation.utils.Constants.*;

@Slf4j
@Service
@AllArgsConstructor
public class Mailer implements MailService{
	
	private final EmailValidationConfig validationConfig;
	private final RestTemplate restTemplate;
	private final SpringTemplateEngine templateEngine;
	private final Context context;
	
	
	@NotNull
	private HttpHeaders getHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set(API_KEY, validationConfig.getBrevoApiKey());
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}
	
	@NotNull
	private static Map<String, Object> getContextVariables(NotificationRequest notificationRequest) {
		Map<String, Object> contextVariables = new HashMap<>();
		contextVariables.put("firstName", notificationRequest.getFirstName());
		contextVariables.put("lastName", notificationRequest.getLastName());
		contextVariables.put("code", notificationRequest.getCode());
		return contextVariables;
	}
	
	private Notification buildNotification(String htmlContent, String userEmail){
		Recipient recipient = Recipient.builder().email(userEmail).build();
		return Notification.builder()
				       .subject(ACCOUNT_ACTIVATION_MAIL_SUBJECT)
				       .sender(Sender.builder().name(SENDER_FULL_NAME).email(SENDER_EMAIL).build())
				       .recipients(Collections.singletonList(recipient))
				       .htmlContent(htmlContent)
				       .build();
	}
	
	@NotNull
	private ResponseEntity<NotificationResponse> sendNotification(NotificationRequest notificationRequest, String email) {
		HttpEntity<Notification> requestEntity = new HttpEntity<>(buildNotification(email, notificationRequest.getEmail()), getHttpHeaders());
		ResponseEntity<NotificationResponse> response = restTemplate.postForEntity(
				BREVO_SEND_EMAIL_API_URL,
				requestEntity,
				NotificationResponse.class
		);
		if (response.getStatusCode().is2xxSuccessful())
			log.info("{} response body:: {}", MESSAGE_SUCCESSFULLY_SENT, Objects.requireNonNull(response.getBody()));
		else log.error("{} response body:: {}", MESSAGE_FAILED_TO_SEND, Objects.requireNonNull(response.getBody()));
		return response;
	}
	
	@Override
	public ResponseEntity<NotificationResponse> importContacts(NotificationRequest notificationRequest) {
		HttpEntity<NotificationRequest> request = new HttpEntity<>(notificationRequest, getHttpHeaders());
		return restTemplate.postForEntity(BREVO_CONTACTS_IMPORT_URL, request, NotificationResponse.class);
	}
	
	@Override
	public ResponseEntity<NotificationResponse> sendAdminInvitationEmail(NotificationRequest notificationRequest) {
		System.out.println(notificationRequest.toString());
		Map<String, Object> contextVariables = getContextVariables(notificationRequest);
		context.setVariables(contextVariables);
		String email = templateEngine.process(notificationRequest.getMailPath(), context);
		return sendNotification(notificationRequest, email);
	}
	
	@Override
	public ResponseEntity<NotificationResponse> sendOtp(NotificationRequest notificationRequest) {
		Map<String, Object> contextVariables = getContextVariables(notificationRequest);
		context.setVariables(contextVariables);
		String email = templateEngine.process(notificationRequest.getMailPath(), context);
		return sendNotification(notificationRequest, email);
	}
	
	@Override
	public ResponseEntity<NotificationResponse> sendFlightFormAsPdf(NotificationRequest notificationRequest) {
		
		return null;
	}
	
	@Override
	public ResponseEntity<NotificationResponse> sendReservationConfirmationEmail(NotificationRequest notificationRequest) {
		return null;
	}
}

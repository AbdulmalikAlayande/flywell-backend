package com.example.airlinereservation.services.notifications.mail;

import com.example.airlinereservation.dtos.Request.NotificationRequest;
import com.example.airlinereservation.dtos.Response.NotificationResponse;
import org.springframework.http.ResponseEntity;

public interface  MailService {
	
	ResponseEntity<NotificationResponse> importContacts(NotificationRequest notificationRequest);
	ResponseEntity<NotificationResponse> sendReservationConfirmationEmail(NotificationRequest notificationRequest);
	ResponseEntity<NotificationResponse> sendAccountActivationEmail(NotificationRequest notificationRequest);
	ResponseEntity<NotificationResponse> sendFlightFormAsPdf(NotificationRequest notificationRequest);
}

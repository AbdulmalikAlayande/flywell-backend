package com.example.airlinereservation.services.notifications.mail;

import com.example.airlinereservation.dtos.Request.NotificationRequest;
import com.example.airlinereservation.dtos.Response.NotificationResponse;
import com.example.airlinereservation.utils.exceptions.InvalidRequestException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface  MailService {
	
	ResponseEntity<NotificationResponse> importContacts(NotificationRequest notificationRequest);
	ResponseEntity<NotificationResponse> sendReservationConfirmationEmail(NotificationRequest notificationRequest);
	ResponseEntity<NotificationResponse> sendAccountActivationEmail(NotificationRequest notificationRequest) throws IOException, InvalidRequestException;
	ResponseEntity<NotificationResponse> sendFlightFormAsPdf(NotificationRequest notificationRequest);
}

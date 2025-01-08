package app.bola.flywell.services.notifications.mail;

import app.bola.flywell.dto.request.NotificationRequest;
import app.bola.flywell.dto.response.NotificationResponse;
import app.bola.flywell.exceptions.InvalidRequestException;
import org.springframework.http.ResponseEntity;

public interface  MailService {
	
	ResponseEntity<NotificationResponse> importContacts(NotificationRequest notificationRequest);
	ResponseEntity<NotificationResponse> sendReservationConfirmationEmail(NotificationRequest notificationRequest);
	ResponseEntity<NotificationResponse> sendAdminInvitationEmail(NotificationRequest notificationRequest) throws InvalidRequestException;
	
	ResponseEntity<NotificationResponse> sendOtp(NotificationRequest notificationRequest) throws InvalidRequestException, InvalidRequestException;
	
	ResponseEntity<NotificationResponse> sendFlightFormAsPdf(NotificationRequest notificationRequest);
}

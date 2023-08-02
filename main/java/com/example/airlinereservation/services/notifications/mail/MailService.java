package com.example.airlinereservation.services.notifications.mail;

public interface MailService {
	
	void verifyEmail(String email);
	void verifyPassword(String password);
}

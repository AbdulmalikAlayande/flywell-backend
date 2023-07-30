package com.example.airlinereservation.services.messages.mail;

public interface MailService {
	
	void verifyEmail(String email);
	void verifyPassword(String password);
}

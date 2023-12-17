package com.example.airlinereservation.services.userservice;

public interface OTPService {
	
	
	String encodeBase32(String input);
	
	String decodeBase32(String base32);
	
	String generateTOTP(String secretKey);
	
	boolean validateTOTP(String secretKey, String inputTOTP);
}

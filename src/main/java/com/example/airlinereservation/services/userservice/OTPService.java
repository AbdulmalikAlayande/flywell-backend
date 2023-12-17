package com.example.airlinereservation.services.userservice;

import com.example.airlinereservation.data.model.persons.OTP;

public interface OTPService {
	
	OTP saveOTP(OTP otp);
	
	OTP encodeBase32(String input);
	
	String decodeBase32(String base32);
	
	OTP generateTOTP(OTP otp);
	
	boolean validateTOTP(String secretKey, String inputTOTP);
	
	String splitSecretKey(String decodedOtp);
}

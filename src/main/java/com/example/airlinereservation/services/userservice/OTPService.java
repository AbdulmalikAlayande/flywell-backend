package com.example.airlinereservation.services.userservice;

import com.example.airlinereservation.data.model.persons.OTP;

public interface OTPService {
	
	OTP saveOTP(OTP otp);
	
	OTP generateTOTP(String input);
	
	boolean validateTOTP(String secretKey, String inputTOTP);
}

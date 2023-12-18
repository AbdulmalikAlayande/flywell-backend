package com.example.airlinereservation.services.userservice;

import com.example.airlinereservation.data.model.persons.OTP;
import com.example.airlinereservation.exceptions.InvalidRequestException;

public interface OTPService {
	
	OTP saveOTP(OTP otp);
	
	OTP generateTOTP(String input);
	
	boolean validateTOTP(String inputTotp) throws InvalidRequestException;
}

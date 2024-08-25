package app.bola.flywell.services.userservice;

import app.bola.flywell.data.model.persons.OTP;
import app.bola.flywell.exceptions.InvalidRequestException;

public interface OTPService {
	
	OTP saveOTP(OTP otp);
	
	OTP generateTOTP(String input);
	
	OTP validatedTOTP(String inputTotp) throws InvalidRequestException;
	
	OTP verifiedOtp(String totp) throws InvalidRequestException;
}

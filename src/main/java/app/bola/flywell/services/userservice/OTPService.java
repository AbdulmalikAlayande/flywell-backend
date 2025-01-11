package app.bola.flywell.services.userservice;

import app.bola.flywell.data.model.persons.OTP;
import app.bola.flywell.exceptions.InvalidRequestException;

public interface OTPService {

	OTP createNew(String email);

	OTP verifyOtp(String totp) throws InvalidRequestException;
}

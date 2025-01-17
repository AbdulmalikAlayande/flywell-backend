package app.bola.flywell.services.users;

import app.bola.flywell.data.model.users.OTP;
import app.bola.flywell.exceptions.InvalidRequestException;

public interface OTPService {

	OTP createNew(String email);

	OTP verifyOtp(String totp) throws InvalidRequestException;
}

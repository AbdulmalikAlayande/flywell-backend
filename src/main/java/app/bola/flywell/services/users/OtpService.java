package app.bola.flywell.services.users;

import app.bola.flywell.data.model.users.Otp;
import app.bola.flywell.exceptions.InvalidRequestException;

public interface OtpService {

	Otp createNew(String email);

	Otp verifyOtp(String totp) throws InvalidRequestException;
}

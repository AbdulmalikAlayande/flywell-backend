package app.bola.flywell.services.users;

import app.bola.flywell.config.EmailConfig;
import app.bola.flywell.data.model.users.Otp;
import app.bola.flywell.data.repositories.OTPRepository;
import app.bola.flywell.exceptions.InvalidRequestException;
import com.eatthepath.otp.TimeBasedOneTimePasswordGenerator;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.time.Duration;
import java.time.Instant;

@Service
@AllArgsConstructor
public class FlyWellOtpService implements OtpService {
	
	final EmailConfig emailConfig;
	final OTPRepository otpRepository;
	private final Logger logger = LoggerFactory.getLogger(FlyWellOtpService.class);

	private static final int TOTP_LENGTH = 6;
	private static final Duration OTP_VALIDITY_DURATION = Duration.ofSeconds(60);
	private static final String HMAC_ALGORITHM = "HmacSHA256";
	
	
	@Override
	public Otp createNew(String email) {

		String value = generateTOTP(email);
		String secretKey = email+ emailConfig.getTotpSecret();

		Otp otp = Otp.builder()
					.staleTime(Instant.now().plus(OTP_VALIDITY_DURATION).toEpochMilli())
					.secretKey(secretKey)
					.userEmail(email)
					.data(Long.parseLong(value))
					.build();

		return otpRepository.save(otp);
	}

	private String generateTOTP(String email) {
		try{
			final TimeBasedOneTimePasswordGenerator totp =
					new TimeBasedOneTimePasswordGenerator(OTP_VALIDITY_DURATION, TOTP_LENGTH);

			byte[] secretKeyBytes = (email + emailConfig.getTotpSecret()).getBytes();
			SecretKey secretKey = new SecretKeySpec(secretKeyBytes, HMAC_ALGORITHM);

			int otp = totp.generateOneTimePassword(secretKey, Instant.now());
			String stringOtp = String.format("%0" + TOTP_LENGTH + "d", otp);
			logger.info("Generated OTP: {}", stringOtp);
			return stringOtp;

		}catch (InvalidKeyException exception) {
			logger.error(exception.getMessage());
			logger.error("OTP Generation Failed: Invalid OTP Key", exception);
            throw new RuntimeException(exception);
        }
	}

	@Override
	public Otp verifyOtp(String totp) throws InvalidRequestException {

		long inputOtp;
		try {
			inputOtp = Long.parseLong(totp);
		} catch (NumberFormatException e) {
			throw new InvalidRequestException("OTP must be numeric.");
		}

		Otp otp = otpRepository.findByData(inputOtp)
							   .orElseThrow(() -> new InvalidRequestException("Invalid OTP"));

		if (System.currentTimeMillis() > otp.getStaleTime()) {
			throw new InvalidRequestException("OTP has expired, please request a new one.");
		}

		otp.setUsed(true);
		return otpRepository.save(otp);
	}

}
	
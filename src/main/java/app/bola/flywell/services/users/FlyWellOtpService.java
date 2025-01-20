package app.bola.flywell.services.users;

import app.bola.flywell.config.EmailValidationConfig;
import app.bola.flywell.data.model.users.Otp;
import app.bola.flywell.data.repositories.OTPRepository;
import app.bola.flywell.exceptions.InvalidRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

import static java.lang.Integer.parseInt;
import static java.math.BigInteger.valueOf;

@Service
@AllArgsConstructor
public class FlyWellOtpService implements OtpService {
	
	private EmailValidationConfig emailValidationConfig;
	private OTPRepository otpRepository;
	private static final int TOTP_LENGTH = valueOf(6).intValue();
	private static final int TIME_STEP = 180000;
	
	
	@Override
	public Otp createNew(String email) {

		String value = generateTOTP(email);
		String secretKey = email+ emailValidationConfig.getTotpSecret();

		Otp.OtpBuilder<?, ?> otpBuilder = Otp.builder();
		otpBuilder.staleTime(System.currentTimeMillis()+TIME_STEP);
		otpBuilder.secretKey(secretKey);
		otpBuilder.userEmail(email);
		otpBuilder.data(Long.parseLong(value));
		Otp otp = otpBuilder.build();

		return otpRepository.save(otp);
	}
	
	private String generateTOTP(String input) {
		String secretKey = input+ emailValidationConfig.getTotpSecret();
		String emailHashcode = String.valueOf(secretKey.hashCode());
		int halfLength = emailHashcode.length() / 2;
		StringBuilder value = new StringBuilder();
		StringBuilder value1 = new StringBuilder();
		for (int index = 0; index < emailHashcode.length(); index++) {
			if (index > halfLength)
				value1.append(emailHashcode.charAt(index));
			else value.append(emailHashcode.charAt(index));
		}
		int addition = parseInt(String.valueOf(value)) + parseInt(String.valueOf(value1));
		String stringValueOfAddition;
		if (addition > 0) stringValueOfAddition = String.valueOf(addition);
		else stringValueOfAddition = String.valueOf(-1*addition);
		String stringOfLengthSix;
		if (stringValueOfAddition.length() != 6)
			stringOfLengthSix=getStringOfLengthSix(stringValueOfAddition);
		else stringOfLengthSix = stringValueOfAddition;
		return stringOfLengthSix;
	}

	public String getStringOfLengthSix(String value){
		int remainingLength = value.length() - TOTP_LENGTH;
		System.out.println(remainingLength);
		String valueOfLengthSix;
		if (remainingLength > 0)
			valueOfLengthSix = removeValues(value);
		else valueOfLengthSix = addValues(value, -1*remainingLength);
		return valueOfLengthSix;
	}
	
	private String addValues(String value, int remainingLength) {
		SecureRandom random = new SecureRandom();
		StringBuilder valueBuilder = new StringBuilder(value);
		for (int index = 0; index < remainingLength; index++) {
			valueBuilder.append(random.nextInt(10));
		}
		return valueBuilder.toString();
	}
	
	private String removeValues(String value) {
		StringBuilder valueOfLengthSix = new StringBuilder();
		for (int index = 0; index < value.length(); index++) {
			if (index > TOTP_LENGTH)
				break;
			else valueOfLengthSix.append(value.charAt(index));
		}
		return valueOfLengthSix.toString();
	}
	
	private Otp validatedTOTP(String inputTotp) throws InvalidRequestException {
		Optional<Otp> foundOTPRef = otpRepository.findByData(Long.parseLong(inputTotp));
		return foundOTPRef.map(otp -> {
			long currentTimeInMilliSeconds = System.currentTimeMillis();
			// FIXME: 12/18/2023 THE EXPIRY SHOULD NOT BE SET HERE NORMALLY,
			//  IT SHOULD BE SOMETHING THAT EXPIRES ITSELF ONCE THE TIME IS EQUAL TO THE SYSTEM CURRENT TIME
			//  IN MILLISECONDS
			if (currentTimeInMilliSeconds > otp.getStaleTime()){
				otp.setExpired(true);
				otpRepository.save(otp);
				throw new RuntimeException("OTP Has Expired");
			}
			if (otp.isUsed()) throw new RuntimeException("OTP Has Been Used");
			else {
				otp.setUsed(true);
				return otpRepository.save(otp);
			}
		}).orElseThrow(()->new InvalidRequestException("Invalid Otp"));
	}
	
	@Override
	public Otp verifyOtp(String totp) throws InvalidRequestException {
		return validatedTOTP(totp);
	}
}
	
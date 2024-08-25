package app.bola.flywell.services.userservice;

import app.bola.flywell.config.EmailValidationConfig;
import app.bola.flywell.data.model.persons.OTP;
import app.bola.flywell.data.repositories.OTPRepository;
import app.bola.flywell.exceptions.InvalidRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

import static java.math.BigInteger.valueOf;
import static org.codehaus.plexus.util.TypeFormat.parseInt;

@Service
@AllArgsConstructor
public class BolaAIr_OTPService implements OTPService {
	
	private EmailValidationConfig validationConfig;
	private OTPRepository otpRepository;
	private static final int TOTP_LENGTH = valueOf(6).intValue();
	private static final int TIME_STEP = 180000;
	
	
	@Override
	public OTP saveOTP(OTP otp) {
		return otpRepository.save(otp);
	}
	
	@Override
	public OTP generateTOTP(String input) {
		String secretKey = input+validationConfig.getTotpSecret();
		String emailHashcode = String.valueOf(secretKey.hashCode());
		int halfLength = emailHashcode.length() / 2;
		StringBuilder value = new StringBuilder();
		StringBuilder value1 = new StringBuilder();
		for (int index = 0; index < emailHashcode.length(); index++) {
			if (index > halfLength)
				value1.append(emailHashcode.charAt(index));
			else value.append(emailHashcode.charAt(index));
		}
		int addition = parseInt(value) + parseInt(value1);
		String stringValueOfAddition;
		if (addition > 0) stringValueOfAddition = String.valueOf(addition);
		else stringValueOfAddition = String.valueOf(-1*addition);
		String stingOfLengthSix;
		if (stringValueOfAddition.length() != 6)
			stingOfLengthSix=getStringOfLengthSix(stringValueOfAddition);
		else stingOfLengthSix = stringValueOfAddition;
		return saveOTP(OTP.builder()
			               .staleTime(System.currentTimeMillis()+TIME_STEP)
			               .secretKey(secretKey)
				           .userEmail(input)
			               .data(Long.parseLong(stingOfLengthSix))
			               .build());
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
	
	@Override
	public OTP validatedTOTP(String inputTotp) throws InvalidRequestException {
		Optional<OTP> foundOTPRef = otpRepository.findByData(Long.parseLong(inputTotp));
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
	public OTP verifiedOtp(String totp) throws InvalidRequestException {
		return validatedTOTP(totp);
	}
}
	
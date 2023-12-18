package com.example.airlinereservation.services.userservice;

import com.example.airlinereservation.config.EmailValidationConfig;
import com.example.airlinereservation.data.model.persons.OTP;
import com.example.airlinereservation.data.repositories.OTPRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

import static java.math.BigInteger.valueOf;
import static org.codehaus.plexus.util.TypeFormat.parseInt;

@Service
@AllArgsConstructor
@NoArgsConstructor
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
	public boolean validateTOTP(String secretKey, String inputTOTP) {
		return false;
	}
	
	public static void main(String[] args) {
		BolaAIr_OTPService service = new BolaAIr_OTPService();
		System.out.println(service.generateTOTP("alaabdumalik03@gmailahgshuirihtuwhg3h;wqoehnfibhou5g3hort.com344323848y34y91785y9"));
	}
}
	
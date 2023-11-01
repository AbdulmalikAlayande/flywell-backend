package com.example.airlinereservation.services.notifications;

import com.example.airlinereservation.exceptions.FieldInvalidException;
import com.example.airlinereservation.exceptions.InvalidRequestException;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
import java.util.regex.Pattern;

import static com.example.airlinereservation.exceptions.Exceptions.throwFieldInvalidException;
import static com.example.airlinereservation.exceptions.Exceptions.throwInvalidRequestException;
import static com.example.airlinereservation.utils.Constants.*;

@Slf4j
public class FieldValidator implements Validator {
	
	private final String[] validDomains = {"gmail.com", "outlook.com", "yahoo.com", "native.semicolon.africa"};
	private final Character[] validSpecialCharacters = {'@', '!', '#', '$', '%', '(', ')', '.', '^'};
	private String email;
	
	@Override
	public void validateEmail(String email) throws InvalidRequestException, FieldInvalidException {
		this.email = email;
		List<String> domains = Arrays.stream(validDomains).toList();
		String regexPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		Pattern compiledPattern = Pattern.compile(regexPattern);
		if (!compiledPattern.matcher(email).matches())
			throwInvalidRequestException(INVALID_EMAIL_FORMAT);
		String part = splitEmailToObtainDomain();
		boolean emailMatchesADomain = domains.stream().anyMatch(domain -> domain.equals(part));
		if (!emailMatchesADomain) {
			String fomattedMessage = new Formatter().format(INVALID_DOMAIN, part, email).toString();
			throwFieldInvalidException(fomattedMessage);
			log.info("The domain part {} of {} is invalid", part, this.email);
		}
		log.info("email {} validated successfully", this.email);
	}
	
	private String splitEmailToObtainDomain() {
		return email.split("@")[1];
	}
	
	@Override
	public void validatePassword(String password) throws FieldInvalidException {
		Formatter formatter = new Formatter();
		Formatter formatMessage = formatter.format(INVALID_PASSWORD_LENGTH, password);
		String invalidLengthMessage = formatMessage.toString();
		if (!fieldLengthIsValid(password, 8, 15))
			throwFieldInvalidException(invalidLengthMessage);
		else {
			boolean passWordContainsSpecialCharacter = false;
			for (Character validSpecialCharacter : validSpecialCharacters) {
				for (int indexJ = 0; indexJ < password.length(); indexJ++) {
					if (validSpecialCharacter == password.charAt(indexJ)) {
						passWordContainsSpecialCharacter = true;
						break;
						
					}
				}
			}
			if (!passWordContainsSpecialCharacter) {
				Formatter formatter1 = new Formatter();
				Formatter message = formatter1.format(PASSWORD_DOES_NOT_CONTAIN_EXPECTED_CHARACTER, Arrays.toString(validSpecialCharacters));
				throwFieldInvalidException(message.toString());
			}
		}
	}
	
	 @Override
	public void validatePhoneNumber(String phoneNumber) {
	
	}
	
	@Override
	public boolean fieldLengthIsValid(String field, int minimumLength, int maximumLength) {
		return field.length() >= minimumLength || field.length() <= maximumLength;
	}
	
	@Override
	public boolean fieldLengthIsValid(String field, int minimumLength) {
		return field.length() > minimumLength;
	}
	
}

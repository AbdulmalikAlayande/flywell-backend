package com.example.airlinereservation.utils.appUtils;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
import java.util.regex.Pattern;

import static com.example.airlinereservation.utils.Exceptions.throwFieldInvalidException;
import static com.example.airlinereservation.utils.Exceptions.throwInvalidRequestException;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class FieldValidator implements Validator{
	private final String[] validDomains = {"gmail.com", "outlook.com", "yahoo.com", "native.semicolon.africa"};
	private final Character[] validSpecialCharacters = {'@', '!', '#', '$', '%', '(', ')', '.', '^'};
	private String email;
	
	@Override
	public void validateEmail(String email) {
		this.email = email;
		List<String> domains = Arrays.stream(validDomains).toList();
		String regexPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		Pattern compiledPattern = Pattern.compile(regexPattern);
		if (!compiledPattern.matcher(email).matches())
			throwInvalidRequestException("Email pattern is not valid:: Please enter a valid email format");
		String part = splitEmailToObtainDomain();
		boolean emailMatchesADomain = domains.stream().anyMatch(domain -> domain.equals(part));
		if (!emailMatchesADomain) {
			String fomattedMessage = new Formatter().format("The domain part %s of %s is invalid", part, email).toString();
			throwFieldInvalidException(fomattedMessage);
			log.info("The domain part {} of {} is invalid", part, this.email);
		}
		log.info("email {} validated successfully", this.email);
	}
	
	private String splitEmailToObtainDomain() {
		return email.split("@")[1];
	}
	
	@Override
	public void validatePassword(String password) {
		Formatter formatter = new Formatter();
		Formatter formatMessage = formatter.format("Password %s lenght is invalid it should be between 8 and 15 characters", password);
		String invalidLenghtMessage = formatMessage.toString();
		if (!fieldLengthIsValid(password, 8, 15))
			throwFieldInvalidException(invalidLenghtMessage);
		boolean passWordContainsSpecialCharacter = false;
		for (int index = 0; index < validSpecialCharacters.length; index++) {
			if (validSpecialCharacters[index] == password.charAt(index)) {
				passWordContainsSpecialCharacter = true;
				break;
			}
		}
		if (!passWordContainsSpecialCharacter){
			Formatter message = formatter.format("Password should contain either of the special characters %s", (Object) validSpecialCharacters);
			throwFieldInvalidException(message.toString());
		}
	}
	
	 @Override
	public void validatePhoneNumber(String phoneNumber) {
	
	}
	
	@Override
	public boolean fieldLengthIsValid(String field, int minimumLength, int maximumLength) {
		return field.length() >= minimumLength && field.length() <= maximumLength;
	}
	
	@Override
	public boolean fieldLengthIsValid(String field, int minimumLength) {
		return field.length() > minimumLength;
	}
	
}

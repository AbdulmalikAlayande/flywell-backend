package com.example.airlinereservation.services.notifications.mail.verification;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class MailServiceImplementation implements MailService{
	@Override
	public void verifyEmail(String email) {
		Pattern regexFormat = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
		List<String> allowedDomains = List.of(new String[]{"gmail.com", "yahoo.com", "outlook.com"});
		if (!regexFormat.matcher(email).matches()) throw new IllegalArgumentException("This is not a valid email address");
		String[] emailSplit = email.split("@");
		String domains = emailSplit[1].toLowerCase();
		boolean domainIsValid = false;
		
		for (String allowedDomain: allowedDomains) {
			if (domains.contains(allowedDomain)) {
				domainIsValid = true;
				break;
			}
		}
		if (!domainIsValid) throw new IllegalArgumentException("This is not a valid email address");
	}
	
	@Override
	public void verifyPassword(String password) {
		boolean isValidPassword = false;
		String regexPattern = "@,%,&,^,$,#,!,(,)()";
		String[] specialCharacterSplit = regexPattern.split(",",8);
		for (String character : specialCharacterSplit) {
			if (password.contains(character)) {
				isValidPassword = true;
				break;
			}
		}
		if (!isValidPassword)
			throw new IllegalArgumentException("""
				Invalid password format\040
				password must contain special characters\040
				like: @, %, &, ^, $, #, !, (, ), ()
				""");
		
	}
}

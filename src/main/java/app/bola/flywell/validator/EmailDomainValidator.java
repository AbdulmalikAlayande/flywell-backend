package app.bola.flywell.validator;

import app.bola.flywell.annotations.EmailPattern;
import app.bola.flywell.exceptions.Exceptions;
import app.bola.flywell.exceptions.InvalidRequestException;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;

public class EmailDomainValidator implements ConstraintValidator<EmailPattern, String> {
	String invalidDomainErrorMessage;
	String[] validDomain;
	String regexPattern;

	@Override
	public void initialize(EmailPattern constraintAnnotation) {
		invalidDomainErrorMessage = constraintAnnotation.message();
		validDomain = constraintAnnotation.validDomain();
		regexPattern = constraintAnnotation.pattern();
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		Pattern compiledPattern = Pattern.compile(regexPattern);
		if (!compiledPattern.matcher(email).matches()) {
			context.buildConstraintViolationWithTemplate("Please enter a valid email format").addConstraintViolation();
			return false;
		}
		System.out.println("I have been feeling low");
		ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder = context
				.buildConstraintViolationWithTemplate("Invalid Domain");
		context.disableDefaultConstraintViolation();
		System.out.println("i don't wanna be low");
		String[] emailSplit = email.split("@");
		if (Arrays.stream(validDomain).noneMatch(domain -> Objects.equals(domain, emailSplit[1]))) {
			try {
				Exceptions.throwInvalidRequestException(constraintViolationBuilder.toString());
			} catch (InvalidRequestException e) {
				throw new RuntimeException(e);
			}
		}
		return false;
	}
}

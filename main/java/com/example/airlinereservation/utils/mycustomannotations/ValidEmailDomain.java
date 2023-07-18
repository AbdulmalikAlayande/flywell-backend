package com.example.airlinereservation.utils.mycustomannotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;

import static com.example.airlinereservation.utils.Exceptions.throwInvalidRequestException;

public class ValidEmailDomain implements ConstraintValidator<EmailPattern, String> {
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
		if (!compiledPattern.matcher(email).matches()){
			context.buildConstraintViolationWithTemplate("Please enter a valid email format").addConstraintViolation();
			return false;
		}
		ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder = context.buildConstraintViolationWithTemplate("Invalid Domain");
		context.disableDefaultConstraintViolation();
		String[] emailSplit = email.split("@");
		return Arrays.stream(validDomain)
				     .anyMatch(domain-> {
					     if (Objects.equals(domain, emailSplit[1]))
							 return true;
						 else {
						     throwInvalidRequestException(constraintViolationBuilder.toString());
						     return false;
					     }
				     });
	}
}
//Stream<Boolean> result = Arrays.stream(validDomain).map(x -> {
//			if (Objects.equals(x, emailSplit[1]))
//				return true;
//			throw new RuntimeException(constraintViolationBuilder.toString());
//		});
//		return result.findAny().isPresent();
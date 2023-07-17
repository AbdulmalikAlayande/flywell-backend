package com.example.airlinereservation.utils.mycustomannotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class ValidEmailDomain implements ConstraintValidator<EmailPattern, String> {
	String invalidDomainErrorMessage;
	String[] validDomain;
	
	@Override
	public void initialize(EmailPattern constraintAnnotation) {
		invalidDomainErrorMessage = constraintAnnotation.message();
		validDomain = constraintAnnotation.validDomain();
	}
	
	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder = context.buildConstraintViolationWithTemplate("Invalid Domain");
		context.disableDefaultConstraintViolation();
		String[] emailSplit = email.split("@");
		Stream<Boolean> result = Arrays.stream(validDomain).map(x -> {
			if (Objects.equals(x, emailSplit[1]))
				return true;
			throw new RuntimeException(constraintViolationBuilder.toString());
		});
		return result.findAny().isPresent();
	}
}

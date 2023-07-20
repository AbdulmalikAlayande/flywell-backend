package com.example.airlinereservation.config.mycustomannotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.*;

@Constraint(validatedBy = ValidEmailDomain.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
@Documented
public @interface EmailPattern {

	String[] validDomain() default  {"gmail.com, yahoo.com, native.semicolon.africa, outlook.com"};
	String message() default "Please enter a valid email format";
	Class<?>[] groups() default { };
	String pattern() default "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	
	Class<? extends Payload>[] payload() default { };
	
	
}

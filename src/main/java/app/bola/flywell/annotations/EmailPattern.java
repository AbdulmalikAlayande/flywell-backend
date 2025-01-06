package app.bola.flywell.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;
import java.lang.annotation.*;

import app.bola.flywell.validator.EmailDomainValidator;

@Constraint(validatedBy = EmailDomainValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
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

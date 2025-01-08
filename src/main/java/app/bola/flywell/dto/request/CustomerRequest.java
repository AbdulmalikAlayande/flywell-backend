package app.bola.flywell.dto.request;

import app.bola.flywell.validator.EmailDomainValidator;
import app.bola.flywell.annotations.EmailPattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * @author Alayande Abdulmalik
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class CustomerRequest {

	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@Email(message = "Please enter a valid email format", regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", groups = {
			EmailDomainValidator.class })
	@EmailPattern
	@NotBlank
	private String email;
	@NotBlank
	private String phoneNumber;
	@NotBlank
	@Size(max = 15, min = 8, message = "Invalid Password Length: Password length must be between 8 and 15 characters")
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*\\\\d)(?=.*[@$!%*#?&])[A-Za-z\\\\d@$!%*#?&]{8,}$", message = "Password Must Contain At Least One Uppercase Character, A Number and One Special Character")
	private String password;
}
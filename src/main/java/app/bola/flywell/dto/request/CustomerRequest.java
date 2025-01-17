package app.bola.flywell.dto.request;

import app.bola.flywell.data.model.persons.Customer;

import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Request DTO for {@link Customer} model
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

	@Email(message = "Please enter a valid email format", regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
	@NotBlank
	private String email;

	@NotBlank
	private String phoneNumber;

	@NotBlank
	@Size(max = 15, min = 8, message = "Invalid Password Length: Password length must be between 8 and 15 characters")
	private String password;

}
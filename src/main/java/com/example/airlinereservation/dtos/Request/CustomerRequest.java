package com.example.airlinereservation.dtos.Request;

import com.example.airlinereservation.data.model.annotations.EmailDomainValidator;
import com.example.airlinereservation.data.model.annotations.EmailPattern;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @@author Alayande Abdulmalik
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {
	
	@NotBlank
	@Valid
	private String firstName;
	@NotBlank
	@Valid
	private String lastName;
	@Email(message = "Please enter a valid email format",
			regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
			groups = {EmailDomainValidator.class})
	@EmailPattern
	@NotBlank
	@Valid
	private String email;
	@NotBlank
	@Valid
	private String phoneNumber;
	@NotBlank
	@Valid
	@Size(max = 15, min = 8, message = "Invalid Password Length: Password length must be between 8 and 15 characters")
	private String password;
}
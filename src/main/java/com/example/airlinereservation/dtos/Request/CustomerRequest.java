package com.example.airlinereservation.dtos.Request;

import com.example.airlinereservation.data.model.annotations.EmailDomainValidator;
import com.example.airlinereservation.data.model.annotations.EmailPattern;
import lombok.*;

import javax.validation.constraints.Email;

/**
 * @@author Alayande Abdulmalik
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {
	
	@NonNull
	private String firstName;
	@NonNull
	private String lastName;
	@Email(message = "Please enter a valid email format",
			regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
			groups = {EmailDomainValidator.class})
	@EmailPattern
	@NonNull
	private String email;
	@NonNull
	private String phoneNumber;
	@NonNull
	private String password;
}

package com.example.airlinereservation.dtos.Request;

import com.example.airlinereservation.data.model.annotations.EmailPattern;
import com.example.airlinereservation.data.model.annotations.EmailDomainValidator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@Email(message = "Please enter a valid email format",
			regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
			groups = {EmailDomainValidator.class})
	@EmailPattern
	private String email;
	@NotBlank
	private String phoneNumber;
	@NotBlank
	private String password;
	@NotBlank
	private String userName;
	
	public String getFullName(){
		return firstName + " " + lastName;
	}
}

package com.example.airlinereservation.dtos.Request;

import com.example.airlinereservation.config.mycustomannotations.EmailPattern;
import com.example.airlinereservation.config.mycustomannotations.ValidEmailDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerRequest {
	private String firstName;
	private String lastName;
	@Email(message = "Please enter a valid email format",
			regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
			groups = {ValidEmailDomain.class})
	@EmailPattern
	private String email;
	private String phoneNumber;
	private String password;
	private String userName;
	
	public String getFullName(){
		return firstName + " " + lastName;
	}
}

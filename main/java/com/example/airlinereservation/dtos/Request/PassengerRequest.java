package com.example.airlinereservation.dtos.Request;

import com.example.airlinereservation.utils.mycustomannotations.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerRequest {
	private String firstName;
	private String lastName;
	@EmailPattern
	private String email;
	private String phoneNumber;
	private String password;
	private String userName;
	
	public String getFullName(){
		return firstName + " " + lastName;
	}
}

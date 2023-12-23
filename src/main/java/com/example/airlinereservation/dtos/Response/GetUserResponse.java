package com.example.airlinereservation.dtos.Response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetUserResponse {
	
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String phoneNumber;
}

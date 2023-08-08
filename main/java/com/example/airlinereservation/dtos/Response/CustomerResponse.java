package com.example.airlinereservation.dtos.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
	private String id;
	private String message;
	private String fullName;
	private String Email;
	private String phoneNumber;
	private String userName;
	private String firstName;
	private String lastName;
}

package com.example.airlinereservation.dtos.Request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {
	
	private String firstName;
	private long OTP;
	private String lastName;
	private String email;
	private String mailPath;
	private String mailSubject;
}

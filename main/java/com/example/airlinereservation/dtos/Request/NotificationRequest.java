package com.example.airlinereservation.dtos.Request;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {
	
	private String email;
	private String phoneNumber;
	private String username;
}

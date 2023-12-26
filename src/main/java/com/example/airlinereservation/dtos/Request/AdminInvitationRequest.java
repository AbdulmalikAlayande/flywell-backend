package com.example.airlinereservation.dtos.Request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminInvitationRequest {
	
	private String email;
	private String phoneNumber;
	private String notificationPreference;
}

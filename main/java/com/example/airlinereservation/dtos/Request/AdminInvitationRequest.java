package com.example.airlinereservation.dtos.Request;


import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminInvitationRequest {
	
	public String adminEmail;
	public String adminPhoneNumber;
}

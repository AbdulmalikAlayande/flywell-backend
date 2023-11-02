package com.example.airlinereservation.dtos.Request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {
	
	@Nullable
	private String username;
	@NotBlank
	private String password;
	@Nullable
	private String email;
}

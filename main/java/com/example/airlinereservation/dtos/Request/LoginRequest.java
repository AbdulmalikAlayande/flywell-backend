package com.example.airlinereservation.dtos.Request;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {
	
	@Nullable
	private String username;
	private String password;
	@Nullable
	private String email;
}

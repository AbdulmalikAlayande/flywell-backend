package com.example.airlinereservation.dtos.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
	
	String message;
	String username;
}

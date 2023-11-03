package com.example.airlinereservation.dtos.Request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateFlightInstanceRequest {
	
	@NonNull
	private String arrivalState;
	@NonNull
	private String departureState;
}

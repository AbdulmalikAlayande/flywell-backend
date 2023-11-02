package com.example.airlinereservation.dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateFlightInstanceRequest {
	
	@NotBlank
	private LocalDate departureDate;
	@NotBlank
	private LocalTime departureTime;
	private LocalTime arrivalTime;
	private LocalDate arrivalDate;
	private String arrivalState;
	private String departureState;
}

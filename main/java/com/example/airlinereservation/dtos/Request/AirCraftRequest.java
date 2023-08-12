package com.example.airlinereservation.dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AirCraftRequest {
	
	private String airCraftName;
	private String model;
	private LocalDate datePurchased;
}

package com.example.airlinereservation.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AirCraft {
	@Id
	private String id;
	private String airCraftName;
	private boolean isAvailable;
	private final int numberOfSeats = BigInteger.valueOf(20).intValue();
	@Transient
	private final AirplaneSeats[] seats = new AirplaneSeats[numberOfSeats];
	public final boolean[] aircraftSeats = new boolean[numberOfSeats];
	
	public boolean getAircraftSeatsAt(int seatIndex){
		return aircraftSeats[seatIndex];
	}
	
}

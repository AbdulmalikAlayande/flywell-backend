package com.example.airlinereservation.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirCraft {
	private String id;
	private String airCraftName;
	private final int numberOfSeats = BigInteger.valueOf(20).intValue();
	private final AirplaneSeats[] seats = new AirplaneSeats[numberOfSeats];
	public final boolean[] aircraftSeats = new boolean[numberOfSeats];
	
	public boolean getAircraftSeatsAt(int seatIndex){
		return aircraftSeats[seatIndex];
	}
	
	public boolean getInitialStateOfSeat(){
		boolean isEmptySeat = false;
		for (boolean seat : aircraftSeats) {
			if (!seat) {
				isEmptySeat = true;
				break;
			}
		}
		return isEmptySeat;
	}
	
}

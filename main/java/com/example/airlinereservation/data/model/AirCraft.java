package com.example.airlinereservation.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirCraft {
	private String id;
	private String airCraftName;
	private AirplaneSeats[] seats;
	private final int numberOfSeats = 20;
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

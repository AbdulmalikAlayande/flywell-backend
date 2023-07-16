package com.example.airlinereservation.services;
import com.example.airlinereservation.data.model.Flight;

import java.math.BigInteger;

public class FirstClassBookingCategory extends BookingCategory {
	private static FirstClassBookingCategory instance = null;
	private FirstClassBookingCategory (){}
	private static final int lastSeatInTheCategory = BigInteger.valueOf(4).intValue();
	boolean canBook(Flight flight) {
		return !flight.getAirCraft().getAircraftSeats()[lastSeatInTheCategory];
	}
	
	public static FirstClassBookingCategory getInstance() {
		if (instance == null)
			return new FirstClassBookingCategory();
		return instance;
	}
	
	void assignSeat(Flight flight) {
		assignSeatToPassenger(flight);
	}
	
	private void assignSeatToPassenger(Flight flight) {
		for (int firstSeat = 0; firstSeat < lastSeatInTheCategory; firstSeat++) {
			if (!flight.getAirCraft().getAircraftSeats()[firstSeat]) {
				flight.getAirCraft().getAircraftSeats()[firstSeat] = true;
				break;
			}
		}
	}
	
}

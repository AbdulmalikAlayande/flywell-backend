package com.example.airlinereservation.services.categories;

import com.example.airlinereservation.data.model.Flight;
import com.example.airlinereservation.data.model.Passenger;

import java.math.BigInteger;

public class PremiumEconomyClassBookingCategory extends BookingCategory {
	
	private static PremiumEconomyClassBookingCategory instance = null;
	PremiumEconomyClassBookingCategory(){}
	private static final int firstSeatInTheCategory = BigInteger.TEN.intValue();
	private static final int lastSeatInTheCategory = BigInteger.valueOf(10).intValue();
	
	public static PremiumEconomyClassBookingCategory getInstance() {
		if (instance == null)
			return new PremiumEconomyClassBookingCategory();
		return instance;
	}
	
	public boolean canBook(Flight flight) { return true; }
	public void assignSeat(Flight flight) {
		assignSeatToPassenger(flight);
	}
	@Override
	public void assignSeat(Passenger passenger, Flight flight) {
	
	}
	private void assignSeatToPassenger(Flight flight) {
		for (int firstSeat = firstSeatInTheCategory; firstSeat <= lastSeatInTheCategory; firstSeat++) {
//			if (!flight.getAirCraft().getAircraftSeats()[firstSeat]) {
//				flight.getAirCraft().getAircraftSeats()[firstSeat] = true;
//				break;
//			}
		}
	}
}

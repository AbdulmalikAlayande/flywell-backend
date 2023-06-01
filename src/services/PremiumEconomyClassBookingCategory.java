package services;

import data.model.Flight;

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
	
	boolean canBook(Flight flight) { return !flight.getSeats()[14]; }
	void assignSeat(Flight flight) {
		assignSeatToPassenger(flight);
	}
	
	private void assignSeatToPassenger(Flight flight) {
		for (int firstSeat = firstSeatInTheCategory; firstSeat <= lastSeatInTheCategory; firstSeat++) {
			if (!flight.getSeats()[firstSeat]) {
				flight.getSeats()[firstSeat] = true;
				break;
			}
		}
	}
}

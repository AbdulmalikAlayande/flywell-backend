package services;

import data.model.Flight;

import java.math.BigInteger;

public class EconomyClassBookingCategory extends BookingCategory {
	private static EconomyClassBookingCategory instance = null;
	
	private static final int firstSeatInTheCategory = BigInteger.valueOf(15).intValue();
	private static final int lastSeatInTheCategory = BigInteger.valueOf(19).intValue();
	
	public static EconomyClassBookingCategory getInstance() {
		if (instance == null)
			return new EconomyClassBookingCategory();
		return instance;
	}
	
	public boolean canBook(Flight flight) { return !flight.getSeats()[19]; }
	
	public void assignSeat(Flight flight) { assignSeatToPassenger(flight); }
	
	private void assignSeatToPassenger(Flight flight) {
		
		for (int firstSeat = firstSeatInTheCategory; firstSeat <= lastSeatInTheCategory; firstSeat++) {
			if (!flight.getSeats()[firstSeat]) {
				flight.getSeats()[firstSeat] = true;
				break;
			}
		}
	}
}

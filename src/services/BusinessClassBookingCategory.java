package services;

import data.model.Flight;

import java.math.BigInteger;

public class BusinessClassBookingCategory extends BookingCategory {
	private BusinessClassBookingCategory(){}
	private static BusinessClassBookingCategory instance = null;
	
	private static final int lastSeatInTheCategory = BigInteger.valueOf(9).intValue();
	private static final int firstSeatInTheCategory = BigInteger.valueOf(5).intValue();
	
	public static BusinessClassBookingCategory getInstance() {
		if (instance == null)
			return new BusinessClassBookingCategory();
		return instance;
	}
	
	boolean canBook(Flight flight) { return !flight.getSeats()[9]; }
	void assignSeat(Flight flight) {
		assignSeatToPassenger(flight);
	}
	
	private void assignSeatToPassenger(Flight flight) {
		for (int firstSeat = firstSeatInTheCategory; firstSeat < lastSeatInTheCategory; firstSeat++) {
			if (!flight.getSeats()[firstSeat]) {
				flight.getSeats()[firstSeat] = true;
				break;
			}
		}
	}
}

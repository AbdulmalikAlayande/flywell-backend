package app.bola.flywell.services.categories;

import app.bola.flywell.data.model.Passenger;
import app.bola.flywell.data.model.flight.Flight;

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
	
	public boolean canBook(Flight flight) { return true; }
	public void assignSeat(Flight flight) {
		assignSeatToPassenger(flight);
	}
	
	@Override
	public void assignSeat(Passenger passenger, Flight flight) {
	
	}
	
	private void assignSeatToPassenger(Flight flight) {
		for (int firstSeat = firstSeatInTheCategory; firstSeat < lastSeatInTheCategory; firstSeat++) {
//			if (!flight.getAirCraft().getAircraftSeats()[firstSeat]) {
//				flight.getAirCraft().getAircraftSeats()[firstSeat] = true;
//				break;
//			}
		}
	}
}

package app.bola.flywell.services.categories;


import app.bola.flywell.data.model.Passenger;
import app.bola.flywell.data.model.flight.Flight;

public abstract class BookingCategory {
	public abstract boolean canBook(Flight flight);
	public abstract void assignSeat(Flight flight);
	public abstract void assignSeat(Passenger passenger, Flight flight);
	
}

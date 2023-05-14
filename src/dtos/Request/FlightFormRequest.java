package dtos.Request;

import data.model.Flight;
import data.model.Passenger;

public class FlightFormRequest {
	private Passenger passenger;
	private Flight flight;
	
	public Passenger getPassenger() {
		return passenger;
	}
	
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	
	public Flight getFlight() {
		return flight;
	}
	
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
}

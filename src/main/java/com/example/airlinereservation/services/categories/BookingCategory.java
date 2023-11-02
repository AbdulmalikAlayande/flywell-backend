package com.example.airlinereservation.services.categories;


import com.example.airlinereservation.data.model.flight.Flight;
import com.example.airlinereservation.data.model.Passenger;

public abstract class BookingCategory {
	public abstract boolean canBook(Flight flight);
	public abstract void assignSeat(Flight flight);
	public abstract void assignSeat(Passenger passenger, Flight flight);
	
}

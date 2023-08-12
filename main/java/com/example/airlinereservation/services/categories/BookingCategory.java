package com.example.airlinereservation.services.categories;


import com.example.airlinereservation.data.model.flight.Flight;
import com.example.airlinereservation.data.model.Passenger;

public abstract class BookingCategory {
	public abstract boolean canBook(Flight flight);
	public abstract void assignSeat(Flight flight);
	public abstract void assignSeat(Passenger passenger, Flight flight);
	
}

// Modify the FlightBooking class to use the new booking categories
/*when the passenger wants to book a flight,
they must input their name
find the passenger by name if found book a flight in a current unfilled flight for the passenger
return the passenger for use later in the future
the if the flight has been booked
generate a flight form
call the save flight form service of the service class
pass in flightForm Request
then map and save the flight form and the return response
then save the form
*/

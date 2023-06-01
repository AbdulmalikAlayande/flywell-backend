package services;

import data.model.Flight;

public abstract class BookingCategory {
	abstract boolean canBook(Flight flight);
	abstract void assignSeat(Flight flight);
}
/*@Override
	public Flight bookFlight(BookingRequest bookingRequest) {
		if (bookingRequestIsValid(bookingRequest) && paymentIsCompleted(bookingRequest) && nameIsValid(bookingRequest)) {
			Flight availableFlight = checkAvailableFlight();
			if (bookingRequest.getBookingCategory() == 0 && !availableFlight.getSeats()[4])
				bookFlightForFirstClass(availableFlight, bookingRequest.getBookingCategory());
			else if (bookingRequest.getBookingCategory() == 1 && !availableFlight.getSeats()[9])
				bookFlightForBusinessClass(availableFlight, bookingRequest.getBookingCategory());
			else if (bookingRequest.getBookingCategory() == 2 && !availableFlight.getSeats()[14])
				bookFlightForPremiumEconomyClass(availableFlight, bookingRequest.getBookingCategory());
			else if (bookingRequest.getBookingCategory() == 3 && !availableFlight.getSeats()[19])
				bookFlightForEconomyClass(availableFlight, bookingRequest.getBookingCategory());
			return availableFlight;
		}
		throw new RuntimeException("Invalid booking request");
	}
	*/

/*private void bookFlightForEconomyClass(Flight availableFlight, int bookingCategory) {
		assignSeatToPassenger(availableFlight, bookingCategory);
	}
	private void bookFlightForPremiumEconomyClass(Flight availableFlight, int bookingCategory) {
		assignSeatToPassenger(availableFlight, bookingCategory);
	}
	private void bookFlightForBusinessClass(Flight availableFlight, int bookingCategory) {assignSeatToPassenger(availableFlight, bookingCategory);}
	private void bookFlightForFirstClass(Flight availableFlight, int bookingCategory) {assignSeatToPassenger(availableFlight, bookingCategory);}
*/

/*private void assignSeatToPassenger(Flight availableFlight, int bookingCategory) {
		int lastAvailableSeatForTheBookingCategory = 0;
		int firstSeatNumber = 0;
		if (bookingCategory == 0) {lastAvailableSeatForTheBookingCategory = 5;}
		else if (bookingCategory == 1) {lastAvailableSeatForTheBookingCategory = 9;firstSeatNumber = 5;}
		else if (bookingCategory == 2) {lastAvailableSeatForTheBookingCategory = 14;firstSeatNumber = 10;}
		else if (bookingCategory == 3) {lastAvailableSeatForTheBookingCategory = 19;firstSeatNumber = 15;}
		for (int firstSeat = firstSeatNumber; firstSeat < lastAvailableSeatForTheBookingCategory; firstSeat++) {
			if (!availableFlight.getSeats()[firstSeat]) {
				availableFlight.getSeats()[firstSeat] = true;
				break;
			}
		}
		
	}*/

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

	

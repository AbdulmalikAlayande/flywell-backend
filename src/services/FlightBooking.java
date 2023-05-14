package services;

import data.model.Flight;
import data.model.TravelClass;
import dtos.Request.BookingRequest;
import dtos.Request.FlightRequest;
import dtos.Response.FlightResponse;
import dtos.Response.PassengerResponse;
import utils.DateTime.Date;

import java.time.LocalTime;
import java.util.EnumSet;
import java.util.List;
/*when the passenger wants to book a flight,
they must input their name
find the passenger by name if found book a flight in a current unfilled flight for the passenger
return the passenger for use later in the future
the if the flight has been booked
generate a flight form
call the save flight form service of the service class
pass in flightForm Request
then map and save the flight form and return the response
then save the form
*/
import static data.model.TravelClass.ECONOMY_CLASS;
import static data.model.TravelClass.FIRST_CLASS;

public class FlightBooking implements Bookable{
	FlightService flightService = new FlightServiceImpl();
	PassengerService passengerService = PassengerServiceImplementation.getInstance();
	
	
	@Override
	public Flight checkAvailableFlight() {
		List<Flight> allFlights = flightService.getAllFLights();
		Flight flight = null;
		if (allFlights.isEmpty()) return newFlightReadyForBooking();
		for (Flight eachFlight : allFlights) {
			if (eachFlight.getSeats()[4] && eachFlight.getSeats()[9] && eachFlight.getSeats()[14] && eachFlight.getSeats()[19]) return newFlightReadyForBooking();
			else flight = eachFlight;
			break;
		}
		return flight;
	}
	@Override
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
	
	private boolean paymentIsCompleted(BookingRequest bookingRequest) {
		return true;
	}
	
	private boolean nameIsValid(BookingRequest bookingRequest){
		PassengerResponse passengerResponse = passengerService.findPassengerByUserName(bookingRequest.getPassengerUsername());
		return passengerResponse != null;
	}
	
	private boolean bookingRequestIsValid(BookingRequest bookingRequest) {
		boolean isInvalidBookingRequest = bookingRequest.getBookingCategory() < 0 && bookingRequest.getBookingCategory() > 3;
		if (!isInvalidBookingRequest && isValidTravelClass(bookingRequest)) {
			return true;
		}
		if (isInvalidBookingRequest) throw new IllegalArgumentException("Invalid Booking Category");
		return false;
	}
	
	private boolean isValidTravelClass(BookingRequest bookingRequest) {
		for (TravelClass travelClass : EnumSet.range(FIRST_CLASS, ECONOMY_CLASS))
			if (travelClass.ordinal() == bookingRequest.getBookingCategory()) {
				return true;
			}
		throw new IllegalArgumentException("Invalid booking category");
	}
	
	private Flight newFlightReadyForBooking() {
		FlightRequest flightRequest = new FlightRequest();
		Date departureDate = new Date();
		departureDate.setDate(12, 4, 2024);
		flightRequest.setDestination("Iju");
		flightRequest.setBaggageAllowance(3);
		flightRequest.setDepartureDate(departureDate);
		flightRequest.setDepartureTime(LocalTime.of(11, 35, 0));
		flightRequest.setArrivalTime(LocalTime.of(17, 35));
		flightRequest.setArrivalDate(new Date().setDate(23, 9, 2024));
		saveFlight(flightRequest);
		return flightService.saveFlightForAdmin(flightRequest);
	}
	
	private void bookFlightForEconomyClass(Flight availableFlight, int bookingCategory) {assignSeatToPassenger(availableFlight, bookingCategory);}
	private void bookFlightForPremiumEconomyClass(Flight availableFlight, int bookingCategory) {assignSeatToPassenger(availableFlight, bookingCategory);}
	private void bookFlightForBusinessClass(Flight availableFlight, int bookingCategory) {assignSeatToPassenger(availableFlight, bookingCategory);}
	private void bookFlightForFirstClass(Flight availableFlight, int bookingCategory) {assignSeatToPassenger(availableFlight, bookingCategory);}
	private void assignSeatToPassenger(Flight availableFlight, int bookingCategory) {
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
	}
	
	@Override
	public FlightResponse saveFlight(FlightRequest flightRequest) {
		return flightService.saveFlight(flightRequest);
	}
	
	@Override
	public void getAvailableSeatsByFlightId(String flightId) {
	
	}
	
	@Override
	public String cancelFlight(String passengerUsername) {
		return null;
	}
}

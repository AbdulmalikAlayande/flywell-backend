package services;

import data.model.Flight;
import data.model.Passenger;
import data.model.TravelClass;
import dtos.Request.BookingRequest;
import dtos.Request.FlightRequest;
import dtos.Response.FlightResponse;
import dtos.Response.PassengerResponse;
import lombok.SneakyThrows;
import utils.DateTime.Date;

import java.time.LocalTime;
import java.util.EnumSet;
import java.util.List;

import static data.model.TravelClass.ECONOMY_CLASS;
import static data.model.TravelClass.FIRST_CLASS;


public class FlightBooking implements Bookable{
	FlightService flightService = new FlightServiceImpl();
	PassengerService passengerService = PassengerServiceImplementation.getInstance();
	
	private final List<BookingCategory> bookingCategories = List.of(
			FirstClassBookingCategory.getInstance(),
			BusinessClassBookingCategory.getInstance(),
			PremiumEconomyClassBookingCategory.getInstance(),
			EconomyClassBookingCategory.getInstance()
	);
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
			setPassengerToArrayOfPassengers(bookingRequest, availableFlight);
			BookingCategory bookingCategory = iDontKnowTheName(bookingRequest.getBookingCategory());
			if (bookingCategory.canBook(availableFlight)) {
				bookingCategory.assignSeat(availableFlight);
				return availableFlight;
			}
		}
		throw new RuntimeException("Invalid booking request");
	}
	
	private void setPassengerToArrayOfPassengers(BookingRequest bookingRequest, Flight availableFlight) {
		for (int i = 0; i < availableFlight.getPassengers().length; i++) {
			if (availableFlight.getPassengers()[i] == null) {
				availableFlight.getPassengers()[i] = foundPassenger(bookingRequest);
				break;
			}
		}
	}
	
	private Passenger foundPassenger(BookingRequest bookingRequest) {
		return passengerService.findPassengerByUserNameForAdmin(bookingRequest.getPassengerUsername());
	}
	
	private BookingCategory iDontKnowTheName(int bookingCategory){
		return bookingCategories.get(bookingCategory);
	}
	
	private boolean paymentIsCompleted(BookingRequest bookingRequest) {
		return true;
	}
	
	@SneakyThrows
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

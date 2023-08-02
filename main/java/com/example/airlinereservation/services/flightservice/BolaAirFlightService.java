package com.example.airlinereservation.services.flightservice;

import com.example.airlinereservation.data.model.*;
import com.example.airlinereservation.data.model.enums.Destinations;
import com.example.airlinereservation.data.model.enums.TravelClass;
import com.example.airlinereservation.dtos.Request.BookingRequest;
import com.example.airlinereservation.dtos.Request.FlightRequest;
import com.example.airlinereservation.dtos.Response.FlightResponse;
import com.example.airlinereservation.dtos.Response.PassengerResponse;
import com.example.airlinereservation.services.categories.*;
import com.example.airlinereservation.services.passengerservice.PassengerService;
import com.example.airlinereservation.utils.exceptions.InvalidRequestException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.example.airlinereservation.utils.appUtils.AppUtilities.INVALID_DESTINATION;


@Service
@AllArgsConstructor
public class BolaAirFlightService implements Bookable {
	PassengerService passengerService;
	ModelMapper mapper;
	private final AirCraft[] availableAirCrafts = new AirCraft[]{};
	private final List<Flight> availableFlights = new ArrayList<>();
	
	private final List<BookingCategory> bookingCategories = List.of(
			FirstClassBookingCategory.getInstance(),
			BusinessClassBookingCategory.getInstance(),
			PremiumEconomyClassBookingCategory.getInstance(),
			EconomyClassBookingCategory.getInstance()
	);
	@Override
	public Flight getAvailableFlight(String destination) throws InvalidRequestException {
		try {
			return availableFlight(destination);
		}catch (Exception exception){
			throw new InvalidRequestException(INVALID_DESTINATION);
		}
	}
	
	@NotNull
	private Flight availableFlight(String destination) {
		Stream<Flight> availableFlight = availableFlights.stream().filter(flight -> flight.getFrom() == Destinations.valueOf(destination.toUpperCase()));
		return availableFlight.findAny().orElseThrow();
	}
	
	public FlightResponse checkAvailableFlight(String destination) throws InvalidRequestException {
		try {
			Flight flight = availableFlight(destination);
			FlightResponse response = new FlightResponse();
			mapper.map(flight, response);
			return response;
		}catch (Exception exception){
			throw new InvalidRequestException(INVALID_DESTINATION);
		}
	}
	
	@Override
	public FlightResponse bookFLight(BookingRequest bookingRequest) {
		return null;
	}
	
	@Override
	public FlightResponse getAvailableSeatsByFlightId(String flightId) {
		return null;
	}
	
	@Override
	public String cancelFlight(String passengerUsername) {
		return null;
	}
	
	@Override
	public boolean isNotFilled(Flight foundFlight) {
		return false;
	}
	
	@Override
	public Flight createNewFlight(String destination) {
		Flight flight = new Flight();
		flight.setFrom(Destinations.valueOf(destination.toUpperCase()));
		AirCraft availableAirCraft = getAvailableAirCraft();
		return flight;
	}
	
	private AirCraft getAvailableAirCraft() {
		return null;
	}
	
	@Override
	public void assignSeatToPassenger(Passenger passenger, String destination, int category) {
		for (Flight flight : availableFlights) {
			if (flight.getFrom() == Destinations.valueOf(destination.toUpperCase())) {
				BookingCategory bookingClass = bookingCategories.get(category);
				bookingClass.assignSeat(passenger, flight);
			}
		}
	}
	
	private Flight newFlightReadyForBooking(Flight flight) {
		
		return null;
	}
	
	@Override
	public Flight bookFlight(BookingRequest bookingRequest) throws InvalidRequestException {
		if (bookingRequestIsValid(bookingRequest) && paymentIsCompleted(bookingRequest) && nameIsValid(bookingRequest)) {
			Flight availableFlight = getAvailableFlight("");
			setPassengerToArrayOfPassengers(bookingRequest, availableFlight);
			BookingCategory bookingCategory = getBookingCategory(bookingRequest.getBookingCategory());
			if (bookingCategory.canBook(availableFlight)) {
				bookingCategory.assignSeat(availableFlight);
				return availableFlight;
			}
		}
		throw new RuntimeException("Invalid booking request");
	}
	
	private void setPassengerToArrayOfPassengers(BookingRequest bookingRequest, Flight availableFlight) {
	
	}
	
	private Optional<Passenger> foundPassenger(BookingRequest bookingRequest) {
		return passengerService.findPassengerByUserNameForAdmin(bookingRequest.getPassengerUsername());
	}
	
	private BookingCategory getBookingCategory(int bookingCategory){
		return bookingCategories.get(bookingCategory);
	}
	
	private boolean paymentIsCompleted(BookingRequest  bookingRequest) {
		return true;
	}
	
	@SneakyThrows
	private boolean nameIsValid(BookingRequest bookingRequest){
		Optional<PassengerResponse> passengerResponse = passengerService.findPassengerByUserName(bookingRequest.getPassengerUsername());
		return passengerResponse.isPresent();
	}
	
	private boolean bookingRequestIsValid(BookingRequest bookingRequest) {
		boolean isInvalidBookingRequest = bookingRequest.getBookingCategory() < 0 && bookingRequest.getBookingCategory() > 3;
		if (!isInvalidBookingRequest && isValidTravelClass(bookingRequest)) return true;
		else if (isInvalidBookingRequest) throw new IllegalArgumentException("Invalid Booking Category");
		return false;
	}
	
	private boolean isValidTravelClass(BookingRequest bookingRequest) {
		for (TravelClass travelClass : EnumSet.range(TravelClass.FIRST_CLASS, TravelClass.ECONOMY_CLASS))
			if (travelClass.ordinal() == bookingRequest.getBookingCategory()) {
				return true;
			}
		throw new IllegalArgumentException("Invalid booking category");
	}
	
	private Flight newFlightReadyForBooking() {
		LocalTime departureTime = LocalTime.now();
		LocalDate departureDate = LocalDate.now();
		LocalTime arrivalTime = LocalTime.of(departureTime.getHour() + 5, departureTime.getMinute());
		LocalDate arrivalDate = null;
		if (arrivalTime == LocalTime.MIDNIGHT)
			arrivalDate = LocalDate.of(departureDate.getYear(), departureDate.getMonth(), departureDate.getDayOfMonth() + 1);
		return new Flight();
	}
	
	
	private FlightRequest buildFlightRequest(LocalTime departureTime, LocalTime arrivalTime, LocalDate arrivaldate) {
		return FlightRequest.builder()
				       .Airline("Bola-Air")
				       .arrivalTime(arrivalTime)
				       .departureTime(departureTime)
				       .arrivalDate(arrivaldate)
				       .departureDate(LocalDate.now())
				       .build();
	}
}
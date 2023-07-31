package com.example.airlinereservation.services.flightservice;

import com.example.airlinereservation.data.model.Destinations;
import com.example.airlinereservation.data.model.Flight;
import com.example.airlinereservation.data.model.Passenger;
import com.example.airlinereservation.data.model.TravelClass;
import com.example.airlinereservation.dtos.Request.BookingRequest;
import com.example.airlinereservation.dtos.Request.FlightRequest;
import com.example.airlinereservation.dtos.Response.FlightResponse;
import com.example.airlinereservation.dtos.Response.PassengerResponse;
import com.example.airlinereservation.services.categories.*;
import com.example.airlinereservation.services.passengerservice.PassengerService;
import com.example.airlinereservation.utils.exceptions.InvalidRequestException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequiredArgsConstructor
public class BolaAirFlightService implements Bookable {
	FlightService flightService = new FlightServiceImpl();
	@Autowired
	PassengerService passengerService;
	@Autowired
	ModelMapper mapper;
	private final List<Flight> availableFlights = new ArrayList<>();
	
	private final List<BookingCategory> bookingCategories = List.of(
			FirstClassBookingCategory.getInstance(),
			BusinessClassBookingCategory.getInstance(),
			PremiumEconomyClassBookingCategory.getInstance(),
			EconomyClassBookingCategory.getInstance()
	);
	@Override
	public Flight getAvailableFlight(String destination) {
		return null;
	}
	
	public FlightResponse checkAvailableFlight(String destination) throws InvalidRequestException {
		try {
			Stream<Flight> availableFlight = availableFlights.stream().filter(flight -> flight.getDestination() == Destinations.valueOf(destination.toUpperCase()));
			Flight flight = availableFlight.findAny().orElseThrow();
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
	
	private Flight newFlightReadyForBooking(Flight flight) {
		
		return null;
	}
	
	@Override
	public Flight bookFlight(BookingRequest bookingRequest) {
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
		for (int i = 0; i < availableFlight.getPassengers().size(); i++) {
			if (availableFlight.getPassengers().get(i) == null) {
				availableFlight.getPassengers().set(i, foundPassenger(bookingRequest).get());
				break;
			}
		}
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
		return flightService.saveFlightForAdminUsage(buildFlightRequest(departureTime, arrivalTime, arrivalDate));
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
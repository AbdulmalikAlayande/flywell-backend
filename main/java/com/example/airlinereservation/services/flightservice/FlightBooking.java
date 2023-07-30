package com.example.airlinereservation.services.flightservice;

import com.example.airlinereservation.data.model.Flight;
import com.example.airlinereservation.data.model.Passenger;
import com.example.airlinereservation.data.model.TravelClass;
import com.example.airlinereservation.services.categories.*;
import com.example.airlinereservation.services.passengerservice.PassengerService;
import com.example.airlinereservation.utils.exceptions.InvalidRequestException;
import lombok.SneakyThrows;
import com.example.airlinereservation.dtos.Request.BookingRequest;
import com.example.airlinereservation.dtos.Request.FlightRequest;
import com.example.airlinereservation.dtos.Response.FlightResponse;
import com.example.airlinereservation.dtos.Response.PassengerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;


@Service
public class FlightBooking implements Bookable {
	FlightService flightService = new FlightServiceImpl();
	@Autowired
	PassengerService passengerService;
	
	private final List<BookingCategory> bookingCategories = List.of(
			FirstClassBookingCategory.getInstance(),
			BusinessClassBookingCategory.getInstance(),
			PremiumEconomyClassBookingCategory.getInstance(),
			EconomyClassBookingCategory.getInstance()
	);
	@Override
	public Flight checkAvailableFlight() {
		Optional<List<Flight>> allFlights = flightService.getAllFLights();
		Flight flight;
		if (allFlights.isEmpty()) return newFlightReadyForBooking();
		flight = allFlights.get().get(allFlights.get().size() - 1);
		if (flight.getAirCraft().getAircraftSeats()[4] && flight.getAirCraft().getAircraftSeats()[9]
				    && flight.getAirCraft().getAircraftSeats()[14] && flight.getAirCraft().getAircraftSeats()[19])
			return newFlightReadyForBooking(flight);
		return flight;
	}
	
	private Flight newFlightReadyForBooking(Flight flight) {
		FlightRequest newFlightToBeCreated;
		int day = flight.getArrivalDate().getDayOfMonth();
		Month month = flight.getArrivalDate().getMonth();
		int hour = flight.getArrivalTime().getHour() + 3;
		LocalTime time = LocalTime.of(hour, flight.getArrivalTime().getMinute());
		//todo part that takes care
		LocalDate date = null;
		if (time == LocalTime.MIDNIGHT) {
			int newDay = day + 1;
			if (day > month.maxLength() || day > month.minLength()) {
				Month newMonth = Month.of(month.ordinal() + 1);
				date = LocalDate.of(flight.getArrivalDate().getYear(), newMonth, newDay);
			} else date = LocalDate.of(flight.getArrivalDate().getYear(), month, newDay);
		} else date = LocalDate.of(flight.getArrivalDate().getYear(), month, day);
		//todo part for arrival time
		LocalTime arrivalTime = time.plusHours(5);
		LocalDate departureDate = LocalDate.of(flight.getArrivalDate().getYear(), month, day);
		LocalDate arrivalDate = null;
		if (arrivalTime == LocalTime.MIDNIGHT) {
			int arrivalDay = departureDate.getDayOfMonth() + 1;
			if(arrivalDay > departureDate.getMonth().maxLength() || arrivalDay > departureDate.getMonth().minLength()){
	       		Month arrivalMonth = Month.of(departureDate.getMonth().ordinal() + 1);
		        arrivalDate = LocalDate.of(departureDate.getYear(), arrivalMonth, arrivalDay);
			}else arrivalDate = LocalDate.of(departureDate.getYear(), departureDate.getMonth(), arrivalDay);
		}
		else arrivalDate = LocalDate.of(departureDate.getYear(), departureDate.getMonth(), departureDate.getDayOfMonth());
		newFlightToBeCreated = FlightRequest.builder()
				                       .Airline("Bola-Air")
				                       .departureDate(departureDate)
				                       .departureTime(time)
				                       .arrivalDate(arrivalDate)
				                       .arrivalTime(arrivalTime)
				                       .build();
		return null;
	}
	
	@Override
	public Flight bookFlight(BookingRequest bookingRequest) throws InvalidRequestException {
		if (bookingRequestIsValid(bookingRequest) && paymentIsCompleted(bookingRequest) && nameIsValid(bookingRequest)) {
			Flight availableFlight = checkAvailableFlight();
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
	
	@Override
	public FlightResponse saveFlight(FlightRequest flightRequest) throws InvalidRequestException {
		return flightService.saveFlight(flightRequest);
	}
	
	@Override
	public void getAvailableSeatsByFlightId(String flightId) {
	
	}
	
	@Override
	public String cancelFlight(String passengerUsername) {
		return null;
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
package com.example.airlinereservation.services.flightservice;

import com.example.airlinereservation.Mapper.FlightMapper;
import com.example.airlinereservation.data.model.Flight;
import com.example.airlinereservation.dtos.Request.BookingRequest;
import com.example.airlinereservation.dtos.Response.FlightResponse;
import com.example.airlinereservation.services.categories.*;
import com.example.airlinereservation.utils.exceptions.InvalidRequestException;
import com.example.airlinereservation.config.mycustomannotations.AdminMethod;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.example.airlinereservation.data.repositories.FlightRepository;
import com.example.airlinereservation.dtos.Request.FlightRequest;
import com.example.airlinereservation.dtos.Request.FlightUpdateRequest;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

@Service
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class FlightServiceImpl implements FlightService {
	private FlightRepository flightRepo;
	String flightId = null;
	
	private final List<BookingCategory> bookingCategories = List.of(
			FirstClassBookingCategory.getInstance(),
			BusinessClassBookingCategory.getInstance(),
			PremiumEconomyClassBookingCategory.getInstance(),
			EconomyClassBookingCategory.getInstance()
	);
	@Override
	public FlightResponse saveFlight(FlightRequest flightRequest) throws InvalidRequestException {
		Flight mappedFlight = FlightMapper.map(flightRequest);
		validateThatEachFieldOfTheFlightObjectIsNotNull(mappedFlight);
		Flight savedFlight = flightRepo.save(mappedFlight);
		flightId = savedFlight.getId();
		flightRequest.setFlightId(savedFlight.getId());
		return FlightMapper.map(savedFlight);
	}
	
	@Override @AdminMethod
	public Flight saveFlightForAdminUsage(FlightRequest flightRequest) {
		Flight mappedFlight = FlightMapper.map(flightRequest);
		return flightRepo.save(mappedFlight);
	}
	
	public String flightId(){
		return flightId;
	}
	
	@Override
	public FlightResponse updateFlight(FlightUpdateRequest flightRequest) {
		Optional<Flight> foundFlight = flightRepo.findById(flightRequest.getFlightId());
		return FlightMapper.map(foundFlight.get());
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
	public boolean deleteFlightBy(String flightId) throws InvalidRequestException {
		flightRepo.deleteById(flightId);
		return true;
	}
	
	@Override
	public Optional<FlightResponse> findFlightBy(String flightId) throws InvalidRequestException {
		Optional<Flight> foundFlight = flightRepo.findById(flightId);
		if (foundFlight.isEmpty()) throw new InvalidRequestException("Error: Invalid Id");
		return Optional.ofNullable(FlightMapper.map(foundFlight.get()));
	}
	
	@Override
	public long getCountOfAllFlights() {
		return flightRepo.count();
	}
	
	@Override
	public Optional<List<Flight>> getAllFLights() {
		List<Flight> foundFlights = new ArrayList<>();
		for (int i = 0; i < flightRepo.findAll().size(); i++)
			if (flightRepo.findAll().get(i) != null) foundFlights.add(flightRepo.findAll().get(i));
		return Optional.of(foundFlights);
	}
	
	@Override
	public FlightResponse bookFLight(BookingRequest bookingRequest) {
		return null;
	}
	
	private void validateThatEachFieldOfTheFlightObjectIsNotNull(Flight flight) throws InvalidRequestException {
		if (flight.getAirline() == null || flight.getArrivalDate() == null
				    || flight.getDestination() == null || flight.getArrivalTime() == null
				    || flight.getDepartureTime() == null || flight.getDepartureDate() == null
				    || flight.getBaggageAllowance() == 0 || flight.getPassengers() == null)
			throw new InvalidRequestException("ERROR: some fields may have not being filled, please fill all fields");
	}
}

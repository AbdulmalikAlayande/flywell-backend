package services;

import Mapper.FlightMapper;
import data.model.Flight;
import data.repositories.FlightRepository;
import data.repositories.FlightRepositoryImplementation;
import dtos.Request.BookingRequest;
import dtos.Request.FlightRequest;
import dtos.Request.FlightUpdateRequest;
import dtos.Response.FlightResponse;

import java.util.List;
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

public class FlightServiceImpl implements FlightService{
	private final FlightRepository flightRepo = new FlightRepositoryImplementation();
	
	@Override
	public FlightResponse saveFlight(FlightRequest flightRequest) {
		Flight mappedFlight = FlightMapper.map(flightRequest);
		Flight savedFlight = flightRepo.saveFlight(mappedFlight);
		flightRequest.setFlightId(savedFlight.getFlightId());
		return FlightMapper.map(savedFlight);
	}
	
	@Override
	public Flight saveFlightForAdmin(FlightRequest flightRequest) {
		Flight mappedFlight = FlightMapper.map(flightRequest);
		return flightRepo.saveFlight(mappedFlight);
	}
	
	@Override
	public FlightResponse updateFlight(FlightUpdateRequest flightRequest) {
		Flight foundFlight = flightRepo.findFlightBy(flightRequest.getFlightId());
		if (flightRequest.getArrivalDate() != null) foundFlight.setArrivalDate(flightRequest.getArrivalDate());
		if (flightRequest.getArrivalTime() != null) foundFlight.setArrivalTime(flightRequest.getArrivalTime());
		if (flightRequest.getAirline() != null) foundFlight.setAirline(flightRequest.getAirline());
		if (flightRequest.getDestination() != null) foundFlight.setDestination(flightRequest.getDestination());
		if (flightRequest.getDepartureTime() != null) foundFlight.setDepartureTime(flightRequest.getDepartureTime());
		foundFlight.setBaggageAllowance(flightRequest.getBaggageAllowance());
		return FlightMapper.map(foundFlight);
	}
	
	@Override
	public boolean deleteFlightBy(String flightId) {
		return flightRepo.deleteFlightBy(flightId);
	}
	
	@Override
	public FlightResponse findFlightBy(String flightId) {
		return FlightMapper.map(flightRepo.findFlightBy(flightId));
	}
	
	@Override
	public int getCountOfAllFlights() {
		return flightRepo.getCountOfAllFlights();
	}
	
	@Override
	public List<Flight> getAllFLights() {
		return flightRepo.getAllFlights();
	}
	
	@Override
	public void bookFLight(BookingRequest bookingRequest) {
	
	}
}

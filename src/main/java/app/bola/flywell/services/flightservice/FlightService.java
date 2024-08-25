package app.bola.flywell.services.flightservice;

import app.bola.flywell.data.model.flight.Flight;
import app.bola.flywell.dtos.Request.*;
import app.bola.flywell.dtos.Response.*;
import app.bola.flywell.exceptions.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface FlightService {
	
	FlightResponse addFlight(FlightRequest flightRequest) throws InvalidRequestException;
	FlightResponse updateFlight(FlightUpdateRequest flightRequest);
	List<Flight> getAllFLights();
	FlightResponse getFlightByArrivalAndDepartureLocation(String arrivalCity, String departureCity) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvalidRequestException;
	Long getCountOfAllFlights();
	
	void removeAll();
}

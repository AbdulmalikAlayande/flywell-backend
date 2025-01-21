package app.bola.flywell.services.flightservice;

import app.bola.flywell.basemodules.FlyWellService;
import app.bola.flywell.data.model.flight.Flight;
import app.bola.flywell.dto.response.FlightResponse;
import app.bola.flywell.dto.request.*;
import app.bola.flywell.exceptions.*;

import java.util.List;

/**
 * Interface for managing flight-related operations.
 */
public interface FlightService extends FlyWellService<FlightRequest, FlightResponse> {

	/**
	 * Adds a new flight to the system.
	 *
	 * @param flightRequest the details of the flight to be added.
	 * @return a response containing the details of the added flight.
//	 * @throws InvalidRequestException if the flight request is invalid or a duplicate flight exists.
	 */
	FlightResponse createNew(FlightRequest flightRequest);

	/**
	 * Updates an existing flight in the system.
	 *
	 * @param flightRequest the updated details of the flight.
	 * @return a response containing the updated flight details.
	 */
	FlightResponse updateFlight(FlightUpdateRequest flightRequest);

	/**
	 * Finds a flight based on its arrival and departure locations.
	 *
	 * @param arrivalCity    the arrival location city.
	 * @param departureCity  the departure location city.
	 * @return a response containing the details of the found flight.
	 * @throws InvalidRequestException if no flight is found for the specified route.
	 */
	FlightResponse findFlightByRoute(String arrivalCity, String departureCity) throws InvalidRequestException;

	/**
	 * Counts the total number of flights in the system.
	 *
	 * @return the total flight count.
	 */
	Long getCountOfAllFlights();

}

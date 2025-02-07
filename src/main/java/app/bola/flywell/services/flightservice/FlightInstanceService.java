package app.bola.flywell.services.flightservice;

import app.bola.flywell.basemodules.FlyWellService;
import app.bola.flywell.data.model.enums.FlightStatus;
import app.bola.flywell.dto.request.FlightInstanceRequest;
import app.bola.flywell.dto.response.AircraftResponse;
import app.bola.flywell.dto.response.FlightInstanceResponse;
import app.bola.flywell.exceptions.InvalidRequestException;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * Service interface for managing flight instances.
 */
public interface FlightInstanceService extends FlyWellService<FlightInstanceRequest, FlightInstanceResponse> {

	/**
	 * Finds all flight instances matching the given status.
	 *
	 * @param status the status of the flight instances to fetch (e.g., LANDED, EN_ROUTE, SCHEDULED).
	 * @return a list of flight instance responses matching the given status.
	 */
	List<FlightInstanceResponse> findAllByStatus(@NotNull FlightStatus status);
	

	/**
	 * Updates an existing flight instance with new details.
	 *
	 * @param id the unique identifier of the flight instance to update.
	 * @param flightInstanceRequest the request containing updated details for the flight instance.
	 * @return the updated flight instance response.
	 */
	FlightInstanceResponse updateInstance(@NotNull String id, FlightInstanceRequest flightInstanceRequest);
	
	/**
	 * Cancels a flight instance by its identifier.
	 *
	 * @param id the unique identifier of the flight instance to cancel.
	 */
	void cancelInstance(@NotNull String id);

	
	/**
	 * Checks the availability of seats in the specified flight instance.
	 *
	 * @param id the unique identifier of the flight instance.
	 * @return the number of available seats in the flight instance.
	 */
	int checkSeatAvailability(@NotNull String id);
	
	/**
	 * Reschedules a flight instance to a new departure time.
	 *
	 * @param id the unique identifier of the flight instance to reschedule.
	 * @param newDepartureTime the new departure time for the flight instance.
	 * @return the updated flight instance response after rescheduling.
	 */
	FlightInstanceResponse rescheduleInstance(@NotNull String id, @NotNull String newDepartureTime);

	/**
	 * Removes all flight instances from the system.
	 * <p>
	 * The default behavior can be overridden in implementations if specific cleanup logic is required.
	 */
	default void removeAll() {
		// Default implementation for batch removal
		throw new UnsupportedOperationException("removeAll is not implemented");
	}

    AircraftResponse getAssignedAircraft(String publicId);

    FlightInstanceResponse assignAircraft(String publicId);

    List<FlightInstanceResponse> getAvailableFlights();

	FlightInstanceResponse assignCrewMemberToFlight(String crewMemberId, String flightId) throws InvalidRequestException;

	FlightInstanceResponse viewFlightSchedule(String flightId);
}
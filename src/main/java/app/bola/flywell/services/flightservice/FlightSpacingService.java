package app.bola.flywell.services.flightservice;

import app.bola.flywell.data.model.flight.FlightInstance;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FlightSpacingService {

	/**
	 * Schedules flights while ensuring minimum spacing and avoiding conflicts.
	 * Only returns flights whose departure or arrival times have been updated.
	 */
	public List<FlightInstance> scheduleFlights(List<FlightInstance> flights, int minSpacingMinutes) {

		flights.sort(this::compareFlights);

		List<FlightInstance> scheduledFlights = new ArrayList<>();
		List<FlightInstance> modifiedFlights = new ArrayList<>();

		for (FlightInstance flight : flights) {
			LocalDateTime originalDepartureTime = flight.getDepartureTime();
			LocalDateTime originalArrivalTime = flight.getArrivalTime();

			if (hasConflict(flight, scheduledFlights, minSpacingMinutes)) {
				adjustFlightTimes(flight, scheduledFlights, minSpacingMinutes);
				if (isModified(flight, originalDepartureTime, originalArrivalTime)) {
					modifiedFlights.add(flight);
				}
			}
			scheduledFlights.add(flight);
		}

		return modifiedFlights;
	}

	private int compareFlights(FlightInstance f1, FlightInstance f2) {
		int priorityCompare = Integer.compare(f2.getPriority(), f1.getPriority());
		return (priorityCompare != 0) ? priorityCompare : f1.getDepartureTime().compareTo(f2.getDepartureTime());
	}

	private boolean hasConflict(FlightInstance flight, List<FlightInstance> scheduledFlights, int minSpacingMinutes) {
		return scheduledFlights.stream().anyMatch(scheduled -> isConflict(scheduled, flight, minSpacingMinutes));
	}

	private void adjustFlightTimes(FlightInstance flight, List<FlightInstance> scheduledFlights, int minSpacingMinutes) {
		flight.setDepartureTime(findNextAvailableSlot(scheduledFlights, flight, minSpacingMinutes));
		flight.setArrivalTime(flight.getDepartureTime().plusMinutes(flight.getDurationMinutes()));
	}

	private boolean isModified(FlightInstance flight, LocalDateTime originalDepartureTime, LocalDateTime originalArrivalTime) {
		return !originalDepartureTime.equals(flight.getDepartureTime()) || !originalArrivalTime.equals(flight.getArrivalTime());
	}

	private boolean isConflict(FlightInstance scheduled, FlightInstance newFlight, int minSpacingMinutes) {
		long spacingBefore = Duration.between(scheduled.getArrivalTime(), newFlight.getDepartureTime()).toMinutes();
		return spacingBefore < minSpacingMinutes;
	}

	private LocalDateTime findNextAvailableSlot(List<FlightInstance> scheduledFlights, FlightInstance flight, int minSpacingMinutes) {
		LocalDateTime proposedTime = flight.getDepartureTime();

		for (FlightInstance scheduled : scheduledFlights) {
			long spacing = Duration.between(scheduled.getArrivalTime(), proposedTime).toMinutes();
			if (spacing < minSpacingMinutes) {
				proposedTime = scheduled.getArrivalTime().plusMinutes(minSpacingMinutes);
			}
		}
		return proposedTime;
	}
}

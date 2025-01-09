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
	 */
	public List<FlightInstance> scheduleFlights(List<FlightInstance> flights, int minSpacingMinutes) {
		flights.sort((f1, f2) -> {
			int priorityCompare = Integer.compare(f2.getPriority(), f1.getPriority());
			return (priorityCompare != 0) ? priorityCompare : f1.getDepartureTime().compareTo(f2.getDepartureTime());
		});

		List<FlightInstance> scheduledFlights = new ArrayList<>();
		for (FlightInstance flight : flights) {
			boolean conflict = false;

			for (FlightInstance scheduled : scheduledFlights) {
				if (isConflict(scheduled, flight, minSpacingMinutes)) {
					conflict = true;
					break;
				}
			}

			if (!conflict) {
				scheduledFlights.add(flight);
			} else {
				flight.setDepartureTime(findNextAvailableSlot(scheduledFlights, flight, minSpacingMinutes));
				flight.setArrivalTime(flight.getDepartureTime().plusMinutes(flight.getDurationMinutes()));
				scheduledFlights.add(flight);
			}
		}
		return scheduledFlights;
	}

	private boolean isConflict(FlightInstance scheduled, FlightInstance newFlight, int minSpacingMinutes) {
		long spacingBefore = Duration.between(scheduled.getArrivalTime(), newFlight.getDepartureTime()).toMinutes();
		long spacingAfter = Duration.between(newFlight.getArrivalTime(), scheduled.getDepartureTime()).toMinutes();

		return spacingBefore < minSpacingMinutes || spacingAfter < minSpacingMinutes;
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

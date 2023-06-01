package dtos.Request;

import data.model.Flight;
import data.model.Passenger;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FlightFormRequest {
	private Passenger passenger;
	private Flight flight;
}

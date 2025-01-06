package app.bola.flywell.dtos.request;


import app.bola.flywell.data.model.flight.Flight;
import app.bola.flywell.data.model.Passenger;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FlightFormRequest {
	private Passenger passenger;
	private Flight flight;
}

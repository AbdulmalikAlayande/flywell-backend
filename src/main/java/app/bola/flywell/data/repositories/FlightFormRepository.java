package app.bola.flywell.data.repositories;

import app.bola.flywell.data.model.flight.FlightForm;
import app.bola.flywell.data.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightFormRepository extends JpaRepository<FlightForm, String> {
	List<FlightForm> findAllByPassenger(Passenger passenger);
}

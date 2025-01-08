package app.bola.flywell.data.repositories;

import app.bola.flywell.basemodules.FlyWellRepository;
import app.bola.flywell.data.model.enums.FlightStatus;
import app.bola.flywell.data.model.flight.FlightInstance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightInstanceRepository extends FlyWellRepository<FlightInstance> {

    List<FlightInstance> findByStatus(FlightStatus status);
}

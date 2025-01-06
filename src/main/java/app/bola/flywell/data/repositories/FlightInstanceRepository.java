package app.bola.flywell.data.repositories;

import app.bola.flywell.basemodules.FlyWellRepository;
import app.bola.flywell.data.model.enums.FlightStatus;
import app.bola.flywell.data.model.flight.FlightInstance;

import java.util.List;
import java.util.Optional;

public interface FlightInstanceRepository extends FlyWellRepository<FlightInstance> {

    List<FlightInstance> findByStatus(FlightStatus status);

    Optional<FlightInstance> findByPublicId(String publicId);
}

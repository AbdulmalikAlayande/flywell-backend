package app.bola.flywell.data.repositories;

import app.bola.flywell.basemodules.FlyWellRepository;
import app.bola.flywell.data.model.flight.Flight;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;


@RepositoryRestResource(path = "flights")
public interface FlightRepository extends FlyWellRepository<Flight> {

	@Override
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<Flight> findByPublicId(String publicId);

}

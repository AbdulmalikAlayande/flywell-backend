package app.bola.flywell.data.repositories;

import app.bola.flywell.data.model.enums.FlightStatus;
import app.bola.flywell.data.model.flight.FlightInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface FlightInstanceRepository extends JpaRepository<FlightInstance, String> {
	
	@Query(
       """
       select f from FlightInstance f
       where f.status = :status
       and f.arrivalDate = :arrivalDate
       and f.departureDate = :departureDate
       """
	)
	List<FlightInstance> findAvailableInstances(@Param("status") FlightStatus status,
												@Param("arrivalDate")LocalDate arrivalDate,
												@Param("departureDate") LocalDate departureDate);
	List<FlightInstance> findByStatus(FlightStatus status);
}

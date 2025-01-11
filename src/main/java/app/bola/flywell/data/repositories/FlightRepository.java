package app.bola.flywell.data.repositories;

import app.bola.flywell.basemodules.FlyWellRepository;
import app.bola.flywell.data.model.flight.Flight;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;


@RepositoryRestResource(path = "flights")
public interface FlightRepository extends FlyWellRepository<Flight> {

	@Query("""
        select f from Flight f
        where f.arrivalAirport.name = :arrivalAirportName
        and f.departureAirport.name = :departureAirportName
        """)
	Optional<Flight> findByArrivalAndDepartureAirport(@Param("arrivalAirportName") String arrivalAirportName,
	                                                          @Param("departureAirportName") String departureAirportName);
	
	Optional<Flight> findByArrivalCityAndDepartureCity(String arrivalCity, String departureCity);
	
	boolean existsByArrivalCityAndDepartureCity(String arrivalCity, String departureCity);
}

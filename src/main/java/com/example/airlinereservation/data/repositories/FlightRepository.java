package com.example.airlinereservation.data.repositories;

import com.example.airlinereservation.data.model.flight.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, String> {
	
	
	@Query("""
        select f from Flight f
        where f.arrivalAirport.airportName = :arrivalAirportName
        and f.departureAirport.airportName = :departureAirportName
        """)
	Optional<Flight> findByArrivalAndDepartureAirport(@Param("arrivalAirportName") String arrivalAirportName,
	                                                          @Param("departureAirportName") String departureAirportName);
	
}

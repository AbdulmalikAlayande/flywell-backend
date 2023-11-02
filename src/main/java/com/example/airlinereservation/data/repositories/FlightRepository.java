package com.example.airlinereservation.data.repositories;

import com.example.airlinereservation.data.model.enums.Destinations;
import com.example.airlinereservation.data.model.flight.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, String > {
	
	@Query("""
          SELECT f FROM Flight f
          WHERE f.arrivalAirport.airportLocation = :arrivalState
          and f.departureAirport.airportLocation = :departureState
          """)
	Optional<Flight> findByArrivalAndDepartureAirportLocation(@Param("arrivalState") Destinations arrivalState, @Param("departureState") Destinations departureState);
}

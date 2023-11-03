package com.example.airlinereservation.data.repositories;

import com.example.airlinereservation.data.model.enums.Destinations;
import com.example.airlinereservation.data.model.enums.FlightStatus;
import com.example.airlinereservation.data.model.flight.FlightInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FlightInstanceRepository extends JpaRepository<FlightInstance, String> {
	
	@Query("""
         select f from FlightInstance f
         where f.flight.arrivalAirport.airportLocation = :arrivalState
         and f.movementStatus = :flightStatus
         """)
	void findByFlightDestinationAndFlightMovementStatus(@Param("arrivalState") Destinations arrivalState,
	                                                    @Param("flightStatus") FlightStatus flightStatus);
}

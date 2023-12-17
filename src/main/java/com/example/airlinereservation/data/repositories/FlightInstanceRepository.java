package com.example.airlinereservation.data.repositories;

import com.example.airlinereservation.data.model.enums.FlightStatus;
import com.example.airlinereservation.data.model.flight.FlightInstance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightInstanceRepository extends JpaRepository<FlightInstance, String> {
	
	List<FlightInstance> findByStatus(FlightStatus status);
}

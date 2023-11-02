package com.example.airlinereservation.data.repositories;

import com.example.airlinereservation.data.model.flight.FlightInstance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightInstanceRepository extends JpaRepository<FlightInstance, String> {

}

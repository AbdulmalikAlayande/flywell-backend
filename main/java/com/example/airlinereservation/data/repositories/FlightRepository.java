package com.example.airlinereservation.data.repositories;

import com.example.airlinereservation.data.model.flight.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, String > {

}

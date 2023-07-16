package com.example.airlinereservation.data.repositories;

import com.example.airlinereservation.data.model.FlightForm;
import com.example.airlinereservation.data.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightFormRepository extends JpaRepository<FlightForm, String> {
	List<FlightForm> findAllByPassenger(Passenger passenger);
}

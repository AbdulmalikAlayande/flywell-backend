package com.example.airlinereservation.data.repositories;

import com.example.airlinereservation.data.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, String> {
}

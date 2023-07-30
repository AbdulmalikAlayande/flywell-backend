package com.example.airlinereservation.data.repositories;

import com.example.airlinereservation.data.model.AirCraft;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirCraftsRepo extends JpaRepository<AirCraft, String> {

}

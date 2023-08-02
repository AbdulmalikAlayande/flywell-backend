package com.example.airlinereservation.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FlightSeat extends Seat{
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String reservationNumber;
}

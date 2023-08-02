package com.example.airlinereservation.data.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.List;

public class Airline {
	
	@OneToOne
	private AirCraft airCraft;
	@OneToMany(cascade = CascadeType.DETACH)
	private List<Passenger> passengers;
	
}

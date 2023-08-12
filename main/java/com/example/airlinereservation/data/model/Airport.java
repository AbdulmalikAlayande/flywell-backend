package com.example.airlinereservation.data.model;

import com.example.airlinereservation.data.model.flight.Flight;
import com.example.airlinereservation.data.model.persons.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Airport {
	
	private String name;
	private String code;
	private Address address;
	private List<Airline> airlines;
	private List<Flight> flights;
}

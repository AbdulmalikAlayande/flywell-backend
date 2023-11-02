package com.example.airlinereservation.data.model;

import com.example.airlinereservation.data.model.enums.Destinations;
import com.example.airlinereservation.data.model.flight.Flight;
import com.example.airlinereservation.data.model.persons.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Airport {
	@Id
	@GeneratedValue(strategy = UUID)
	private String id;
	private String name;
	private String code;
	private String airportAddress;
	@Enumerated(value = STRING)
	private Destinations airportLocation;
}

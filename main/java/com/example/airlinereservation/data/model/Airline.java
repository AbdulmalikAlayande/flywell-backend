package com.example.airlinereservation.data.model;

import com.example.airlinereservation.data.model.persons.CrewMember;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Airline {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String name;
	private String code;
	@OneToOne
	private AirCraft airCraft;
	@OneToMany
	private List<Flight> flight;
	@OneToMany
	private List<CrewMember> crewMembers;
}

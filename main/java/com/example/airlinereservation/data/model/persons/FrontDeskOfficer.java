package com.example.airlinereservation.data.model.persons;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class FrontDeskOfficer extends Person{
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
}

package com.example.airlinereservation.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Passenger {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String token;
	private LocalDate lastLoggedIn;
	private boolean expiredToken;
	@OneToOne(cascade = CascadeType.ALL)
	private UserBioData userBioData;
}

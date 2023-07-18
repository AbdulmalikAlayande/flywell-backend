package com.example.airlinereservation.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Passenger {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@OneToOne(cascade = CascadeType.ALL)
	private UserBioData userBioData;
//	private String firstName;
//	private String lastName;
//	private String fullName = firstName + lastName;
//	private String username;
//	private String Email;
//	private String phoneNumber;
//	private String password;
}

package com.example.airlinereservation.data.model;

import com.example.airlinereservation.data.model.enums.Gender;
import com.example.airlinereservation.data.model.persons.UserBioData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Passenger {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String fullName;
	private Gender gender;
	private Date dateOfBirth;
	private String token;
	private Byte passport;
	private String passportUrl;
	private LocalDate lastLoggedIn;
	private boolean expiredToken;
	private boolean loggedIn;
	@OneToOne(cascade = CascadeType.ALL)
	private UserBioData userBioData;
}

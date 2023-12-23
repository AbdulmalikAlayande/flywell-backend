package com.example.airlinereservation.data.model.persons;

import com.example.airlinereservation.data.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.CascadeType.ALL;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Admin extends Person{
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@Enumerated(EnumType.STRING)
	private Role role;
	private String adminCode;
	private String email;
	@OneToOne(cascade = ALL)
	private UserBioData bioData;
}

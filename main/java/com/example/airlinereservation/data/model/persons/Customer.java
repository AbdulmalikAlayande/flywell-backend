package com.example.airlinereservation.data.model.persons;

import com.example.airlinereservation.data.model.UserBioData;
import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Customer extends Person{
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private Long frequentFlyerNumber;
	@OneToOne
	private UserBioData bioData;
	
}

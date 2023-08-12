package com.example.airlinereservation.data.model.persons;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserBioData {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@NotBlank
	private String firstName;
	@NotEmpty
	private String lastName;
	@NotEmpty
	private String fullName;
	@Column(unique = true)
	@NotBlank
	private String userName;
	@NotBlank
	@Column(unique = true)
	private String email;
	@NotBlank
	private String phoneNumber;
	@NotBlank
	private String password;
	@OneToOne
	private Address address;
	
	public String getFullName(){
		return firstName + " " + lastName;
	}
}

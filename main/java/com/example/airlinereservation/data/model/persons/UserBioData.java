package com.example.airlinereservation.data.model.persons;

import com.example.airlinereservation.data.model.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import static jakarta.persistence.EnumType.STRING;

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
	@NotBlank
	private String password;
	@Column(unique = true)
	@NotBlank
	private String userName;
	@NotBlank
	@Column(unique = true, nullable = false)
	@NaturalId(mutable = true)
	private String email;
	@NotBlank
	private String phoneNumber;
	@NotEmpty
	private String fullName;
	@OneToOne
	private Address address;
	@Enumerated(STRING)
	private Gender gender;
}

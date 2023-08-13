package com.example.airlinereservation.data.model.persons;

import com.example.airlinereservation.data.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
public class CrewMember extends Person{
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@Enumerated(EnumType.STRING)
	private Role role;
	@Column(unique = true, nullable = false)
	private UUID departmentId = UUID.randomUUID();
	//@OneToOne
	//private UserBioData bioData;
	private boolean available;
	@NotBlank
	private String password;
	@NotBlank
	@Column(unique = true)
	private String userName;
	@NotBlank
	private String email;
	@NotBlank
	private String phoneNumber;
	@NotBlank
	private String firstName;
	@NotEmpty
	private String lastName;
	private String fullName;
	private String country;
	private String state;
	private String postalCode;
	private String streetName;
	private String streetNumber;
	private String houseNumber;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CrewMember that)) return false;
		if (!super.equals(o)) return false;
		return Objects.equals(getDepartmentId(), that.getDepartmentId()) && Objects.equals(getUserName(), that.getUserName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getDepartmentId(), getUserName());
	}
}

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
	@OneToOne
	private UserBioData bioData;
	private boolean available;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CrewMember that)) return false;
		if (!super.equals(o)) return false;
		return Objects.equals(getDepartmentId(), that.getDepartmentId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getDepartmentId());
	}
}

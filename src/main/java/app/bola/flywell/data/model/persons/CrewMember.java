package app.bola.flywell.data.model.persons;

import app.bola.flywell.data.model.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
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
	@Builder.Default
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

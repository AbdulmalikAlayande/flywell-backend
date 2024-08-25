package app.bola.flywell.data.model.persons;

import app.bola.flywell.data.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class FrontDeskOfficer extends Person{
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@Enumerated(EnumType.STRING)
	private Role role;
	@OneToOne
	private UserBioData bioData;
}

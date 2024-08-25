package app.bola.flywell.data.model.persons;

import app.bola.flywell.data.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.CascadeType.MERGE;

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
	@Enumerated(EnumType.STRING)
	private Role role;
	@OneToOne(cascade = MERGE)
	private UserBioData bioData;
	private LocalDate lastLoggedIn;
	private boolean expiredToken;
	private boolean loggedIn;
	private String token;
	
}

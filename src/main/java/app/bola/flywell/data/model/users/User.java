package app.bola.flywell.data.model.users;

import app.bola.flywell.basemodules.FlyWellModel;
import app.bola.flywell.data.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class User extends FlyWellModel {

	private String email;
	private String password;
	private String refreshToken;

	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToOne(cascade = ALL)
	private UserBioData bioData;

	@ManyToMany
	private Set<app.bola.flywell.security.models.Role> roles = new HashSet<>();
}

package app.bola.flywell.data.model.users;

import app.bola.flywell.basemodules.FlyWellModel;
import app.bola.flywell.data.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.CascadeType.ALL;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class User extends FlyWellModel {

	@Enumerated(EnumType.STRING)
	private Role role;
	@OneToOne(cascade = ALL)
	private UserBioData bioData;
}

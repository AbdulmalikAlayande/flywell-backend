package app.bola.flywell.data.model.users;

import app.bola.flywell.basemodules.FlyWellModel;
import app.bola.flywell.data.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.CascadeType.ALL;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Customer extends FlyWellModel {

	private Long frequentFlyerNumber;

	@Builder.Default
	@Enumerated(EnumType.STRING)
	private Role role = Role.USER;

	@OneToOne(cascade = ALL, orphanRemoval = true)
	private UserBioData bioData;
}

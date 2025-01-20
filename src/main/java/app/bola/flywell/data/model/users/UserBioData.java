package app.bola.flywell.data.model.users;

import app.bola.flywell.basemodules.FlyWellModel;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserBioData extends FlyWellModel {

	private String firstName;

	private String lastName;

	private String password;

	private String email;

	private String phoneNumber;

	@OneToMany(cascade = CascadeType.ALL)
	@Builder.Default
	private List<Otp> Otps = new ArrayList<>();

}

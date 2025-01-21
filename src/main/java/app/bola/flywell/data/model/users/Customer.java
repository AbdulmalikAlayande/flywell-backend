package app.bola.flywell.data.model.users;

import app.bola.flywell.basemodules.FlyWellModel;
import app.bola.flywell.security.models.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Customer extends FlyWellModel {

	private String email;
	private String password;
	private String lastName;
	private String firstName;
	private String phoneNumber;

	@OneToMany(cascade = CascadeType.ALL)
	@Builder.Default
	private List<Otp> Otps = new ArrayList<>();

	@ManyToMany
	private Set<Role> roles = new HashSet<>();

	@OneToOne(cascade = ALL, orphanRemoval = true)
	private UserBioData bioData;


	public void addOtp(Otp otp) {
		if(otp != null){
			Otps.add(otp);
		}
	}
}

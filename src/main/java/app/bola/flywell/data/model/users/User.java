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

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class User extends FlyWellModel {

	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String password;
	private String lastName;
	private String firstName;
	private String phoneNumber;
	private boolean active;
	private String refreshToken;

	@ManyToMany
	@Builder.Default
	private Set<Role> roles = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL)
	@Builder.Default
	private List<Otp> Otps = new ArrayList<>();


	public void addOtp(Otp otp) {
		if(otp != null){
			Otps.add(otp);
		}
	}
}

/*

*/
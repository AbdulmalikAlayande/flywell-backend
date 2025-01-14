package app.bola.flywell.data.model.persons;

import app.bola.flywell.basemodules.FlyWellModel;
import app.bola.flywell.data.model.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;

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

	@OneToOne
	private Address address;

	@Enumerated(STRING)
	private Gender gender;

	@OneToMany(cascade = CascadeType.ALL)
	@Builder.Default
	private List<OTP> OTPs = new ArrayList<>();

	public void addOtp(OTP otp) {
		if(otp != null){
			OTPs.add(otp);
		}
	}
}

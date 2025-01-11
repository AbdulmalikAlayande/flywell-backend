package app.bola.flywell.data.model;

import app.bola.flywell.basemodules.FlyWellModel;
import app.bola.flywell.data.model.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Passenger extends FlyWellModel {

	private String firstname;
	private String lastname;
	private String passportUrl;
	private String passportNumber;
	private Gender gender;
	private Date dateOfBirth;
}

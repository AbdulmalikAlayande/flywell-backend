package app.bola.flywell.data.model;

import app.bola.flywell.data.model.enums.Gender;
import app.bola.flywell.data.model.persons.UserBioData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Passenger implements Serializable, Cloneable {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String fullName;
	private Gender gender;
	private Date dateOfBirth;
	private String token;
	private Byte passport;
	private String passportUrl;
	private LocalDate lastLoggedIn;
	private boolean expiredToken;
	private boolean loggedIn;
	@OneToOne(cascade = CascadeType.ALL)
	private UserBioData userBioData;
	
	@Override
	public Passenger clone() {
		try {
			// TODO: copy mutable state here, so the clone can't change the internals of the original
			return (Passenger) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
	
	@Override
	public boolean equals(Object object) {
		if (this == object) return true;
		if (!(object instanceof Passenger passenger)) return false;
		return isExpiredToken() == passenger.isExpiredToken() && isLoggedIn() == passenger.isLoggedIn() && Objects.equals(getId(), passenger.getId()) && Objects.equals(getFullName(), passenger.getFullName()) && getGender() == passenger.getGender() && Objects.equals(getDateOfBirth(), passenger.getDateOfBirth()) && Objects.equals(getToken(), passenger.getToken()) && Objects.equals(getPassport(), passenger.getPassport()) && Objects.equals(getPassportUrl(), passenger.getPassportUrl()) && Objects.equals(getLastLoggedIn(), passenger.getLastLoggedIn()) && Objects.equals(getUserBioData(), passenger.getUserBioData());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getId(), getFullName(), getGender(), getDateOfBirth(), getToken(), getPassport(), getPassportUrl(), getLastLoggedIn(), isExpiredToken(), isLoggedIn(), getUserBioData());
	}
}

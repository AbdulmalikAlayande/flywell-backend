package app.bola.flywell.data.model.persons;

import app.bola.flywell.validator.EmailPattern;
import app.bola.flywell.data.model.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

import java.util.List;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class UserBioData {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@Valid
	@NotBlank(message = "This Field Cannot Be Empty Or Blank")
	@Size(max = 15, message = "This Field Should Not Contain More Than 15 Characters")
	private String firstName;
	@Valid
	@NotBlank(message = "This Field Cannot Be Empty Or Blank")
	@Size(max = 15, message = "This Field Should Not Contain More Than 15 Characters")
	private String lastName;
	@Valid
	@NotBlank(message = "This Field Cannot Be Empty Or Blank")
	@Size(max = 15, min = 8, message = "Invalid Password Length: Password length must be between 8 and 15 characters")
	private String password;
	@Valid
	@NotBlank(message = "This Field Cannot Be Empty Or Blank")
	@Column(unique = true, nullable = false)
	@NaturalId(mutable = true)
	@Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid Email: Email Must Match The Format Specified")
	@EmailPattern
	private String email;
	@Valid
	@NotBlank(message = "This Field Cannot Be Empty Or Blank")
	private String phoneNumber;
	@OneToOne
	private Address address;
	@Enumerated(STRING)
	private Gender gender;
	@OneToMany(cascade = CascadeType.ALL)
	private List<OTP> OTPs;
}

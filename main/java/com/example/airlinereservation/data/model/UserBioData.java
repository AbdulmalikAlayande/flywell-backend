package com.example.airlinereservation.data.model;

import com.example.airlinereservation.config.mycustomannotations.EmailPattern;
import com.example.airlinereservation.config.mycustomannotations.ValidEmailDomain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

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
	@NotBlank
	private String firstName;
	@NotEmpty
	private String lastName;
	@NotEmpty
	private String fullName;
	@Column(unique = true)
	@NotBlank
	private String userName;
	@NotBlank
	@Column(unique = true)
	@Email(message = "Please enter a valid email format",
			regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
			groups = {ValidEmailDomain.class})
	@EmailPattern(groups = ValidEmailDomain.class)
	private String email;
	@NotBlank
	private String phoneNumber;
	@NotBlank
	private String password;
	
	public String getFullName(){
		return firstName + " " + lastName;
	}
}

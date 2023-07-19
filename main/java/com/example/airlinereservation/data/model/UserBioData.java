package com.example.airlinereservation.data.model;

import com.example.airlinereservation.utils.mycustomannotations.EmailPattern;
import com.example.airlinereservation.utils.mycustomannotations.ValidEmailDomain;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
			groups = {ValidEmailDomain.class, ValidationMode.class})
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

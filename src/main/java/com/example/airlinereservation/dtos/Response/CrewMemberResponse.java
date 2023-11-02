package com.example.airlinereservation.dtos.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor

public class CrewMemberResponse {
    private String password;
    private String userName;
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String fullName;
    private String country;
    private String state;
    private String postalCode;
    private String streetName;
    private String streetNumber;
    private String houseNumber;

}

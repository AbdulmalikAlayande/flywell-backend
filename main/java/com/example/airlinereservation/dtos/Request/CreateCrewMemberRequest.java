package com.example.airlinereservation.dtos.Request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateCrewMemberRequest {
    @NotBlank
    private String password;
    @NotBlank
    private String userName;
    @NotBlank
    private String email;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String firstName;
    @NotEmpty
    private String lastName;
    private String fullName;
    private String country;
    private String state;
    private String postalCode;
    private String streetName;
    private String streetNumber;
    private String houseNumber;

}


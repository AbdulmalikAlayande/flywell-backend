package com.example.airlinereservation.dtos.Request;

import com.example.airlinereservation.data.model.Address;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class CreateAdminRequest {
    private String email;
    @NotBlank
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotBlank
    private String userName;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String password;
    @NotBlank
    private String fullName;
    private String country;
    private String state;
    private String postalCode;
    private String streetName;
    private String streetNumber;
    private String houseNumber;


}

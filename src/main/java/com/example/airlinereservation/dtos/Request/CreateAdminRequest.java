package com.example.airlinereservation.dtos.Request;

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
    private String phoneNumber;
    @NotBlank
    private String password;


}

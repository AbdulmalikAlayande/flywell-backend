package com.example.airlinereservation.dtos.Request;

import com.example.airlinereservation.data.model.enums.Role;
import jakarta.persistence.*;
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
    
}


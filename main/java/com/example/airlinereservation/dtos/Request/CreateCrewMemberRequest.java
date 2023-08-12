package com.example.airlinereservation.dtos.Request;

import com.example.airlinereservation.data.model.enums.Role;
import com.example.airlinereservation.data.model.persons.UserBioData;
import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter


public class CreateCrewMemberRequest {
    private Role role;
    private UserBioData bioData;
    private boolean available;
}


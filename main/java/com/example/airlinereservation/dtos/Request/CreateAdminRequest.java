package com.example.airlinereservation.dtos.Request;

import com.example.airlinereservation.data.model.UserBioData;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class CreateAdminRequest  {
    private String  email;

}

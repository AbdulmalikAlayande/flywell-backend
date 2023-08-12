package com.example.airlinereservation.services.userservice;

import com.example.airlinereservation.dtos.Request.CreateCrewMemberRequest;
import com.example.airlinereservation.dtos.Response.CreateCrewMemberResponse;

public interface CrewMemberService {
    CreateCrewMemberResponse createCrewMember(CreateCrewMemberRequest createCrewMemberRequest);

}

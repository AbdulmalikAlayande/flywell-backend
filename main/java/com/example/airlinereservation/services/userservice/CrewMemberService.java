package com.example.airlinereservation.services.userservice;

import com.example.airlinereservation.dtos.Request.CreateCrewMemberRequest;
import com.example.airlinereservation.dtos.Response.CreateCrewMemberResponse;
import com.example.airlinereservation.utils.exceptions.InvalidRequestException;

import java.util.OptionalDouble;

public interface CrewMemberService {
    CreateCrewMemberResponse createCrewMember(CreateCrewMemberRequest createCrewMemberRequest);

    void deleteCrewMemberById(String id) throws InvalidRequestException;

    // viewFlightSchedule()
}

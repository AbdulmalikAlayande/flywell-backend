package com.example.airlinereservation.services.userservice;

import com.example.airlinereservation.data.model.enums.Destinations;
import com.example.airlinereservation.data.model.persons.CrewMember;
import com.example.airlinereservation.dtos.Response.CreateCrewMemberResponse;
import com.example.airlinereservation.dtos.Response.CrewMemberResponse;

import java.util.List;

public interface CrewMemberManagementService {

    CreateCrewMemberResponse addCrewMemberToDepartment(CrewMember crewMember);
    void removeCrewMemberFromDepartment(CrewMember crewMember);

    List<CrewMember> getAvailableCrewMembersBy(Destinations location, boolean availability);
    CrewMemberResponse assignCrewMember(CrewMember crewMember, Destinations location);
    List<CrewMemberResponse> assignCrewMembers(int sizeOfCrewMembers, Destinations location);
}

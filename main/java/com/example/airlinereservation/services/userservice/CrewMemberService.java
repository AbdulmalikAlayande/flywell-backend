package com.example.airlinereservation.services.userservice;

import com.example.airlinereservation.data.model.flight.FlightInstance;
import com.example.airlinereservation.data.model.persons.CrewMember;
import com.example.airlinereservation.dtos.Request.CreateCrewMemberRequest;
import com.example.airlinereservation.dtos.Response.CreateCrewMemberResponse;
import com.example.airlinereservation.utils.exceptions.InvalidRequestException;

import java.util.Optional;
import java.util.OptionalDouble;

public interface CrewMemberService {
    CreateCrewMemberResponse createCrewMember(CreateCrewMemberRequest createCrewMemberRequest);
    void deleteCrewMemberById(String id) throws InvalidRequestException;
    FlightInstance assignCrewMember(FlightInstance flightInstance);
    void deleteCrewMemberByUsername(String userName) throws InvalidRequestException;

    long getCountOfCrewMembers();
    
    boolean existsByUsername(String userName);

    Optional<CrewMember> findCrewMemberByUserName(String userName) throws InvalidRequestException;

//viewFlightSchedule()
//test that the location of the crew members to be assigned is the location where the flight instance is coming from
/*test that the flight instance does not have the crew members assigned to it yet, before assignment and after assignment
  the flight instance now has a number of crew members assigned to it.*/
//    test that the crew members to be assigned are available and are not assigned to a flight yet
}

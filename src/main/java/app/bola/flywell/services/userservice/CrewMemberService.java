package app.bola.flywell.services.userservice;

import app.bola.flywell.data.model.flight.FlightInstance;
import app.bola.flywell.data.model.persons.CrewMember;
import app.bola.flywell.dto.response.CreateCrewMemberResponse;
import app.bola.flywell.dto.response.CrewMemberResponse;
import app.bola.flywell.dto.response.FlightScheduleResponse;
import app.bola.flywell.dto.request.*;
import app.bola.flywell.exceptions.*;
import java.util.Optional;

public interface CrewMemberService {
    CreateCrewMemberResponse createCrewMember(CreateCrewMemberRequest createCrewMemberRequest) throws EmptyFieldException, FieldInvalidException;
    void deleteCrewMemberById(String id) throws InvalidRequestException;
    FlightInstance assignCrewMember(FlightInstance instance);
    void deleteCrewMemberByUsername(String userName) throws InvalidRequestException;

    long getCountOfCrewMembers();
    
    boolean existsByUsername(String userName);

    Optional<CrewMember> findCrewMemberByUserName(String userName) throws InvalidRequestException;

    CrewMemberResponse updateDetailsOfRegisteredCrewMember(UpdateRequest updateRequest);
    FlightScheduleResponse viewFlightSchedule(ViewFlightScheduleRequest flightScheduleRequest);
    
//test that the location of the crew members to be assigned is the location where the flight instance is coming from
/*test that the flight instance does not have the crew members assigned to it yet, before assignment and after assignment
  the flight instance now has a number of crew members assigned to it.*/
//    test that the crew members to be assigned are available and are not assigned to a flight yet
}

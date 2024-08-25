package app.bola.flywell.services.userservice;

import app.bola.flywell.data.model.enums.Destinations;
import app.bola.flywell.data.model.persons.CrewMember;
import app.bola.flywell.dtos.Response.CreateCrewMemberResponse;
import app.bola.flywell.dtos.Response.CrewMemberResponse;

import java.util.List;

public interface CrewMemberManagementService {

    CreateCrewMemberResponse addCrewMemberToDepartment(CrewMember crewMember);
    void removeCrewMemberFromDepartment(CrewMember crewMember);

    List<CrewMember> getAvailableCrewMembersBy(Destinations location, boolean availability);
    CrewMemberResponse assignCrewMember(CrewMember crewMember, Destinations location);
    List<CrewMemberResponse> assignCrewMembers(int sizeOfCrewMembers, Destinations location);
}

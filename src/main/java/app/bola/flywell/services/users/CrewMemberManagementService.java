package app.bola.flywell.services.users;

import app.bola.flywell.data.model.enums.Destinations;
import app.bola.flywell.data.model.persons.CrewMember;
import app.bola.flywell.dto.response.CreateCrewMemberResponse;
import app.bola.flywell.dto.response.CrewMemberResponse;

import java.util.List;

public interface CrewMemberManagementService {

    CreateCrewMemberResponse addCrewMemberToDepartment(CrewMember crewMember);
    void removeCrewMemberFromDepartment(CrewMember crewMember);

    List<CrewMember> getAvailableCrewMembersBy(Destinations location, boolean availability);
    CrewMemberResponse assignCrewMember(CrewMember crewMember, Destinations location);
    List<CrewMemberResponse> assignCrewMembers(int sizeOfCrewMembers, Destinations location);
}

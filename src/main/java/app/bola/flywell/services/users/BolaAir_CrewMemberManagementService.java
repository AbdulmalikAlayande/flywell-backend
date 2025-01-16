package app.bola.flywell.services.users;

import app.bola.flywell.data.model.enums.Destinations;
import app.bola.flywell.data.model.persons.CrewMember;
import app.bola.flywell.dto.response.CreateCrewMemberResponse;
import app.bola.flywell.dto.response.CrewMemberResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class BolaAir_CrewMemberManagementService implements CrewMemberManagementService{

    private final Set<CrewMember> crewMembersDepartment = new HashSet<>();

    @Override
    public CreateCrewMemberResponse addCrewMemberToDepartment(CrewMember crewMember) {
        crewMember.setAvailable(true);
        crewMembersDepartment.add(crewMember);
        CreateCrewMemberResponse response = new CreateCrewMemberResponse();
        response.setDepartmentId(crewMember.getDepartmentId().toString());
        return response;
    }

    @Override
    public void removeCrewMemberFromDepartment(CrewMember crewMember) {
        crewMembersDepartment.removeIf(member-> member.equals(crewMember));
    }

    @Override
    public List<CrewMember> getAvailableCrewMembersBy(Destinations location, boolean availability) {
        return null;
    }
    
    @Override
    public CrewMemberResponse assignCrewMember(CrewMember crewMember, Destinations location) {
        return null;
    }
    
    @Override
    public List<CrewMemberResponse> assignCrewMembers(int sizeOfCrewMembers, Destinations location) {
        return null;
    }
    
    
}

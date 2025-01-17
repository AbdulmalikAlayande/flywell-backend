package app.bola.flywell.services.users;

import app.bola.flywell.basemodules.FlyWellService;
import app.bola.flywell.data.model.flight.FlightInstance;
import app.bola.flywell.dto.response.FlightInstanceResponse;
import app.bola.flywell.dto.request.*;

public interface CrewMemberService extends FlyWellService<UserRequest, UserRequest> {

    FlightInstance assignCrewMember(FlightInstance instance);
    FlightInstanceResponse viewFlightSchedule(String flightId);

//test that the location of the crew members to be assigned is the location where the flight instance is coming from
/*test that the flight instance does not have the crew members assigned to it yet, before assignment and after assignment
  the flight instance now has a number of crew members assigned to it.*/
//    test that the crew members to be assigned are available and are not assigned to a flight yet
}

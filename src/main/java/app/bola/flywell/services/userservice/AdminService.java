package app.bola.flywell.services.userservice;

import app.bola.flywell.dtos.Request.*;
import app.bola.flywell.dtos.Response.*;
import app.bola.flywell.exceptions.*;


public interface AdminService {

    CreateAdminResponse createAdminAccount(CreateAdminRequest createAdminRequest) throws FailedRegistrationException;
	AdminInvitationResponse inviteAdmin(AdminInvitationRequest invitationRequest) throws InvalidRequestException, FieldInvalidException, EmptyFieldException;
    CreateCrewMemberResponse addCrewMember(CreateCrewMemberRequest createCrewMemberRequest) throws EmptyFieldException, IllegalAccessException, FieldInvalidException;
    GetUserResponse findByEmail(String email);
	FlightResponse addNewFlight(FlightRequest flightRequest) throws InvalidRequestException;
	
	void deleteAll();
}
 
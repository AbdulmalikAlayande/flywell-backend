package app.bola.flywell.services.users;

import app.bola.flywell.dto.request.*;
import app.bola.flywell.dto.response.*;
import app.bola.flywell.exceptions.*;


public interface AdminService {

    CreateAdminResponse createNew(CreateAdminRequest createAdminRequest) throws FailedRegistrationException;
	AdminInvitationResponse inviteAdmin(AdminInvitationRequest invitationRequest) throws InvalidRequestException, FieldInvalidException, EmptyFieldException;
	FlightResponse addNewFlight(FlightRequest flightRequest) throws InvalidRequestException;
	
	void deleteAll();
}
 
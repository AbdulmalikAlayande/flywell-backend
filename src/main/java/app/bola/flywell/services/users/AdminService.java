package app.bola.flywell.services.users;

import app.bola.flywell.basemodules.FlyWellService;
import app.bola.flywell.dto.request.*;
import app.bola.flywell.dto.response.*;
import app.bola.flywell.exceptions.*;


public interface AdminService extends FlyWellService<CreateAdminRequest, AdminResponse> {

	AdminInvitationResponse inviteAdmin(AdminInvitationRequest invitationRequest) throws InvalidRequestException, FieldInvalidException, EmptyFieldException;

}
 
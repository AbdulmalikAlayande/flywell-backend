package app.bola.flywell.controllers;

import app.bola.flywell.basemodules.FlyWellController;
import app.bola.flywell.dto.request.*;
import app.bola.flywell.dto.response.AdminInvitationResponse;
import app.bola.flywell.dto.response.AdminResponse;
import app.bola.flywell.exceptions.*;
import app.bola.flywell.services.users.AdminService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("admin")
@AllArgsConstructor
public class AdminController implements FlyWellController<CreateAdminRequest, AdminResponse> {
	
	private AdminService adminService;
	
	@PostMapping("invite-admin")
	@PreAuthorize("hasRole('ADMIN') and hasAuthority('INVITE_ADMIN')")
	public ResponseEntity<AdminInvitationResponse> inviteAdmin(@Valid @RequestBody AdminInvitationRequest invitationRequest) throws FieldInvalidException, InvalidRequestException, EmptyFieldException {
		AdminInvitationResponse invitationResponse = adminService.inviteAdmin(invitationRequest);
		return ResponseEntity.status(HttpStatus.OK).body(invitationResponse);
	}

	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<AdminResponse> createNew(@Valid @RequestBody CreateAdminRequest createAdminRequest){
		AdminResponse response = adminService.createNew(createAdminRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@Override
	public ResponseEntity<AdminResponse> findByPublicId(String publicId) {
		return null;
	}

	@Override
	public ResponseEntity<Collection<AdminResponse>> findAll() {
		return null;
	}

	@Override
	public ResponseEntity<Collection<AdminResponse>> findAll(Pageable pageable) {
		return null;
	}
}

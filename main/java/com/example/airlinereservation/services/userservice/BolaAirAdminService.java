package com.example.airlinereservation.services.userservice;

import com.example.airlinereservation.dtos.Request.AdminInvitationRequest;
import com.example.airlinereservation.dtos.Request.CreateAdminRequest;
import com.example.airlinereservation.dtos.Response.AdminInvitationResponse;
import com.example.airlinereservation.dtos.Response.CreateAdminResponse;
import org.springframework.stereotype.Service;

@Service
public class BolaAirAdminService implements AdminService{
	
	@Override
	public CreateAdminResponse createAdmin(CreateAdminRequest createAdminRequest) {
		return null;
	}
	
	@Override
	public AdminInvitationResponse inviteAdmin(AdminInvitationRequest invitationRequest) {
		return null;
	}
}

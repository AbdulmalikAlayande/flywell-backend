package com.example.airlinereservation.services.userservice;

import com.example.airlinereservation.data.model.persons.UserBioData;
import com.example.airlinereservation.dtos.Request.AdminInvitationRequest;
import com.example.airlinereservation.dtos.Request.CreateAdminRequest;
import com.example.airlinereservation.dtos.Response.AdminInvitationResponse;
import com.example.airlinereservation.dtos.Response.CreateAdminResponse;
import com.example.airlinereservation.utils.exceptions.FailedRegistrationException;

import java.util.Optional;

public interface AdminService {

    CreateAdminResponse createAdmin(CreateAdminRequest createAdminRequest) throws FailedRegistrationException;
	AdminInvitationResponse inviteAdmin(AdminInvitationRequest invitationRequest);

    Optional<UserBioData> findByUsername(String userName);
}
 
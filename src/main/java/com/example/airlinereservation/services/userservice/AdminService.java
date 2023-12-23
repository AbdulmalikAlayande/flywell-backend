package com.example.airlinereservation.services.userservice;

import com.example.airlinereservation.dtos.Request.AdminInvitationRequest;
import com.example.airlinereservation.dtos.Request.CreateAdminRequest;
import com.example.airlinereservation.dtos.Request.CreateCrewMemberRequest;
import com.example.airlinereservation.dtos.Request.FlightRequest;
import com.example.airlinereservation.dtos.Response.*;
import com.example.airlinereservation.exceptions.EmptyFieldException;
import com.example.airlinereservation.exceptions.FailedRegistrationException;
import com.example.airlinereservation.exceptions.FieldInvalidException;
import com.example.airlinereservation.exceptions.InvalidRequestException;

public interface AdminService {

    CreateAdminResponse createAdminAccount(CreateAdminRequest createAdminRequest) throws FailedRegistrationException;
	AdminInvitationResponse inviteAdmin(AdminInvitationRequest invitationRequest) throws InvalidRequestException, FieldInvalidException, EmptyFieldException;
    CreateCrewMemberResponse addCrewMember(CreateCrewMemberRequest createCrewMemberRequest) throws EmptyFieldException, IllegalAccessException, FieldInvalidException;
    GetUserResponse findByEmail(String email);
	FlightResponse addNewFlight(FlightRequest flightRequest) throws InvalidRequestException;
	
	void deleteAll();
}
 
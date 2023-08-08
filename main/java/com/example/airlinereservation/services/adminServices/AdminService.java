package com.example.airlinereservation.services.adminServices;

import com.example.airlinereservation.dtos.Request.CreateAdminRequest;
import com.example.airlinereservation.dtos.Response.CreateAdminResponse;

public interface AdminService {
    // inviteAdmin(AdminRequest adminRequest)

    // createAdmin(AdminRequest adminRequest)
    // assignCrewMembers(List<CrewMember> crewMembers)

CreateAdminResponse createAdmin(CreateAdminRequest createAdminRequest);


}

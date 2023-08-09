package com.example.airlinereservation.services.userservice;

import com.example.airlinereservation.dtos.Request.CreateAdminRequest;
import com.example.airlinereservation.dtos.Response.CreateAdminResponse;

public interface AdminService {

    CreateAdminResponse createAdmin(CreateAdminRequest createAdminRequest);

	
}
 
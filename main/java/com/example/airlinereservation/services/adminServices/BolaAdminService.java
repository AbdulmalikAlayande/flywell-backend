package com.example.airlinereservation.services.adminServices;

import com.example.airlinereservation.dtos.Request.CreateAdminRequest;
import com.example.airlinereservation.dtos.Response.CreateAdminResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor

public class BolaAdminService implements AdminService{
	
	@Override
	public CreateAdminResponse createAdmin(CreateAdminRequest createAdminRequest) {
		return null;
	}
}

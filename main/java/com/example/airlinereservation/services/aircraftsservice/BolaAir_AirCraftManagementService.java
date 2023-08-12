package com.example.airlinereservation.services.aircraftsservice;

import com.example.airlinereservation.data.model.aircraft.AirCraft;
import com.example.airlinereservation.dtos.Request.AirCraftRequest;
import com.example.airlinereservation.dtos.Response.AirCraftResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor


public class BolaAir_AirCraftManagementService implements AirCraftManagementService{
	private final Set<AirCraft> hanger = new HashSet<>();

	@Override
	public AirCraftResponse addAircraftToHanger(AirCraftRequest airCraftRequest) {
		return null;
	}
	
	@Override
	public AirCraft getAvailableAirCraft(String location, boolean availability) {
		return null;
	}
	
	@Override
	public void removeAircraft(AirCraft aircraft) {
	
	}
}

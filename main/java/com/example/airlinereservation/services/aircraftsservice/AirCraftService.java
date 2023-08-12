package com.example.airlinereservation.services.aircraftsservice;

import com.example.airlinereservation.dtos.Request.AirCraftRequest;
import com.example.airlinereservation.dtos.Response.AirCraftResponse;

public interface AirCraftService {
	
	AirCraftResponse saveAirCraft(AirCraftRequest airCraftRequest);
	AirCraftResponse assignAircraft(AirCraftRequest airCraftRequest);
	
}

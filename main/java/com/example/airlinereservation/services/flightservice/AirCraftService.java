package com.example.airlinereservation.services.flightservice;

import com.example.airlinereservation.dtos.Request.AirCraftRequest;
import com.example.airlinereservation.dtos.Response.AirCraftResponse;

public interface AirCraftService {
	
	AirCraftResponse addAirCraft(AirCraftRequest airCraftRequest);
	AirCraftResponse assignAircraft(AirCraftRequest airCraftRequest);
	
}

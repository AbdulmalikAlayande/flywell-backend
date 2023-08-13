package com.example.airlinereservation.services.aircraftsservice;

import com.example.airlinereservation.data.model.aircraft.AirCraft;
import com.example.airlinereservation.data.model.enums.Destinations;
import com.example.airlinereservation.dtos.Request.AirCraftRequest;
import com.example.airlinereservation.dtos.Response.AirCraftResponse;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface AirCraftManagementService {

	AirCraftResponse addAircraftToHanger(AirCraftRequest airCraftRequest);
	AirCraft getAvailableAirCraft(Destinations location, boolean availability);
	void removeAircraft(AirCraft aircraft);
	
	boolean hangerContainsAirCraftByName(String airCraftName);
	
	List<AirCraft> getAirCraftByModel(String model);
	
	boolean hangerContainsAirCraftByModel(String model);
	boolean hangerContainsAirCraft(AirCraft airCraft);
	UUID getTestHangerId();
	
	long getCountOfAirCraftInHanger();
}

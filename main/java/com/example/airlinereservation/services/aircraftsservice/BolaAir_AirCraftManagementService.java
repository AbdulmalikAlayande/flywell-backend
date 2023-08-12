package com.example.airlinereservation.services.aircraftsservice;

import com.example.airlinereservation.data.model.aircraft.AirCraft;
import com.example.airlinereservation.dtos.Request.AirCraftRequest;
import com.example.airlinereservation.dtos.Response.AirCraftResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class BolaAir_AirCraftManagementService implements AirCraftManagementService{
	
	private final Set<AirCraft> hanger = new HashSet<>();
	private ModelMapper mapper;
	
	@Override
	public AirCraftResponse addAircraftToHanger(AirCraftRequest airCraftRequest) {
		AirCraft airCraft = new AirCraft();
		mapper.map(airCraftRequest, airCraft);
		return null;
	}
	
	@Override
	public AirCraft getAvailableAirCraft(String location, boolean availability) {
		return null;
	}
	
	@Override
	public void removeAircraft(AirCraft aircraft) {
	}
	
	@Override
	public boolean hangerContainsAirCraftByName(String airCraftName) {
		return false;
	}
	
	@Override
	public boolean hangerContainsAirCraftByModel(String airCraftName) {
		return false;
	}
	
	@Override
	public boolean hangerContainsAirCraft(AirCraft airCraft) {
		return false;
	}
}

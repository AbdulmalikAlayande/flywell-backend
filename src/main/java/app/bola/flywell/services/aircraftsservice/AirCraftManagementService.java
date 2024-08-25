package app.bola.flywell.services.aircraftsservice;

import app.bola.flywell.data.model.aircraft.AirCraft;
import app.bola.flywell.data.model.enums.Destinations;
import app.bola.flywell.dtos.Request.AirCraftRequest;
import app.bola.flywell.dtos.Response.AirCraftResponse;

import java.util.List;
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

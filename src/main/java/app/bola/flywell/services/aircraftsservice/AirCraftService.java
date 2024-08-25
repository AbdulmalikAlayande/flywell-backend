package app.bola.flywell.services.aircraftsservice;

import app.bola.flywell.dtos.Request.AirCraftRequest;
import app.bola.flywell.dtos.Response.AirCraftResponse;

public interface AirCraftService {
	
	AirCraftResponse saveAirCraft(AirCraftRequest airCraftRequest);
	AirCraftResponse assignAircraft(AirCraftRequest airCraftRequest);
	
}

package app.bola.flywell.services.aircraftsservice;

import app.bola.flywell.dtos.request.AirCraftRequest;
import app.bola.flywell.dto.response.AirCraftResponse;

public interface AirCraftService {
	
	AirCraftResponse saveAirCraft(AirCraftRequest airCraftRequest);
	AirCraftResponse assignAircraft(AirCraftRequest airCraftRequest);
	
}

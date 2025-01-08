package app.bola.flywell.services.aircrafts;

import app.bola.flywell.dto.request.AirCraftRequest;
import app.bola.flywell.dto.response.AirCraftResponse;

public interface AirCraftService {
	
	AirCraftResponse saveAirCraft(AirCraftRequest airCraftRequest);
	AirCraftResponse assignAircraft(AirCraftRequest airCraftRequest);
	
}

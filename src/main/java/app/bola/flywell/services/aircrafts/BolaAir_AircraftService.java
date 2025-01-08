package app.bola.flywell.services.aircrafts;

import app.bola.flywell.dto.request.AirCraftRequest;
import app.bola.flywell.dto.response.AirCraftResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class BolaAir_AircraftService implements AirCraftService{

	@Override
	public AirCraftResponse saveAirCraft(AirCraftRequest airCraftRequest) {
		return null;
	}
	
	@Override
	public AirCraftResponse assignAircraft(AirCraftRequest airCraftRequest) {
		return null;
	}
}

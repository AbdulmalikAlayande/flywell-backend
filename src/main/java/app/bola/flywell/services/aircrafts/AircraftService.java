package app.bola.flywell.services.aircrafts;

import app.bola.flywell.basemodules.FlyWellService;
import app.bola.flywell.data.model.aircraft.Aircraft;
import app.bola.flywell.dto.request.AircraftRequest;
import app.bola.flywell.dto.response.AircraftResponse;

public interface AircraftService extends FlyWellService<AircraftRequest, AircraftResponse> {

	Aircraft findAvailableAircraft(int capacity);
}

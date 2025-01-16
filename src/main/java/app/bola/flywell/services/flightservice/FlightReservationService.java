package app.bola.flywell.services.flightservice;

import app.bola.flywell.basemodules.FlyWellService;
import app.bola.flywell.dto.request.FlightReservationRequest;
import app.bola.flywell.dto.response.FlightReservationResponse;

public interface FlightReservationService extends FlyWellService<FlightReservationRequest, FlightReservationResponse> {

    void cancelReservation(String flightId, String reservationId);

}

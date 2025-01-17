package app.bola.flywell.controllers;

import app.bola.flywell.basemodules.FlyWellController;
import app.bola.flywell.dto.request.FlightReservationRequest;
import app.bola.flywell.dto.response.FlightReservationResponse;
import app.bola.flywell.services.flightservice.FlightReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/reservation")
@AllArgsConstructor
public class FlightReservationController implements FlyWellController<FlightReservationRequest, FlightReservationResponse> {

    FlightReservationService reservationService;


    @Override
    public ResponseEntity<FlightReservationResponse> createNew(FlightReservationRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<FlightReservationResponse> findByPublicId(String publicId) {
        return null;
    }

    @Override
    public ResponseEntity<Collection<FlightReservationResponse>> findAll() {
        return null;
    }
}

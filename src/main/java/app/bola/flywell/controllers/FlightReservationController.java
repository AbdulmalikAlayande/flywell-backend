package app.bola.flywell.controllers;

import app.bola.flywell.basemodules.FlyWellController;
import app.bola.flywell.dto.request.FlightReservationRequest;
import app.bola.flywell.dto.response.FlightReservationResponse;
import app.bola.flywell.services.flightservice.FlightReservationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/reservation")
@AllArgsConstructor
public class FlightReservationController implements FlyWellController<FlightReservationRequest, FlightReservationResponse> {

    final FlightReservationService reservationService;


    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public ResponseEntity<FlightReservationResponse> createNew(FlightReservationRequest request) {
        FlightReservationResponse response = reservationService.createNew(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @Override
    @GetMapping("{public-id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public ResponseEntity<FlightReservationResponse> findByPublicId(@PathVariable("public-id") String publicId) {
        FlightReservationResponse response = reservationService.findByPublicId(publicId);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public ResponseEntity<Collection<FlightReservationResponse>> findAll(Pageable pageable) {
        return null;
    }

    @PutMapping("{flight-id}/{reservation-id}/cancel")
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public ResponseEntity<FlightReservationResponse> cancelReservation(@PathVariable("flight-id") String flightId,
                                                                       @PathVariable("reservation-id") String reservationId){
        FlightReservationResponse response = reservationService.cancelReservation(flightId, reservationId);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @PutMapping("{flight-id}/{reservation-id}/update-status")
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public ResponseEntity<FlightReservationResponse> updateReservationStatus(@PathVariable("flight-id") String flightId,
                                                                             @PathVariable("reservation-id") String reservationId){
        FlightReservationResponse response = reservationService.updateReservationStatus(flightId, reservationId);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public ResponseEntity<Collection<FlightReservationResponse>> findAll() {
        return null;
    }
}

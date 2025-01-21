package app.bola.flywell.controllers;

import app.bola.flywell.basemodules.FlyWellController;
import app.bola.flywell.dto.request.FlightRequest;
import app.bola.flywell.dto.request.FlightUpdateRequest;
import app.bola.flywell.dto.response.ApiResponse;
import app.bola.flywell.dto.response.FlightResponse;
import app.bola.flywell.services.flightservice.FlightService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("flights")
@CrossOrigin("*")
@AllArgsConstructor

public class FlightController implements FlyWellController<FlightRequest, FlightResponse> {
	
	private final FlightService flightService;

	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<FlightResponse> createNew(@Valid @RequestBody FlightRequest flightRequest){
		FlightResponse response = flightService.createNew(flightRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<FlightResponse> updateFlight(@Valid @RequestBody FlightUpdateRequest flightUpdateRequest){
		FlightResponse response = flightService.updateFlight(flightUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<FlightResponse> findByPublicId(String publicId) {
		FlightResponse response = flightService.findByPublicId(publicId);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Collection<FlightResponse>> findAll() {
		Collection<FlightResponse> response = flightService.findAll();
		return ResponseEntity.status(HttpStatus.CREATED).body(response);	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Collection<FlightResponse>> findAll(Pageable pageable) {
		Collection<FlightResponse> response = flightService.findAll(pageable);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}

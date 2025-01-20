package app.bola.flywell.controllers;

import app.bola.flywell.basemodules.FlyWellController;
import app.bola.flywell.dto.response.FlightInstanceResponse;
import app.bola.flywell.dto.request.*;
import app.bola.flywell.exceptions.InvalidRequestException;
import app.bola.flywell.services.flightservice.FlightInstanceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("flight-instance/")
@CrossOrigin("*")
@AllArgsConstructor
public class FlightInstanceController implements FlyWellController<FlightInstanceRequest, FlightInstanceResponse> {
	
	final FlightInstanceService flightInstanceService;


	public ResponseEntity<FlightInstanceResponse> createNew(@Valid @RequestBody FlightInstanceRequest instanceRequest){
		FlightInstanceResponse response = flightInstanceService.createNew(instanceRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("available-flights")
	public ResponseEntity<List<FlightInstanceResponse>> getAvailableFlights(){
		List<FlightInstanceResponse> response = flightInstanceService.getAvailableFlights();
		return ResponseEntity.status(HttpStatus.FOUND).body(response);
	}

	@PostMapping("assign-crew-member")
	public ResponseEntity<?> assignCrewMemberToFlight(@RequestParam String crewMemberId, @RequestParam String flightId) throws InvalidRequestException {
		FlightInstanceResponse response = flightInstanceService.assignCrewMemberToFlight(crewMemberId, flightId);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
	}

	@GetMapping("/{flight-id}/view-schedule")
	public ResponseEntity<FlightInstanceResponse> viewFlightSchedule(@PathVariable("flight-id") String flightId){
		FlightInstanceResponse response = flightInstanceService.viewFlightSchedule(flightId);
		return ResponseEntity.status(HttpStatus.FOUND).body(response);
	}

	@Override
	public ResponseEntity<FlightInstanceResponse> findByPublicId(String publicId) {
		FlightInstanceResponse response = flightInstanceService.findByPublicId(publicId);
		return ResponseEntity.status(HttpStatus.FOUND).body(response);
	}

	@Override
	public ResponseEntity<Collection<FlightInstanceResponse>> findAll() {
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();

	}

}

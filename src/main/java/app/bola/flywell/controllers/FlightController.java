package app.bola.flywell.controllers;

import app.bola.flywell.dtos.request.FlightRequest;
import app.bola.flywell.dto.response.ApiResponse;
import app.bola.flywell.dto.response.FlightResponse;
import app.bola.flywell.services.flightservice.FlightService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bola-air/flights/")
@CrossOrigin("*")
@AllArgsConstructor

public class FlightController {
	
	private final FlightService flightService;
	
	@PostMapping("add-flight/")
	@Validated
	public ApiResponse<?> addNewFlight(@Valid @RequestBody FlightRequest flightRequest){
	
		ApiResponse<FlightResponse> apiResponse = new ApiResponse<>();
		FlightResponse response = flightService.createNew(flightRequest);
		apiResponse.setResponseData(response);
		apiResponse.setSuccessful(HttpStatus.CREATED.is2xxSuccessful());
		apiResponse.setStatusCode(HttpStatus.CREATED.value());
		return apiResponse;
	}
}

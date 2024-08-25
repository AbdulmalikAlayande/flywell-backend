package app.bola.flywell.controllers;

import app.bola.flywell.dtos.Request.FlightRequest;
import app.bola.flywell.dtos.Response.ApiResponse;
import app.bola.flywell.dtos.Response.FlightResponse;
import app.bola.flywell.exceptions.InvalidRequestException;
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

public class BolaAir_FlightController {
	
	private final FlightService flightService;
	
	@PostMapping("add-flight/")
	@Validated
	public ApiResponse<?> addNewFlight(@Valid @RequestBody FlightRequest flightRequest){
	
		try {
			ApiResponse<FlightResponse> apiResponse = new ApiResponse<>();
			FlightResponse response = flightService.addFlight(flightRequest);
			apiResponse.setResponseData(response);
			apiResponse.setSuccessful(HttpStatus.CREATED.is2xxSuccessful());
			apiResponse.setStatusCode(HttpStatus.CREATED.value());
			return apiResponse;
		} catch (InvalidRequestException exception) {
			ApiResponse<String> apiResponse = new ApiResponse<>();
			apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
			apiResponse.setResponseData(exception.getMessage());
			apiResponse.setSuccessful(HttpStatus.BAD_REQUEST.is4xxClientError());
			return apiResponse;
		}
	}
}

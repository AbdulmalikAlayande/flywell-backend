package com.example.airlinereservation.Controller;

import com.example.airlinereservation.dtos.Request.FlightRequest;
import com.example.airlinereservation.dtos.Response.ApiResponse;
import com.example.airlinereservation.dtos.Response.FlightResponse;
import com.example.airlinereservation.exceptions.InvalidRequestException;
import com.example.airlinereservation.services.flightservice.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bola-air/")
@CrossOrigin("*")
@AllArgsConstructor
public class BolaAir_FlightController {
	
	private final FlightService flightService;
	
	@PostMapping(value = "add-flight/")
	public ApiResponse<?> addNewFlight(@RequestBody FlightRequest flightRequest){
	
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

package app.bola.flywell.controllers;


import app.bola.flywell.dto.response.ApiResponse;
import app.bola.flywell.dto.response.FlightInstanceResponse;
import app.bola.flywell.dtos.request.*;
import app.bola.flywell.exceptions.InvalidRequestException;
import app.bola.flywell.services.flightservice.FlightInstanceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("bola-air/")
@CrossOrigin("*")
@AllArgsConstructor
public class FlightInstanceController {
	
	private final FlightInstanceService flightInstanceService;
	@GetMapping("available-flights/")
	public ApiResponse<?> getAvailableFlights(){
		return null;
	}
	
	@PostMapping("create-new-flight-instance")
	public ApiResponse<?> createNewInstance(@Valid @RequestBody FlightInstanceRequest instanceRequest){
		try {
			ApiResponse<FlightInstanceResponse> apiResponse = new ApiResponse<>();
			FlightInstanceResponse response = flightInstanceService.createNew(instanceRequest);
			apiResponse.setSuccessful(HttpStatus.CREATED.is2xxSuccessful());
			apiResponse.setStatusCode(HttpStatus.CREATED.value());
			apiResponse.setResponseData(response);
			return apiResponse;
		} catch (Exception e) {
			ApiResponse<String> apiResponse = new ApiResponse<>();
			apiResponse.setResponseData(e.getMessage());
			apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
			apiResponse.setSuccessful(HttpStatus.BAD_GATEWAY.is4xxClientError());
			return apiResponse;
		}
    }
}

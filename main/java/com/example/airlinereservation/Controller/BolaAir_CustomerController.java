package com.example.airlinereservation.Controller;

import com.example.airlinereservation.dtos.Request.CustomerRequest;
import com.example.airlinereservation.dtos.Request.LoginRequest;
import com.example.airlinereservation.dtos.Response.ApiResponse;
import com.example.airlinereservation.dtos.Response.CustomerResponse;
import com.example.airlinereservation.dtos.Response.LoginResponse;
import com.example.airlinereservation.services.userservice.CustomerService;
import com.example.airlinereservation.utils.exceptions.FailedRegistrationException;
import com.example.airlinereservation.utils.exceptions.LoginFailedException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@RequestMapping("bola-air/api/v3/")
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class BolaAir_CustomerController {
	
	public static final String ERROR_MESSAGE = "Error Message:: {}";
	private CustomerService customerService;
	
	@PostMapping("register-customer/")
	public ResponseEntity<?> registerCustomer(@RequestBody CustomerRequest customerRequest){
		CustomerResponse response = new CustomerResponse();
		try {
			response = customerService.registerNewCustomer(customerRequest);
			ApiResponse<CustomerResponse> apiResponse = new ApiResponse<>();
			apiResponse.setData(response);
			apiResponse.setSuccessful(HttpStatus.CREATED.is2xxSuccessful());
			apiResponse.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (FailedRegistrationException e) {
			log.info(ERROR_MESSAGE, e.getMessage());
			log.info("Error:: ", e);
			response.setMessage(e.getMessage());
			ApiResponse<CustomerResponse> apiResponse= new ApiResponse<>();
			apiResponse.setData(response);
			apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
			apiResponse.setSuccessful(HttpStatus.BAD_REQUEST.is4xxClientError());
			return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("login-customer")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
		try {
			LoginResponse loginResponse = customerService.login(loginRequest);
			ApiResponse<LoginResponse> response = new ApiResponse<>();
			response.setData(loginResponse);
			response.setSuccessful(HttpStatus.OK.is2xxSuccessful());
			response.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (LoginFailedException e) {
			LoginResponse loginResponse = new LoginResponse();
			return null;
		}
	}
}

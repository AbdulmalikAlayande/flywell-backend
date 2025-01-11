package app.bola.flywell.controllers;

import app.bola.flywell.dto.request.CustomerRequest;
import app.bola.flywell.dto.response.ApiResponse;
import app.bola.flywell.dto.response.CustomerResponse;
import app.bola.flywell.exceptions.*;
import app.bola.flywell.services.userservice.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static app.bola.flywell.utils.Constants.ERROR_MESSAGE;
import static app.bola.flywell.utils.Constants.REGISTRATION_SUCCESSFUL_MESSAGE;

@RestController
@RequestMapping("bola-air/api/v3/")
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
@Validated
public class FlyWellCustomerController {
	
	private CustomerService customerService;


	@GetMapping("/")
	public String home(){
		return "Welcome to FlyWell";
	}
	@PostMapping("register-customer/")
	@Validated
	public ApiResponse<?> registerCustomer(@RequestBody @Valid CustomerRequest customerRequest){
		CustomerResponse response = new CustomerResponse();
		try {
			response = customerService.createNew(customerRequest);
			response.setMessage(REGISTRATION_SUCCESSFUL_MESSAGE);
			ApiResponse<CustomerResponse> apiResponse = new ApiResponse<>();
			apiResponse.setResponseData(response);
			apiResponse.setSuccessful(HttpStatus.CREATED.is2xxSuccessful());
			apiResponse.setStatusCode(HttpStatus.CREATED.value());
			System.out.println(apiResponse);
			return apiResponse;
		} catch (Exception exception) {
			log.info(ERROR_MESSAGE, exception.getMessage());
			log.info("Error:: ", exception);
			response.setMessage(exception.getMessage());
			ApiResponse<CustomerResponse> apiResponse= new ApiResponse<>();
			apiResponse.setResponseData(response);
			apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
			apiResponse.setSuccessful(HttpStatus.BAD_REQUEST.is4xxClientError());
			System.out.println(apiResponse);
			return apiResponse;
		}
	}
	
	@PostMapping("activate-account/{public-id}/{TOTP}")
	public ApiResponse<?> activateAccount(@PathVariable("public-id") String publicId, @PathVariable String TOTP){
		CustomerResponse customerResponse;
		try {
			ApiResponse<CustomerResponse> apiResponse = new ApiResponse<>();
			customerResponse = customerService.activateCustomerAccount(TOTP, publicId);
			apiResponse.setResponseData(customerResponse);
			apiResponse.setSuccessful(HttpStatus.CREATED.is2xxSuccessful());
			apiResponse.setStatusCode(HttpStatus.CREATED.value());
			System.out.println("api response at activate account ==> "+apiResponse);
			return apiResponse;
		} catch (InvalidRequestException e) {
			ApiResponse<String> apiResponse = new ApiResponse<>();
			apiResponse.setSuccessful(false);
			apiResponse.setResponseData(e.getMessage());
			apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
			return apiResponse;
		}
	}

	
	@GetMapping("all-customers/")
	public ApiResponse<?> getAllCustomers(){
		try{
			List<CustomerResponse> foundCustomers = customerService.findAll();
			ApiResponse<List<CustomerResponse>> apiResponse = new ApiResponse<>();
			apiResponse.setResponseData(foundCustomers);
			apiResponse.setSuccessful(HttpStatus.FOUND.is2xxSuccessful());
			apiResponse.setStatusCode(HttpStatus.FOUND.value());
			System.out.println("api response at get all customers try ==> "+ apiResponse);
			return apiResponse;
		}catch (Throwable exception){
			ApiResponse<String> apiResponse = new ApiResponse<>();
			apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
			apiResponse.setSuccessful(HttpStatus.NOT_FOUND.is4xxClientError());
			apiResponse.setResponseData(exception.getMessage());
			System.out.println("api response at get all customers try ==> "+ apiResponse);
			return apiResponse;
		}
	}
}

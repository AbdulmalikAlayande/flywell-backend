package app.bola.flywell.controllers;

import app.bola.flywell.basemodules.FlyWellController;
import app.bola.flywell.dto.request.CustomerRequest;
import app.bola.flywell.dto.response.ApiResponse;
import app.bola.flywell.dto.response.CustomerResponse;
import app.bola.flywell.exceptions.*;
import app.bola.flywell.services.users.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("customer")
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class CustomerController implements FlyWellController<CustomerRequest, CustomerResponse> {
	
	private CustomerService customerService;


	@Override
	public ResponseEntity<CustomerResponse> createNew(@RequestBody @Valid CustomerRequest customerRequest){
		CustomerResponse response = customerService.createNew(customerRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@Override
	public ResponseEntity<CustomerResponse> findByPublicId(String publicId) {
		return null;
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

	public ResponseEntity<Collection<CustomerResponse>> findAll(){
		return null;
	}
}

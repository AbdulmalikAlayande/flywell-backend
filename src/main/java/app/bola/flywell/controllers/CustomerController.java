package app.bola.flywell.controllers;

import app.bola.flywell.basemodules.FlyWellController;
import app.bola.flywell.dto.request.CustomerRequest;
import app.bola.flywell.dto.response.CustomerResponse;
import app.bola.flywell.dto.response.LoginResponse;
import app.bola.flywell.exceptions.*;
import app.bola.flywell.services.users.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("customer")
public class CustomerController implements FlyWellController<CustomerRequest, CustomerResponse> {
	
	private CustomerService customerService;


	@Override
	public ResponseEntity<CustomerResponse> createNew(@RequestBody @Valid CustomerRequest customerRequest){
		CustomerResponse response = customerService.createNew(customerRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@Override
	@PreAuthorize(value = "hasAnyRole('CUSTOMER', 'ADMIN')")
	public ResponseEntity<CustomerResponse> findByPublicId(String publicId) {
		CustomerResponse response = customerService.findByPublicId(publicId);
		return ResponseEntity.status(HttpStatus.FOUND).body(response);
	}

	@PostMapping("activate-account/{public-id}/{TOTP}")
	public ResponseEntity<?> activateAccount(@PathVariable("public-id") String publicId, @PathVariable String TOTP) throws InvalidRequestException {
		LoginResponse response = customerService.activateCustomerAccount(TOTP, publicId);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@Override
	@PreAuthorize(value = "hasRole('ADMIN')")
	public ResponseEntity<Collection<CustomerResponse>> findAll(){
		List<CustomerResponse> response = customerService.findAll();
		return ResponseEntity.status(HttpStatus.FOUND).body(response);
	}

	@GetMapping
	@PreAuthorize(value = "hasRole('ADMIN')")
	public ResponseEntity<Collection<CustomerResponse>> findAll(Pageable pageable) {
		List<CustomerResponse> response = customerService.findAll(pageable);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}

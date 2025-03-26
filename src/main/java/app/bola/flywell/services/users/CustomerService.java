package app.bola.flywell.services.users;


import app.bola.flywell.basemodules.FlyWellService;
import app.bola.flywell.dto.request.CustomerRequest;
import app.bola.flywell.dto.response.CustomerResponse;
import app.bola.flywell.dto.response.LoginResponse;
import app.bola.flywell.dto.response.FlightReservationResponse;
import app.bola.flywell.exceptions.AuthenticationFailedException;
import app.bola.flywell.exceptions.InvalidRequestException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService extends FlyWellService<CustomerRequest, CustomerResponse> {

	CustomerResponse activateCustomerAccount(String OTP, String publicId) throws InvalidRequestException, AuthenticationFailedException;

	long getCountOfCustomers();

	List<CustomerResponse> findAll(Pageable pageable);

	List<CustomerResponse> findAll();
	
	List<FlightReservationResponse> fetchCustomerReservations(String userId);
}

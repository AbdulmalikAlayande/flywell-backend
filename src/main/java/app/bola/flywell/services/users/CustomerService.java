package app.bola.flywell.services.users;


import app.bola.flywell.basemodules.FlyWellService;
import app.bola.flywell.dto.request.CustomerRequest;
import app.bola.flywell.dto.response.CustomerResponse;
import app.bola.flywell.exceptions.InvalidRequestException;

import java.util.List;

public interface CustomerService extends FlyWellService<CustomerRequest, CustomerResponse> {

	CustomerResponse activateCustomerAccount(String OTP, String publicId) throws InvalidRequestException;

	long getCountOfCustomers();

	List<CustomerResponse> findAll();
}

package app.bola.flywell.services.userservice;


import app.bola.flywell.basemodules.FlyWellService;
import app.bola.flywell.dto.request.CustomerRequest;
import app.bola.flywell.dto.request.UpdateRequest;
import app.bola.flywell.dto.response.CustomerResponse;
import app.bola.flywell.exceptions.InvalidRequestException;

import java.util.List;

public interface CustomerService extends FlyWellService<CustomerRequest, CustomerResponse> {

	CustomerResponse activateCustomerAccount(String OTP, String publicId) throws InvalidRequestException;

	CustomerResponse updateDetailsOfRegisteredCustomer(UpdateRequest updateRequest);

	long getCountOfCustomers();

	List<CustomerResponse> findAll();
}

package app.bola.flywell.services.userservice;

import app.bola.flywell.data.model.enums.Role;
import app.bola.flywell.data.model.persons.Customer;
import app.bola.flywell.data.model.persons.OTP;
import app.bola.flywell.data.model.persons.UserBioData;
import app.bola.flywell.data.repositories.CustomerRepository;
import app.bola.flywell.data.repositories.UserBioDataRepository;
import app.bola.flywell.dtos.Request.NotificationRequest;
import app.bola.flywell.dtos.Response.CustomerResponse;
import app.bola.flywell.exceptions.FieldInvalidException;
import app.bola.flywell.services.notifications.Validator;
import app.bola.flywell.services.notifications.mail.MailService;
import app.bola.flywell.data.model.Passenger;
import app.bola.flywell.dtos.Request.CustomerRequest;
import app.bola.flywell.dtos.Request.LoginRequest;
import app.bola.flywell.dtos.Request.UpdateRequest;
import app.bola.flywell.dtos.Response.FlightResponse;
import app.bola.flywell.dtos.Response.LoginResponse;
import app.bola.flywell.exceptions.FailedRegistrationException;
import app.bola.flywell.exceptions.InvalidRequestException;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;

import static app.bola.flywell.exceptions.Exceptions.throwInvalidRequestException;
import static app.bola.flywell.utils.Constants.*;

@Service
@AllArgsConstructor
@Slf4j
public class BolaAirCustomerService implements CustomerService {
	
	final Validator validator;
	final CustomerRepository customerRepository;
	final UserBioDataRepository userBioDataRepository;
	final ModelMapper mapper;
	final OTPService otpService;
	final MailService mailer;
	
	@Override
	@Transactional(rollbackFor = {SQLException.class, FailedRegistrationException.class})
	public CustomerResponse registerNewCustomer(@NotNull CustomerRequest customerRequest) throws FailedRegistrationException {
		CustomerResponse customerResponse = new CustomerResponse();
		try {
			validator.validateEmail(customerRequest.getEmail());
			validator.validatePassword(customerRequest.getPassword());
			UserBioData biodata = mapper.map(customerRequest, UserBioData.class);
			biodata.setOTPs(new ArrayList<>());
			UserBioData savedBio = userBioDataRepository.save(biodata);
			Customer customer = new Customer();
			customer.setRole(Role.USER);
			customer.setBioData(savedBio);
			customerRepository.save(customer);
			
			OTP createdOTP = createOtp(biodata.getEmail());
			long otpData = createdOTP.getData();
			mailer.sendOtp(buildNotificationRequest(biodata.getFirstName(), biodata.getEmail(), otpData));
			
			savedBio.getOTPs().add(createdOTP);
			userBioDataRepository.save(savedBio);
			mapper.map(savedBio, customerResponse);
			customerResponse.setMessage(REGISTRATION_SUCCESSFUL_MESSAGE);
			customerResponse.setOtp(otpData);
		} catch (FieldInvalidException | InvalidRequestException exception) {
			throwFailedRegistrationException(exception);
		}
        return customerResponse;
	}

	private void throwFailedRegistrationException(Exception exception) {
	}

	@Override
	@Transactional(rollbackFor = {SQLException.class, FailedRegistrationException.class})
	public CustomerResponse activateCustomerAccount(String OTP) throws InvalidRequestException {
		OTP otp = otpService.verifiedOtp(OTP);
		return userBioDataRepository.findByEmail(otp.getUserEmail())
								    .map(userBioData -> buildCustomerResponse(userBioData, SUCCESSFUL_ACTIVATION_MESSAGE))
								    .orElseThrow(()->new InvalidRequestException("USER WITH EMAIL NOT FOUND"));
	}
	
	private CustomerResponse buildCustomerResponse(UserBioData bioData, String message) {
		return CustomerResponse.builder()
						       .message(message)
						       .lastName(bioData.getLastName())
						       .phoneNumber(bioData.getPhoneNumber())
						       .email(bioData.getEmail())
						       .build();
	}
	
	public OTP createOtp(String email){
		OTP generatedTotp = otpService.generateTOTP(email);
		return otpService.saveOTP(generatedTotp);
	}
	
	private NotificationRequest buildNotificationRequest(String firstName, String email, long otp){
		return NotificationRequest.builder()
				       .email(email)
				       .code(String.valueOf(otp))
				       .OTP(otp)
				       .mailPath("otp")
				       .firstName(firstName)
				       .build();
	}
	@Override
	public List<FlightResponse> viewAvailableFLights() {
		return null;
		
	}
	
	@Override
	public CustomerResponse updateDetailsOfRegisteredCustomer(@NotNull UpdateRequest updateRequest) {
		CustomerResponse response = new CustomerResponse();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setSkipNullEnabled(true);
		return response;
	}
	
	@Override
	public LoginResponse login(LoginRequest loginRequest) {
	    return new LoginResponse();
	}

	@Override public Optional<CustomerResponse> findCustomerById(String passengerId) {
		CustomerResponse response = new CustomerResponse();
		Optional<Customer> foundCustomer = customerRepository.findById(passengerId);
		return Optional.of(foundCustomer
				       .map(passenger -> {
						   mapper.map(passenger, response);
						   return response;
					   })
				       .orElseThrow(()-> {
					       try {
						       return throwInvalidRequestException(String.format(INVALID_REQUEST_MESSAGE, "User", "id", passengerId));
					       } catch (InvalidRequestException e) {
						       throw new RuntimeException(e);
					       }
				       }));
	}
	
	@Override
	public List<CustomerResponse> getAllCustomers() {
		List<Customer> allCustomers = customerRepository.findAll();
		return new ArrayList<>(allCustomers.stream().map(passenger -> {
			CustomerResponse response = new CustomerResponse();
			mapper.map(passenger.getBioData(), response);
			return response;
		}).toList());
	}
	
	@Override
	public Optional<CustomerResponse> findCustomerByEmailAndPassword(String email, String password) {
		Optional<UserBioData> foundBio = userBioDataRepository.findByEmailAndPassword(email, password);
		CustomerResponse response = new CustomerResponse();
		foundBio.ifPresent(bioData -> {
			Optional<Customer> foundCustomer = customerRepository.findByBioData(bioData);
			foundCustomer.ifPresent(passenger -> mapper.map(bioData, response));
		});
		return Optional.of(response);
	}
	
	@Override public Optional<CustomerResponse> findCustomerByUserName(String userName) {
		return Optional.of(new CustomerResponse());
	}
	
	@Override public long getCountOfCustomers() {
		return customerRepository.count();
	}
	
	@Override
	public Optional<Passenger> findPassengerByUserNameForAdmin(String passengerUsername) {
		return Optional.empty();
	}
	
	@Override
	public void removeAll() {
		customerRepository.deleteAll();
		userBioDataRepository.deleteAll();
	}
}

package com.example.airlinereservation.services.userservice;

import com.example.airlinereservation.data.model.Passenger;
import com.example.airlinereservation.data.model.persons.*;
import com.example.airlinereservation.data.repositories.*;
import com.example.airlinereservation.dtos.Request.*;
import com.example.airlinereservation.dtos.Response.*;
import com.example.airlinereservation.exceptions.*;
import com.example.airlinereservation.services.notifications.Validator;
import com.example.airlinereservation.services.notifications.mail.MailService;
import com.example.airlinereservation.utils.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static com.example.airlinereservation.data.model.enums.Role.USER;
import static com.example.airlinereservation.exceptions.Exceptions.*;
import static com.example.airlinereservation.utils.Constants.*;

@Service
@AllArgsConstructor
@Slf4j
public class BolaAirCustomerService implements CustomerService {
	
	private Validator validator;
	private CustomerRepository customerRepository;
	private UserBioDataRepository userBioDataRepository;
	private AddressRepository addressRepository;
	private OTPRepository otpRepository;
	private ModelMapper mapper;
	private MailService mailer;
	
	@Override
	@Transactional(rollbackFor = {SQLException.class, FailedRegistrationException.class})
	public CustomerResponse registerNewCustomer(@NotNull CustomerRequest customerRequest) throws FailedRegistrationException {
		CustomerResponse customerResponse = new CustomerResponse();
		try {
			validateEmailAndPassword(customerRequest.getEmail(), customerRequest.getPassword());
			UserBioData biodata = mapper.map(customerRequest, UserBioData.class);
			biodata.setOTPs(new ArrayList<>());
			UserBioData savedBio = userBioDataRepository.save(biodata);
			Customer customer = new Customer();
			customer.setRole(USER);
			customer.setBioData(savedBio);
			customerRepository.save(customer);
			
			OTP createdOTP = createOtp(biodata.getEmail());
			long otpData = createdOTP.getData();
			mailer.sendOtp(buildNotificationRequest(biodata.getEmail(), otpData));
			
			savedBio.getOTPs().add(createdOTP);
			userBioDataRepository.save(savedBio);
			mapper.map(savedBio, customerResponse);
			customerResponse.setMessage(REGISTRATION_SUCCESSFUL_MESSAGE);
		} catch (FieldInvalidException | InvalidRequestException exception) {
			throwFailedRegistrationException(exception);
		}
		return customerResponse;
	}
	
	public void activateCustomerAccount(String OTP){
	
	}
	
	public OTP createOtp(String email){
		String emailEncoded = OTPGenerator.encodeBase32(email);
		String generatedOtp = OTPGenerator.generateTOTP(emailEncoded);
		return otpRepository.save(OTP.builder()
				       .data(Long.parseLong(generatedOtp))
				       .build());
	}
	
	private NotificationRequest buildNotificationRequest(String email, long otp){
		return NotificationRequest.builder()
				       .email(email)
				       .OTP(otp)
				       .build();
	}
	@Override
	public List<FlightResponse> viewAvailableFLights() {
		return null;
		
	}
	
	private void validateEmailAndPassword(String email, String password) throws FieldInvalidException, InvalidRequestException {
		validator.validateEmail(email);
		validator.validatePassword(password);
	}
	
	private boolean userDoesNotExistBy(String userName) {
		Optional<UserBioData> foundBio = userBioDataRepository.findByUserName(userName);
		return foundBio.filter(userBioData ->
				       customerRepository.existsByBioData(userBioData)).isEmpty();
	}
	
	@Override
	public CustomerResponse updateDetailsOfRegisteredCustomer(@NotNull UpdateRequest updateRequest) {
		CustomerResponse response = new CustomerResponse();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setSkipNullEnabled(true);
		Optional<UserBioData> userBio = userBioDataRepository.findByUserName(updateRequest.getUserName());
		return userBio.map(userBioData -> {
				   modelMapper.map(updateRequest, userBioData);
				   if (updateRequest.getNewUserName() != null){
					   userBioData.setUserName(updateRequest.getNewUserName());
				   }
				   Optional<Customer> foundCustomer = customerRepository.findByBioData(userBioData);
				   foundCustomer.ifPresent(passenger -> {
					   passenger.setBioData(userBioData);
					   customerRepository.save(passenger);
				   });
				   modelMapper.map(userBioData, response);
				   return response;
		}).orElseThrow(()-> {
			try {
				return throwInvalidRequestException(UPDATE_NOT_COMPLETED);
			} catch (InvalidRequestException e) {
				throw new RuntimeException(e);
			}
		});
		
	}
	
	@Override
	public LoginResponse login(LoginRequest loginRequest) throws LoginFailedException {
		validateLoginCredentialsIntegrity(loginRequest);
		checkIfUserExists(loginRequest);
		if (loginRequest.getUsername() == null)
			return logInViaEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
		return loginViaUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
	}
	
	private @NotNull LoginResponse loginViaUsernameAndPassword(String username, String password) throws LoginFailedException {
		Optional<UserBioData> foundUser = userBioDataRepository.findByUserName(username);
		AtomicReference<LoginResponse> response = new AtomicReference<>();
		Optional<LoginResponse> optionalResponse = foundUser.map(bioData -> {
			Optional<Customer> foundCustomer = customerRepository.findByBioData(bioData);
			foundCustomer.ifPresent(passenger -> {
				if (passenger.isLoggedIn())
					response.set(loginResponse(SESSION_NOT_EXHAUSTED_MESSAGE, username));
				passenger.setLastLoggedIn(LocalDate.now());
				passenger.setLoggedIn(true);
				response.set(loginResponse(LOGIN_SUCCESS_MESSAGE, username));
			});
			return response.get();
		});
		if (optionalResponse.isPresent())
			return optionalResponse.get();
		throw new LoginFailedException(LOGIN_FAILURE_MESSAGE);
	}
	
	private LoginResponse logInViaEmailAndPassword(String email, String password) throws LoginFailedException {
		Optional<UserBioData> foundBio = userBioDataRepository.findByEmailAndPassword(email, password);
		AtomicReference<LoginResponse> loginResponse = new AtomicReference<>();
		if (foundBio.isPresent()){
			Optional<Customer> foundCustomer = customerRepository.findByBioData(foundBio.get());
			Optional<LoginResponse> optionalResponse = foundCustomer.map(passenger -> {
				if (passenger.isLoggedIn())
					return loginResponse(SESSION_NOT_EXHAUSTED_MESSAGE, foundBio.get().getUserName());
				passenger.setLastLoggedIn(LocalDate.now());
				passenger.setLoggedIn(true);
				return loginResponse(LOGIN_SUCCESS_MESSAGE, foundBio.get().getUserName());
			});
			if (optionalResponse.isPresent())
				loginResponse.set(optionalResponse.get());
			else throw new LoginFailedException(LOGIN_FAILURE_MESSAGE);
		}
		return loginResponse.get();
	}
	
	private void validateLoginCredentialsIntegrity(@NotNull LoginRequest loginRequest) throws LoginFailedException {
		if (loginRequest.getUsername() == null && loginRequest.getEmail() == null)
			throw new LoginFailedException(INCORRECT_FORMAT_LOGIN_FAILURE_MESSAGE);
		else if (loginRequest.getPassword() == null) {
			throw new LoginFailedException(INCORRECT_FORMAT_LOGIN_FAILURE_MESSAGE);
		}
	}
	
	private void checkIfUserExists(@NotNull LoginRequest loginRequest) throws LoginFailedException {
		boolean userDoesNotExistsByUsername = userDoesNotExistBy(loginRequest.getUsername());
		boolean userDoesNotExistsByEmailAndPassword = userDoesNotExistBy(loginRequest.getEmail(), loginRequest.getPassword());
		if (userDoesNotExistsByEmailAndPassword && userDoesNotExistsByUsername)
			throw new LoginFailedException(LOGIN_FAILURE_MESSAGE);
	}
	
	private LoginResponse loginResponse(String message, String username) {
		return LoginResponse.builder().message(message).username(username).build();
	}
	
	private boolean userDoesNotExistBy(String email, String password) {
		Optional<CustomerResponse> response = findCustomerByEmailAndPassword(email, password);
		return response.isEmpty();
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
		List<CustomerResponse> responses = new ArrayList<>();
		List<Customer> allCustomers = customerRepository.findAll();
		allCustomers.forEach(passenger -> {
			CustomerResponse response = new CustomerResponse();
			mapper.map(passenger.getBioData(), response);
			responses.add(response);
		});
		return responses;
	}
	
	@Override public Optional<CustomerResponse> findCustomerByEmailAndPassword(String email, String password) {
		Optional<UserBioData> foundBio = userBioDataRepository.findByEmailAndPassword(email, password);
		CustomerResponse response = new CustomerResponse();
		foundBio.ifPresent(bioData -> {
			Optional<Customer> foundCustomer = customerRepository.findByBioData(bioData);
			foundCustomer.ifPresent(passenger -> mapper.map(bioData, response));
		});
		return Optional.of(response);
	}
	
	@Override public Optional<CustomerResponse> findCustomerByUserName(String userName) throws InvalidRequestException {
		CustomerResponse passengerResponse = new CustomerResponse();
		AtomicReference<CustomerResponse> response = new AtomicReference<>();
		Optional<UserBioData> foundBio = userBioDataRepository.findByUserName(userName);
		Optional<CustomerResponse> optionalPassengerResponse = foundBio.map(userBioData -> {
			Optional<Customer> foundCustomer = customerRepository.findByBioData(userBioData);
			foundCustomer.ifPresent(passenger -> {
				mapper.map(userBioData, passengerResponse);
				response.set(passengerResponse);
			});
			return response.get();
		});
		if (optionalPassengerResponse.isPresent())
			return optionalPassengerResponse;
		throw new InvalidRequestException(String.format(INVALID_REQUEST_MESSAGE, "User", "username",userName));
	}
	
	@Override public long getCountOfCustomers() {
		return customerRepository.count();
	}
	
	@Override public boolean removeCustomerByUserName(String userName) throws InvalidRequestException {
		Optional<UserBioData> foundBio = userBioDataRepository.findByUserName(userName);
		Optional<Boolean> optionalBooleanIsDeleted = foundBio.map(userBioData -> {
			Optional<Customer> foundCustomer = customerRepository.findByBioData(userBioData);
			if (foundCustomer.isPresent()) {
				customerRepository.deleteByBioData(userBioData);
				return true;
			}
			return false;
		});
		if (optionalBooleanIsDeleted.isPresent()){
			return optionalBooleanIsDeleted.get();
		}
		else throw new InvalidRequestException(String.format(INVALID_REQUEST_MESSAGE, "User", "username", userName));
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

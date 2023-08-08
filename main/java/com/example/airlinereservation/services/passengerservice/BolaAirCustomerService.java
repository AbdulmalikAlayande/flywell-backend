package com.example.airlinereservation.services.passengerservice;

import com.example.airlinereservation.data.model.Passenger;
import com.example.airlinereservation.data.model.UserBioData;
import com.example.airlinereservation.data.repositories.PassengerRepository;
import com.example.airlinereservation.data.repositories.UserBioDataRepository;
import com.example.airlinereservation.dtos.Request.LoginRequest;
import com.example.airlinereservation.dtos.Request.PassengerRequest;
import com.example.airlinereservation.dtos.Request.UpdateRequest;
import com.example.airlinereservation.dtos.Response.LoginResponse;
import com.example.airlinereservation.dtos.Response.PassengerResponse;
import com.example.airlinereservation.utils.appUtils.TokenGenerator;
import com.example.airlinereservation.utils.appUtils.Validator;
import com.example.airlinereservation.utils.exceptions.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static com.example.airlinereservation.utils.Exceptions.throwFailedRegistrationException;
import static com.example.airlinereservation.utils.Exceptions.throwInvalidRequestException;
import static com.example.airlinereservation.utils.appUtils.AppUtilities.*;

@Service
@AllArgsConstructor
@Slf4j
public class BolaAirCustomerService implements CustomerService {
	
	Validator validator;
	private PassengerRepository passengerRepository;
	private UserBioDataRepository userBioDataRepository;
	private ModelMapper mapper;
	
	
	@Override
	public PassengerResponse registerNewCustomer(@NotNull PassengerRequest passengerRequest) throws FailedRegistrationException {
		Field[] declaredFields = passengerRequest.getClass().getDeclaredFields();
		PassengerResponse passengerResponse = new PassengerResponse();
		if (userDoesNotExistBy(passengerRequest.getUserName())){
			try {
				checkForNullFields(declaredFields, passengerRequest);
				validateEmailAndPassword(passengerRequest.getEmail(), passengerRequest.getPassword());
				Passenger passenger = new Passenger();
				UserBioData biodata = new UserBioData();
				mapper.map(passengerRequest, biodata);
				biodata.setFullName(passengerResponse.getFullName());
				passenger.setUserBioData(biodata);
				passengerRepository.save(passenger);
				mapper.map(passenger.getUserBioData(), passengerResponse);
				return passengerResponse;
			} catch (Throwable throwable) {
				throwFailedRegistrationException(throwable);
			}
		}
		throw new FailedRegistrationException(DUPLICATE_ACCOUNT_REGISTRATION_FAILURE_MESSAGE);
	}
	
	private void validateEmailAndPassword(String email, String password) throws FieldInvalidException, InvalidRequestException {
		validator.validateEmail(email);
		validator.validatePassword(password);
	}
	
	private boolean userDoesNotExistBy(String userName) {
		Optional<UserBioData> foundBio = userBioDataRepository.findByUserName(userName);
		return foundBio.filter(userBioData ->
				       passengerRepository
					   .existsByUserBioData(userBioData))
				       .isEmpty();
	}
	
	private void checkForNullFields(Field[] declaredFields, PassengerRequest passengerRequest) {
		Arrays.stream(declaredFields)
			  .forEach(field -> {
				  String errorMessage = String.format(EMPTY_FIELD_MESSAGE, passengerRequest.getUserName());
				  try {
					  field.setAccessible(true);
					  Object accessesField = field.get(passengerRequest);
					  if (accessesField == null || (accessesField instanceof String && accessesField.toString().isEmpty()))
						  throw new EmptyFieldException(String.format(INCOMPLETE_DETAILS_MESSAGE, errorMessage));
				  }
				  catch (Exception e) {
					  throw new RuntimeException(e);
				  }
			  });
	}
	
	@Override
	public PassengerResponse updateDetailsOfRegisteredCustomer(@NotNull UpdateRequest updateRequest) {
		PassengerResponse response = new PassengerResponse();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setSkipNullEnabled(true);
		Optional<UserBioData> userBio = userBioDataRepository.findByUserName(updateRequest.getUserName());
		return userBio.map(userBioData -> {
				   modelMapper.map(updateRequest, userBioData);
				   if (updateRequest.getNewUserName() != null){
					   userBioData.setUserName(updateRequest.getNewUserName());
				   }
				   Optional<Passenger> foundPassenger = passengerRepository.findByUserBioData(userBioData);
				   foundPassenger.ifPresent(passenger -> {
					   passenger.setUserBioData(userBioData);
					   passengerRepository.save(passenger);
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
			Optional<Passenger> foundPassenger = passengerRepository.findByUserBioData(bioData);
			foundPassenger.ifPresent(passenger -> {
				if (passenger.isLoggedIn())
					response.set(loginResponse(SESSION_NOT_EXHAUSTED_MESSAGE, username));
				passenger.setLastLoggedIn(LocalDate.now());
				passenger.setLoggedIn(true);
				passenger.setToken(TokenGenerator.generateSessionToken());
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
			Optional<Passenger> foundPassenger = passengerRepository.findByUserBioData(foundBio.get());
			Optional<LoginResponse> optionalResponse = foundPassenger.map(passenger -> {
				if (passenger.isLoggedIn())
					return loginResponse(SESSION_NOT_EXHAUSTED_MESSAGE, foundBio.get().getUserName());
				passenger.setLastLoggedIn(LocalDate.now());
				passenger.setLoggedIn(true);
				passenger.setToken(TokenGenerator.generateSessionToken());
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
		Optional<PassengerResponse> response = findCustomerByEmailAndPassword(email, password);
		return response.isEmpty();
	}
	
	@Override public Optional<PassengerResponse> findCustomerById(String passengerId) {
		PassengerResponse response = new PassengerResponse();
		Optional<Passenger> foundPassenger = passengerRepository.findById(passengerId);
		return Optional.of(foundPassenger
				       .map(passenger -> {
						   mapper.map(passenger, response);
						   return response;
					   })
				       .orElseThrow(()-> {
					       try {
						       return throwInvalidRequestException(String.format(INVALID_REQUEST_MESSAGE, passengerId));
					       } catch (InvalidRequestException e) {
						       throw new RuntimeException(e);
					       }
				       }));
	}
	
	@Override
	public List<PassengerResponse> getAllCustomers() {
		List<PassengerResponse> responses = new ArrayList<>();
		List<Passenger> allPassengers = passengerRepository.findAll();
		allPassengers.forEach(passenger -> {
			PassengerResponse response = new PassengerResponse();
			mapper.map(passenger.getUserBioData(), response);
			responses.add(response);
		});
		return responses;
	}
	
	@Override public Optional<PassengerResponse> findCustomerByEmailAndPassword(String email, String password) {
		Optional<UserBioData> foundBio = userBioDataRepository.findByEmailAndPassword(email, password);
		PassengerResponse response = new PassengerResponse();
		foundBio.ifPresent(bioData -> {
			Optional<Passenger> foundPassenger = passengerRepository.findByUserBioData(bioData);
			foundPassenger.ifPresent(passenger -> mapper.map(bioData, response));
		});
		return Optional.of(response);
	}
	
	@Override public Optional<PassengerResponse> findCustomerByUserName(String userName) throws InvalidRequestException {
		PassengerResponse passengerResponse = new PassengerResponse();
		AtomicReference<PassengerResponse> response = new AtomicReference<>();
		Optional<UserBioData> foundBio = userBioDataRepository.findByUserName(userName);
		Optional<PassengerResponse> optionalPassengerResponse = foundBio.map(userBioData -> {
			Optional<Passenger> foundPassenger = passengerRepository.findByUserBioData(userBioData);
			foundPassenger.ifPresent(passenger -> {
				mapper.map(userBioData, passengerResponse);
				response.set(passengerResponse);
			});
			return response.get();
		});
		if (optionalPassengerResponse.isPresent())
			return optionalPassengerResponse;
		throw new InvalidRequestException(String.format(INVALID_REQUEST_MESSAGE, userName));
	}
	
	@Override public void removeCustomerById(String passengerId) throws InvalidRequestException {
		Optional<Passenger> foundPassenger = passengerRepository.findById(passengerId);
		if (foundPassenger.isEmpty())
			throw new InvalidRequestException(String.format(INVALID_REQUEST_MESSAGE, passengerId));
		passengerRepository.deleteById(passengerId);
	}
	
	@Override public long getCountOfCustomers() {
		return passengerRepository.count();
	}
	
	@Override public boolean removeCustomerByUserName(String userName) throws InvalidRequestException {
		Optional<UserBioData> foundBio = userBioDataRepository.findByUserName(userName);
		Optional<Boolean> optionalBooleanIsDeleted = foundBio.map(userBioData -> {
			Optional<Passenger> foundPassenger = passengerRepository.findByUserBioData(userBioData);
			if (foundPassenger.isPresent()) {
				passengerRepository.deleteByUserBioData(userBioData);
				return true;
			}
			return false;
		});
		if (optionalBooleanIsDeleted.isPresent()){
			return optionalBooleanIsDeleted.get();
		}
		else throw new InvalidRequestException(String.format(INVALID_REQUEST_MESSAGE, userName));
	}
	
	@Override
	public Optional<Passenger> findPassengerByUserNameForAdmin(String passengerUsername) {
		return Optional.empty();
	}
	
	@Override
	public void removeAll() {
		passengerRepository.deleteAll();
	}
}

package com.example.airlinereservation.services;

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
public class PassengerServiceImplementation implements PassengerService{
	
	Validator validator;
	private PassengerRepository passengerRepository;
	private UserBioDataRepository userBioDataRepository;
	private ModelMapper mapper;
	
	
	@Override
	public PassengerResponse registerNewPassenger(@NotNull PassengerRequest passengerRequest) throws FailedRegistrationException {
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
		throw new FailedRegistrationException("Registration Failed:: Seems Like You Already Have An Account With Us");
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
				  String errorMessage = "Field " + field.getName() + " is null or empty";
				  try {
					  field.setAccessible(true);
					  Object accessesField = field.get(passengerRequest);
					  if (accessesField == null || (accessesField instanceof String && accessesField.toString().isEmpty()))
						  throw new EmptyFieldException("Incomplete Details\n" + errorMessage);
				  }
				  catch (Exception e) {
					  throw new RuntimeException(e);
				  }
			  });
	}
	
	@Override
	public PassengerResponse updateDetailsOfRegisteredPassenger(@NotNull UpdateRequest updateRequest) {
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
				return throwInvalidRequestException("Update could not be completed");
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
	
	private LoginResponse logInViaEmailAndPassword(String email, String password) {
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
			loginResponse.set(optionalResponse.get());
		}
		return loginResponse.get();
	}
	
	private void validateLoginCredentialsIntegrity(@NotNull LoginRequest loginRequest) throws LoginFailedException {
		if (loginRequest.getUsername() == null && loginRequest.getEmail() == null)
			throw new LoginFailedException(INCORRECT_FORMAT_LOGIN_FAILURE_MESSAGE);
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
		Optional<PassengerResponse> response = findPassengerByEmailAndPassword(email, password);
		return response.isEmpty();
	}
	
	@Override public Optional<PassengerResponse> findPassengerById(String passengerId) {
		PassengerResponse response = new PassengerResponse();
		Optional<Passenger> foundPassenger = passengerRepository.findById(passengerId);
		return Optional.of(foundPassenger
				       .map(passenger -> {
						   mapper.map(passenger, response);
						   return response;
					   })
				       .orElseThrow(()-> {
					       try {
						       return throwInvalidRequestException("Invalid Request:: User with id "+passengerId+" not found");
					       } catch (InvalidRequestException e) {
						       throw new RuntimeException(e);
					       }
				       }));
	}
	
	@Override
	public List<PassengerResponse> getAllPassengers() {
		List<PassengerResponse> responses = new ArrayList<>();
		List<Passenger> allPassengers = passengerRepository.findAll();
		allPassengers.forEach(passenger -> {
			PassengerResponse response = new PassengerResponse();
			mapper.map(passenger.getUserBioData(), response);
			responses.add(response);
		});
		return responses;
	}
	
	@Override public Optional<PassengerResponse> findPassengerByEmailAndPassword(String email, String password) {
		Optional<UserBioData> foundBio = userBioDataRepository.findByEmailAndPassword(email, password);
		PassengerResponse response = new PassengerResponse();
		foundBio.ifPresent(bioData -> {
			Optional<Passenger> foundPassenger = passengerRepository.findByUserBioData(bioData);
			foundPassenger.ifPresent(passenger -> {
				mapper.map(bioData, response);
			});
		});
		return Optional.of(response);
	}
	
	@Override public Optional<PassengerResponse> findPassengerByUserName(String userName) {
		PassengerResponse passengerResponse = new PassengerResponse();
		Optional<UserBioData> foundBio = userBioDataRepository.findByUserName(userName);
		return foundBio.map(userBioData -> {
						   Optional<Passenger> foundPassenger = passengerRepository.findByUserBioData(userBioData);
						   return foundPassenger.map(passenger -> {
							   System.out.println("User bio data is:: "+userBioData.getUserName());
							   mapper.map(userBioData, passengerResponse);
							   return passengerResponse;
						   });
					   }).orElseThrow(() -> {
			try {
				return throwInvalidRequestException("Invalid Request:: User with username "+userName+" not found");
			} catch (InvalidRequestException e) {
				throw new RuntimeException(e);
			}
		});
	}
	
	@Override public void removePassengerBId(String passengerId) throws InvalidRequestException {
		Optional<Passenger> foundPassenger = passengerRepository.findById(passengerId);
		if (foundPassenger.isEmpty())
			throw new InvalidRequestException("Passenger with id: "+passengerId+" does not exists");
		passengerRepository.deleteById(passengerId);
	}
	
	@Override public long getCountOfPassengers() {
		return passengerRepository.count();
	}
	
	@Override public boolean removePassengerByUserName(String userName){
		Optional<UserBioData> foundBio = userBioDataRepository.findByUserName(userName);
		String message = "Passenger Not Found Incorrect User name";
		return foundBio.map(userBioData -> {
			Optional<Passenger> foundPassenger = passengerRepository.findByUserBioData(userBioData);
			foundPassenger.ifPresent(passenger-> passengerRepository.deleteByUserBioData(userBioData));
			return true;
		}).orElseThrow(()-> {
			try {
				return throwInvalidRequestException(message);
			} catch (InvalidRequestException e) {
				throw new RuntimeException(e);
			}
		});
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

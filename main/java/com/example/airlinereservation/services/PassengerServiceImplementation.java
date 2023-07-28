package com.example.airlinereservation.services;

import com.example.airlinereservation.data.model.Passenger;
import com.example.airlinereservation.data.model.UserBioData;
import com.example.airlinereservation.data.repositories.PassengerRepository;
import com.example.airlinereservation.data.repositories.UserBioDataRepository;
import com.example.airlinereservation.dtos.Request.PassengerRequest;
import com.example.airlinereservation.dtos.Request.UpdateRequest;
import com.example.airlinereservation.dtos.Response.PassengerResponse;
import com.example.airlinereservation.utils.appUtils.Validator;
import com.example.airlinereservation.utils.exceptions.EmptyFieldException;
import com.example.airlinereservation.utils.exceptions.FailedRegistrationException;
import com.example.airlinereservation.utils.exceptions.InvalidRequestException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.example.airlinereservation.utils.Exceptions.throwFailedRegistrationException;
import static com.example.airlinereservation.utils.Exceptions.throwInvalidRequestException;

@Service
@AllArgsConstructor
@Slf4j
public class PassengerServiceImplementation implements PassengerService{
	
	@Autowired
	Validator validator;
	private PassengerRepository passengerRepository;
	@Autowired
	private UserBioDataRepository userBioDataRepository;
	@Autowired
	private ModelMapper mapper;
	
	
	@Override
	public PassengerResponse registerNewPassenger(PassengerRequest passengerRequest) throws FailedRegistrationException {
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
	
	private void validateEmailAndPassword(String email, String password) {
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
				  catch (Throwable e) {
					throw new EmptyFieldException(e.getMessage());
				}
			  });
	}
	
	@Override public PassengerResponse updateDetailsOfRegisteredPassenger(UpdateRequest updateRequest) {
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
		}).orElseThrow(()->throwInvalidRequestException("Update could not be completed"));
		
	}
	
	@Override public Optional<PassengerResponse> findPassengerById(String passengerId) throws InvalidRequestException {
		PassengerResponse response = new PassengerResponse();
		Optional<Passenger> foundPassenger = passengerRepository.findById(passengerId);
		return Optional.of(foundPassenger
				       .map(passenger -> {
						   mapper.map(passenger, response);
						   return response;
					   })
				       .orElseThrow(()-> throwInvalidRequestException("Invalid Request:: User with id "+passengerId+" not found")));
	}
	
	public Optional<List<PassengerResponse>> getAllPassengersBy(String flightId) {
		return Optional.of(new ArrayList<>());
	}
	
	@Override
	public List<PassengerResponse> getAllPassengers() {
		List<PassengerResponse> responses = new ArrayList<>();
		List<Passenger> allPassengers = passengerRepository.findAll();
		mapper.addMappings(new PropertyMap<Passenger, PassengerResponse>() {
			@Override
			protected void configure() {
				map().setFirstName(source.getUserBioData().getFirstName());
				map().setLastName(source.getUserBioData().getLastName());
				map().setEmail(source.getUserBioData().getEmail());
			}
		});
		allPassengers.forEach(passenger -> {
			PassengerResponse response = new PassengerResponse();
			mapper.map(passenger, response);
			log.info("Response s:: {}", response);
			log.info("Response username is:: {}", response.getUserName());
			responses.add(response);
		});
		return responses;
	}
	
	@Override public Optional<PassengerResponse> findPassengerByEmailAndPassword(String email, String password) throws InvalidRequestException {
		return Optional.empty();
	}
	
	@Override public Optional<PassengerResponse> findPassengerByUserName(String userName) throws InvalidRequestException {
		PassengerResponse passengerResponse = new PassengerResponse();
		Optional<UserBioData> foundBio = userBioDataRepository.findByUserName(userName);
		return foundBio.map(userBioData -> {
						   Optional<Passenger> foundPassenger = passengerRepository.findByUserBioData(userBioData);
						   return foundPassenger.map(passenger -> {
							   System.out.println("User bio data is:: "+userBioData.getUserName());
							   mapper.map(userBioData, passengerResponse);
							   return passengerResponse;
						   });
					   }).orElseThrow(() -> throwInvalidRequestException("Invalid Request:: User with username "+userName+" not found"));
	}
	
	@Override public void removePassengerBId(String passengerId) throws InvalidRequestException {
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
			foundPassenger.ifPresent(x-> passengerRepository.deleteByUserBioData(userBioData));
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

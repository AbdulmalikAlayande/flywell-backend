package com.example.airlinereservation.services;

import com.example.airlinereservation.Mapper.Mapper;
import com.example.airlinereservation.data.model.*;
import com.example.airlinereservation.data.repositories.*;
import com.example.airlinereservation.dtos.Request.*;
import com.example.airlinereservation.dtos.Response.*;
import com.example.airlinereservation.utils.exceptions.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

import static com.example.airlinereservation.utils.Exceptions.*;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class PassengerServiceImplementation implements PassengerService{
	
	@Autowired
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
		System.out.println("HELLO TO ME");
		throw new FailedRegistrationException("Registration Failed:: Seems Like You Already Have An Account With Us");
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
				       .orElseThrow(()-> throwInvalidRequestException("Invalid Request:: User with username "+passengerId+" not found")));
	}
	
	public Optional<List<PassengerResponse>> getAllPassengersBy(String flightId) {
		return Optional.of(new ArrayList<>());
	}
	
	@Override
	public List<PassengerResponse> getAllPassengers() {
		List<PassengerResponse> responses = new ArrayList<>();
		for (int i = 0; i < passengerRepository.count(); i++) {
			if (passengerRepository.findAll().get(i) != null)
				responses.add(Mapper.map(passengerRepository.findAll().get(i)));
		}
		return responses;
	}
	
	@Override public Optional<PassengerResponse> findPassengerByEmailAndPassword(String email, String password) throws InvalidRequestException {
		return Optional.empty();
	}
	
	@Override public Optional<PassengerResponse> findPassengerByUserName(String userName) throws InvalidRequestException {
		PassengerResponse passengerResponse = new PassengerResponse();
		Optional<UserBioData> foundBio = userBioDataRepository.findByUserName(userName);
		
		return Optional.of(foundBio
				       .flatMap(userBioData -> {
						   Optional<Passenger> foundPassenger = passengerRepository.findByUserBioData(userBioData);
						   return foundPassenger.map(passenger -> {
							   mapper.map(foundBio, passengerResponse);
							   return passengerResponse;
						   });
					   })
				       .orElseThrow(()-> throwInvalidRequestException("Invalid Request:: User with username "+userName+" not found")));
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

package com.example.airlinereservation.services;

import com.example.airlinereservation.Mapper.Mapper;
import com.example.airlinereservation.data.model.*;
import com.example.airlinereservation.data.repositories.*;
import com.example.airlinereservation.dtos.Request.*;
import com.example.airlinereservation.dtos.Response.PassengerResponse;
import com.example.airlinereservation.utils.exceptions.*;
import com.example.airlinereservation.utils.mycustomannotations.AdminMethod;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.*;
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
	public PassengerResponse registerNewPassenger(PassengerRequest passengerRequest)throws FailedRegistrationException {
		Field[] declaredFields = passengerRequest.getClass().getDeclaredFields();
		PassengerResponse passengerResponse = new PassengerResponse();
		if (userDoesNotExistBy(passengerRequest.getUserName())){
			try {
				checkForNullFields(declaredFields, passengerRequest);
				Passenger passenger = new Passenger();
				UserBiodata biodata = new UserBiodata();
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
	
	private boolean userDoesNotExistBy(String userName) {
		Optional<UserBiodata> foundBio = userBioDataRepository.findByUserName(userName);
		return foundBio.filter(userBiodata ->
				       passengerRepository
					   .existsByUserBioData(userBiodata))
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
		return null;
	}
	
	@Override public Optional<PassengerResponse> findPassengerById(String passengerId) throws InvalidRequestException {
		PassengerResponse response = new PassengerResponse();
		Optional<Passenger> foundPassenger = passengerRepository.findById(passengerId);
		return Optional.of(foundPassenger
				                   .map(passenger -> {
									mapper.map(passenger, response);
									return response;
									})
				                   .orElseThrow(()->{
									   try {
											throw new InvalidRequestException("");
									   } catch (InvalidRequestException e) {
											throw new RuntimeException(e);
									   }
									}
		));
	}
	
	@Override public List<PassengerResponse> getAllPassengersBy(String flightId) {
		List<PassengerResponse> passengerResponses = new ArrayList<>();
		for (int i = 0; i < passengerRepository.count(); i++) {
//			if (passengerRepository.findAll().get(i) != null)
//				passengerResponses.add(Mapper.map(passengerRepository.findAll().get(i)));
		}
		return passengerResponses;
	}
	
	@Override public void removePassengerBId(String passengerId) throws InvalidRequestException {
		passengerRepository.deleteById(passengerId);
	}
	
	@Override public long getCountOfPassengers() {
		return passengerRepository.count();
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
		return Optional.empty();
	}
	
	@Override public boolean removePassengerByUserName(String userName){
		Optional<UserBiodata> foundBio = userBioDataRepository.findByUserName(userName);
		String message = "Passenger Not Found Incorrect User name";
		return foundBio.map(userBiodata -> {
			Optional<Passenger> foundPassenger = passengerRepository.findByUserBioData(userBiodata);
			foundPassenger.ifPresent(x-> passengerRepository.deleteByUserBioData(userBiodata));
			return true;
		}).orElseThrow(()-> {
			try {
				return throwInvalidRequestException(message);
			} catch (InvalidRequestException e) {
				throw new RuntimeException(e);
			}
		});
	}
	
	@Override @AdminMethod() public Optional<Passenger> findPassengerByUserNameForAdmin(String passengerUsername) {
		return Optional.empty();
	}
}

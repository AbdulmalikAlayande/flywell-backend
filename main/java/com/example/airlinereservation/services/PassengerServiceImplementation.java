package com.example.airlinereservation.services;

import com.example.airlinereservation.Mapper.Mapper;
import com.example.airlinereservation.data.model.*;
import com.example.airlinereservation.data.repositories.*;
import com.example.airlinereservation.dtos.Request.*;
import com.example.airlinereservation.dtos.Response.PassengerResponse;
import com.example.airlinereservation.utils.exceptions.*;
import com.example.airlinereservation.utils.mycustomannotations.AdminMethod;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

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
		try {
			System.out.println(Arrays.toString(declaredFields));
			System.out.println("Sodiq");
			checkForNullFields(declaredFields);
			Passenger passenger = new Passenger();
			UserBiodata biodata = new UserBiodata();
			mapper.map(passengerRequest, biodata);
			biodata.setFullName(passengerResponse.getFullName());
			passenger.setUserBioData(biodata);
			passengerRepository.save(passenger);
			mapper.map(passenger.getUserBioData(), passengerResponse);
			return passengerResponse;
		}
		catch (Throwable throwable){
			throw new FailedRegistrationException("Registration Failed "+ throwable.getMessage()+"\n"+throwable.getCause());
		}
	}
	
	private void checkForNullFields(Field[] declaredFields) {
		Arrays.stream(declaredFields).forEach(System.out::println);
		List<Field> listOfNullValues = Arrays.stream(declaredFields).filter(Objects::isNull).toList();
		listOfNullValues.stream().findAny().ifPresent(x->{
			String name = x.getName();
			System.out.println(x);
			System.out.println(name);
			throw new EmptyFieldException("Field is empty");
		});
	}
	
	@Override public PassengerResponse updateDetailsOfRegisteredPassenger(UpdateRequest updateRequest) {
		return null;
	}
	
	@Override public Optional<PassengerResponse> findPassengerById(String passengerId) throws InvalidRequestException {
		Optional<Passenger> foundPassenger = passengerRepository.findById(passengerId);
		return Optional.ofNullable(foundPassenger.map(passenger -> new PassengerResponse()).orElseThrow(() -> {
			try {
				throw new InvalidRequestException("Error: Incorrect Id");
			} catch (InvalidRequestException e) {
				throw new RuntimeException(e);
			}
		}));
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
				return throwInvalidRequestException (message);
			} catch (InvalidRequestException e) {
				throw new RuntimeException(e);
			}
		});
	}
	
	
	@Override @AdminMethod() public Optional<Passenger> findPassengerByUserNameForAdmin(String passengerUsername) {
		return Optional.empty();
	}
	
	private static RuntimeException throwInvalidRequestException(String message) throws InvalidRequestException {
		throw new InvalidRequestException(message);
	}
}

package services;

import Mapper.Mapper;
import data.model.Passenger;
import data.repositories.PassengerRepository;
import data.repositories.PassengerRepositoryImpl;
import dtos.Request.PassengerRequest;
import dtos.Request.UpdateRequest;
import dtos.Response.PassengerResponse;
import utils.exceptions.FailedRegistrationException;
import utils.exceptions.InvalidRequestException;
import utils.mycustomannotations.AdminMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class PassengerServiceImplementation implements PassengerService{
	private static PassengerService instance = null;
	private final PassengerRepository passengerRepository = new PassengerRepositoryImpl();
	private PassengerServiceImplementation(){}
	
	public static PassengerService getInstance() {
		if (instance == null){
			instance = new PassengerServiceImplementation();
		}
		return instance;
	}
	
	@Override public PassengerResponse registerNewPassenger(PassengerRequest passengerRequest)throws FailedRegistrationException {
		validateThatPassengerRequestDoesNotHaveAnyNullField(passengerRequest);
		Passenger mappedPassenger = Mapper.map(passengerRequest);
		if (emailIsValid(mappedPassenger.getEmail()) && passwordIsValid(mappedPassenger.getPassword()))
			return Mapper.map(passengerRepository.savePassenger(mappedPassenger));
		throw new FailedRegistrationException("Registration Failed");
	}
	
	@Override public PassengerResponse updateDetailsOfRegisteredPassenger(UpdateRequest updateRequest) {
		Passenger foundPassenger = passengerRepository.findPassengerByUserName(updateRequest.getUserName());
		foundPassenger.setUserName(updateRequest.getUserName());
		if (updateRequest.getEmail() != null) foundPassenger.setEmail(updateRequest.getEmail());
		if (updateRequest.getFirstName() != null) foundPassenger.setFirstName(updateRequest.getFirstName());
		if (updateRequest.getLastName() != null) foundPassenger.setLastName(updateRequest.getLastName());
		if (updateRequest.getPassword() != null) foundPassenger.setPassword(updateRequest.getPassword());
		if (updateRequest.getPhoneNumber() != null) foundPassenger.setPhoneNumber(updateRequest.getPhoneNumber());
		return Mapper.map(passengerRepository.savePassenger(foundPassenger));
	}
	
	@Override public PassengerResponse findPassengerById(String passengerId) throws InvalidRequestException {
		Passenger foundPassenger = passengerRepository.getPassengerById(passengerId);
		if (foundPassenger == null)
			throw new InvalidRequestException("Error: Incorrect Id");
		return Mapper.map(passengerRepository.getPassengerById(passengerId));
		
	}
	
	@Override
	public Passenger findPassengerByIdForAdmin(String passengerId) throws InvalidRequestException {
		Passenger foundPassenger = passengerRepository.getPassengerById(passengerId);
		if (foundPassenger == null)
			throw new InvalidRequestException("Error: Incorrect Id");
		return foundPassenger;
	}
	
	@Override public List<PassengerResponse> getAllPassengersBy(String flightId) {
		List<PassengerResponse> passengerResponses = new ArrayList<>();
		for (int i = 0; i < passengerRepository.getCountOfPassengers(); i++) {
			if (passengerRepository.getAllPassengers().get(i) != null)
				passengerResponses.add(Mapper.map(passengerRepository.getAllPassengers().get(i)));
		}
		return passengerResponses;
	}
	
	@Override public void removePassengerBId(String passengerId) throws InvalidRequestException {
		boolean isDeleted = passengerRepository.removePassenger(passengerId);
		if (!isDeleted) throw new InvalidRequestException("Error: Invalid Password");
	}
	
	@Override public int getCountOfPassengers() {
		return passengerRepository.getCountOfPassengers();
	}
	
	@Override
	public List<PassengerResponse> getAllPassengers() {
		List<PassengerResponse> responses = new ArrayList<>();
		for (int i = 0; i < passengerRepository.getCountOfPassengers(); i++) {
			if (passengerRepository.getAllPassengers().get(i) != null)
				responses.add(Mapper.map(passengerRepository.getAllPassengers().get(i)));
		}
		return responses;
	}
	
	@Override public PassengerResponse findPassengerByEmailAndPassword(String email, String password) throws InvalidRequestException {
		Passenger foundPassenger = passengerRepository.findPassengerByEmailAndPassword(email, password);
		if (foundPassenger == null)
			throw new InvalidRequestException("ERROR: Not found due to invalid email or password");
		return Mapper.map(foundPassenger);
	}
	
	@Override public PassengerResponse findPassengerByUserName(String userName) throws InvalidRequestException {
		Passenger foundPassenger = passengerRepository.findPassengerByUserName(userName);
		if (foundPassenger == null)
			throw new InvalidRequestException("ERROR: Name Not Found");
		return Mapper.map(foundPassenger);
	}
	
	@Override @AdminMethod public Passenger findAPassengerByUserName(String userName) {
		return passengerRepository.findPassengerByUserName(userName);
	}
	
	@Override public boolean removePassengerByUserName(String userName) throws InvalidRequestException {
		boolean isDeletedPassenger = passengerRepository.removePassengerByUserName(userName);
		if (!isDeletedPassenger)
			throw new InvalidRequestException("ERROR: Invalid username");
		return true;
	}
	
	@Override @AdminMethod() public Passenger findPassengerByUserNameForAdmin(String passengerUsername) {
		return passengerRepository.findPassengerByUserName(passengerUsername);
	}
	
	private boolean passwordIsValid(String password) {
		boolean isValidPassword = false;
		String regexPattern = "@,%,&,^,$,#,!,(,)()";
		String[] specialCharacterSplit = regexPattern.split(",",8);
		for (String character : specialCharacterSplit) {
			if (password.contains(character)) {
				isValidPassword = true;
				break;
			}
		}
		if (!isValidPassword)
			throw new IllegalArgumentException("""
				Invalid password format\040
				password must contain special characters\040
				like: @, %, &, ^, $, #, !, (, ), ()
				""");
		return true;
	}
	
	private boolean emailIsValid(String email) {
		Pattern regexFormat = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
		List<String> allowedDomains = List.of(new String[]{"gmail.com", "yahoo.com", "outlook.com"});
		if (!regexFormat.matcher(email).matches()) throw new IllegalArgumentException("This is not a valid email address");
		String[] emailSplit = email.split("@");
		String domains = emailSplit[1].toLowerCase();
		boolean domainIsValid = false;
		
		for (String allowedDomain: allowedDomains) {
			if (domains.contains(allowedDomain)) {
				domainIsValid = true;
				break;
			}
		}
		if (!domainIsValid) throw new IllegalArgumentException("This is not a valid email address");
		return true;
	}
	
	private void validateThatPassengerRequestDoesNotHaveAnyNullField(PassengerRequest passengerRequest) {
		if (passengerRequest.getEmail() == null || passengerRequest.getUserName() == null || passengerRequest.getPassword() == null
				    || passengerRequest.getPhoneNumber() == null || passengerRequest.getLastName() == null || passengerRequest.getFirstName() == null){
			throw new IllegalArgumentException("Please fill the empty field");
		}
	}
}

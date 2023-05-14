package services;

import Mapper.Mapper;
import data.model.Passenger;
import data.repositories.PassengerRepository;
import data.repositories.PassengerRepositoryImpl;
import dtos.Request.PassengerRequest;
import dtos.Request.UpdateRequest;
import dtos.Response.PassengerResponse;

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
	
	@Override
	public PassengerResponse registerNewPassenger(PassengerRequest passengerRequest) {
		Passenger mappedPassenger = Mapper.map(passengerRequest);
		if (emailIsValid(mappedPassenger.getEmail()) && passwordIsValid(mappedPassenger.getPassword())) {
			Passenger savedPassenger = passengerRepository.savePassenger(mappedPassenger);
			return Mapper.map(savedPassenger);
		}
		throw new IllegalArgumentException("Invalid email or password");
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
		if (!isValidPassword) {
			throw new IllegalArgumentException("Invalid password format" +
					                                   "password must contain special characters like: @, %, &, ^, $, #, !, (, ), ()");
		}
		return isValidPassword;
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
	
	@Override
	public PassengerResponse updateDetailsOfRegisteredPassenger(UpdateRequest updateRequest) {
		Passenger foundPassenger = passengerRepository.findPassengerByUserName(updateRequest.getUserName());
		if (updateRequest.getEmail() != null) foundPassenger.setEmail(updateRequest.getEmail());
		if (updateRequest.getUserName() != null) foundPassenger.setUserName(updateRequest.getUserName());
		if (updateRequest.getFirstName() != null) foundPassenger.setFirstName(updateRequest.getFirstName());
		if (updateRequest.getLastName() != null) foundPassenger.setLastName(updateRequest.getLastName());
		if (updateRequest.getPassword() != null) foundPassenger.setPassword(updateRequest.getPassword());
		if (updateRequest.getPhoneNumber() != null) foundPassenger.setPhoneNumber(updateRequest.getPhoneNumber());
		return Mapper.map(passengerRepository.savePassenger(foundPassenger));
	}
	
	@Override
	public PassengerResponse findPassengerById(String passengerId) {
		return null;
	}
	
	@Override
	public List<PassengerResponse> getAllPassengersBy(String flightId) {
		return null;
	}
	
	@Override
	public boolean removePassengerBId(String passengerId) {
		return false;
	}
	
	@Override
	public int getCountOfPassengers() {
		return passengerRepository.getCountOfPassengers();
	}
	
	@Override
	public PassengerResponse findPassengerByEmailAndPassword(String email, String password) {
		Passenger foundPassenger = passengerRepository.findPassengerByEmailAndPassword(email, password);
		if (foundPassenger != null) return Mapper.map(foundPassenger);
		throw new RuntimeException("ERROR: Not found due to invalid email or password");
	}
	
	@Override
	public PassengerResponse findPassengerByUserName(String userName) {
		Passenger foundPassenger = passengerRepository.findPassengerByUserName(userName);
		System.out.println("The found passenger is: "+ foundPassenger);
		if (foundPassenger == null)
			throw new RuntimeException("ERROR: Name Not Found");
		return Mapper.map(foundPassenger);
	}
	
	@Override
	public boolean removePassengerByUserName(String userName) {
		boolean isDeletedPassenger = passengerRepository.removePassengerByUserName(userName);
		if (!isDeletedPassenger)
			throw new RuntimeException("ERROR: Invalid username");
		return true;
	}
}

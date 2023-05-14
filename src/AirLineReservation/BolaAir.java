package AirLineReservation;
import data.model.*;
import utils.appUtils.IdLetterCharacter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;


public class BolaAir {
	TravelClassBooking travelClassBooking = new TravelClassBooking();
	AirplaneSeats airplaneSeats = new AirplaneSeats();
	
	private final List<Passenger> passengers = new ArrayList<>();
	int firstClassSeatCounter;
	int businessClassSeatCounter;
	int premiumEconomyClassSeatCounter;
	int economyClassSeatCounter;
	int lastFirstClassSeat = 4;  int lastBusinessClassSeat = 9;
	int lastPremiumEconomyClassSeat = 14;  int lastEconomyClassSeat = 19;
	
	private void bookFlight(int bookingCategory) {
		validateBookingCategory(bookingCategory);
		checkWhetherSeatsAreStillAvailable(bookingCategory);
		if (bookingCategory == TravelClass.FIRST_CLASS.ordinal() && !AirplaneSeats.getSeats(lastFirstClassSeat))
			bookFirstClassSeat();
		if (bookingCategory == TravelClass.BUSINESS_CLASS.ordinal() && (!airplaneSeats.getSeats()[lastBusinessClassSeat]))
			bookBusinessClassSeat();
		if (bookingCategory == TravelClass.PREMIUM_ECONOMY_CLASS.ordinal() && !airplaneSeats.getSeats()[lastPremiumEconomyClassSeat])
			bookPremiumEconomyClassSeat();
		if (bookingCategory == TravelClass.ECONOMY_CLASS.ordinal() && !airplaneSeats.getSeats()[lastEconomyClassSeat])
			bookEconomyClassSeat();
	}
	
	private void checkWhetherSeatsAreStillAvailable(int bookingCategory) {
		if (bookingCategory == TravelClass.FIRST_CLASS.ordinal() && AirplaneSeats.getSeats(lastFirstClassSeat))
			throw new RuntimeException("Section is filled");
		else if (bookingCategory == TravelClass.BUSINESS_CLASS.ordinal() && airplaneSeats.getSeats()[lastBusinessClassSeat])
			throw new RuntimeException("Section is filled");
		else if (bookingCategory == TravelClass.PREMIUM_ECONOMY_CLASS.ordinal() && airplaneSeats.getSeats()[lastPremiumEconomyClassSeat])
			throw new RuntimeException("Section is filled");
		else if (bookingCategory == TravelClass.ECONOMY_CLASS.ordinal() && airplaneSeats.getSeats()[lastEconomyClassSeat] == true)
			throw new RuntimeException("Section is filled");
	}
	
	public void bookFlight(int bookingCategory, Passenger passenger){
		Passenger foundPassenger = findUserByEmail(passenger.getEmail());
		if (passengers.contains(foundPassenger))
			bookFlight(bookingCategory);
		else {
			bookFlight(bookingCategory);
		}
		/*todo: i need to refactor this method but for now let's work with it,
		   i will refactor it when am done*/
	}
	
	private void validateBookingCategory(int bookingCategory) {
		boolean isFirstClass = bookingCategory == TravelClass.FIRST_CLASS.ordinal();
		boolean isBusinessClass = bookingCategory == TravelClass.BUSINESS_CLASS.ordinal();
		boolean isPremiumEconomyClass = bookingCategory == TravelClass.PREMIUM_ECONOMY_CLASS.ordinal();
		boolean isEconomyClass = bookingCategory == TravelClass.ECONOMY_CLASS.ordinal();
		
		boolean isValidBookingCategory = isFirstClass || isBusinessClass || isEconomyClass || isPremiumEconomyClass;
		if(!isValidBookingCategory)
			throw new IllegalArgumentException("Invalid booking category");
	}
	
	public void bookFirstClassSeat(){
		travelClassBooking.bookTravelClassSeat(TravelClass.FIRST_CLASS.ordinal());
		firstClassSeatCounter++;
	}
	public int firstClassSeatCounter(){
		return firstClassSeatCounter;
	}
	public void bookBusinessClassSeat(){
		travelClassBooking.bookTravelClassSeat(5);
		businessClassSeatCounter++;
	}
	public int businessClassSeatCounter(){
		return businessClassSeatCounter;
	}
	public void bookPremiumEconomyClassSeat(){
		travelClassBooking.bookTravelClassSeat(10);
		premiumEconomyClassSeatCounter++;
	}
	public int premiumEconomyClassSeatCounter() {
		return premiumEconomyClassSeatCounter;
	}
	
	public void bookEconomyClassSeat() {
		travelClassBooking.bookTravelClassSeat(15);
		economyClassSeatCounter++;
	}
	
	public int economyClassSeatCounter() {
		return economyClassSeatCounter;
	}
	
	public Passenger registerNewUser(Passenger passenger) {
		passenger.setId(generateId());
		validateEmail(passenger.getEmail());
		passengers.add(passenger);
		return passenger;
	}
	
	private String generateId() {
		int counter = 0;
		int idRecognizer = IdLetterCharacter.values().length;;
		String userId =  idRecognizer+ counter + getNoOfPassengers() + IdLetterCharacter.getCharacter();
		if (getNoOfPassengers()  % 10 == 0){
			counter++;
			userId =  idRecognizer+ counter + getNoOfPassengers() + IdLetterCharacter.getCharacter();
		}
		return userId;
	}
	
	public boolean validateEmail(String email){
		Pattern regexFormat = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
		
		List<String> allowedDomains = List.of(new String[]{"gmail.com", "yahoo.com", "outlook.com"});
		
		if (!regexFormat.matcher(email).matches()) {
			throw new IllegalArgumentException("This is not a valid email address");
		}
		String[] emailSplit = email.split("@");
		String domains = emailSplit[1].toLowerCase();
		boolean domainIsValid = false;
		
		for (String allowedDomain: allowedDomains) {
			if (domains.contains(allowedDomain)) {
				domainIsValid = true;
				break;
			}
		}
		if (!domainIsValid) {
			throw new IllegalArgumentException("This is not a valid email address");
		}
		return true;
	}
	
	public Passenger findUserByEmail(String passengerEmail){
		for (Passenger eachPassenger : passengers) {
			boolean isFoundUser = Objects.equals(passengerEmail, eachPassenger.getEmail());
			if (isFoundUser) return eachPassenger;
		}
		return null;
	}
	
	public int getNoOfPassengers() {
		return passengers.size();
	}
	
	public FlightForm fillFlightForm(FlightForm flightForm) {
		FlightForm form = new FlightForm();
		flightForm.getFlightPrice();
		return null;
	}
}

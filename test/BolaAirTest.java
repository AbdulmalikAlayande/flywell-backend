import AirLineReservation.*;
import data.model.AirplaneSeats;
import data.model.Passenger;
import utils.DateTime.Date;
import data.model.FlightForm;
import data.model.TravelClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.Arrays;

import static data.model.AirplaneSeats.getSeats;
import static org.junit.jupiter.api.Assertions.*;


class BolaAirTest {
	
	BolaAir airLine;
	Passenger passenger;
	AirplaneSeats airplaneSeats;
	
	@BeforeEach
	void setUp() {
		airLine = new BolaAir();
		passenger = new Passenger();
		airplaneSeats = new AirplaneSeats();
	}
	
	@Test
	@DisplayName("Number of seats on airplane test")
	public void AirplaneSeatIsTwentyTest(){
		assertEquals(20, airplaneSeats.getNumberOfSeats());
		System.out.println(Arrays.toString(getSeats()));
	}
	
	@Test
	@DisplayName("Initial state of all seats on airplane is unoccupied test")
	void AllAirplaneSeatIsInitiallyFalseTest(){
		boolean[] seats = getSeats();
		boolean[] expectedSeats = {false, false, false, false, false, false, false, false, false, false,
				false, false, false, false, false, false, false, false, false, false};
		assertArrayEquals(seats, expectedSeats);
		assertTrue(airplaneSeats.getInitialStateOfSeat());
	}
	
	@Test
	@DisplayName("Booking Test")
	void testThatUsersCanBookATravelClass(){
		assertEquals(0, TravelClass.FIRST_CLASS.ordinal());
		assertEquals(1, TravelClass.BUSINESS_CLASS.ordinal());
		assertEquals(2, TravelClass.PREMIUM_ECONOMY_CLASS.ordinal());
		assertEquals(3, TravelClass.ECONOMY_CLASS.ordinal());
	}
	
	@Test
	@DisplayName("First Class Booking Test")
	void testThatUsersCanBookAFirstClass(){
		airLine.bookFlight(0, passenger);
		airLine.bookFlight(0, passenger);
		airLine.bookFlight(0, passenger);
		airLine.bookFlight(0, passenger);
		airLine.bookFlight(0, passenger);
		assertEquals(5, airLine.firstClassSeatCounter());
	}
	@Test
	@DisplayName("Business Class Booking Test")
	void testThatUsersCanBookABusinessClass(){
		BolaAir airline12 = new BolaAir();
		airline12.bookFlight(1, passenger);
		airline12.bookFlight(1, passenger);
		airline12.bookFlight(1, passenger);
		airline12.bookFlight(1, passenger);
		airline12.bookFlight(1, passenger);
		airline12.bookFlight(1, passenger);
		airline12.bookFlight(1, passenger);
		assertEquals(4, airline12.businessClassSeatCounter());
	}
	@Test
	@DisplayName("Premium Economic Class Booking Test")
	void testThatUsersCanBookAPremiumEconomyClass(){
		BolaAir airLine3 = new BolaAir();
		airLine3.bookFlight(2, passenger);
		airLine3.bookFlight(2, passenger);
		airLine3.bookFlight(2, passenger);
		airLine3.bookFlight(2, passenger);
		airLine3.bookFlight(2, passenger);
		assertEquals(5, airLine3.premiumEconomyClassSeatCounter());
	}
	
	@Test
	@DisplayName("Economic Class Booking Test")
	void testThatUsersCanBookAnEconomyClass(){
		BolaAir airLine7 = new BolaAir();
		airLine7.bookFlight(3, passenger);
		airLine7.bookFlight(3, passenger);
		airLine7.bookFlight(3, passenger);
		airLine7.bookFlight(3, passenger);
		airLine7.bookFlight(3, passenger);
		airLine7.bookFlight(3, passenger);
		airLine7.bookFlight(3, passenger);
		airLine7.bookFlight(3, passenger);
		assertEquals(5, airLine7.economyClassSeatCounter());
	}
	
	@Test
	@DisplayName("Invalid Booking Category Test")
	void testThatExceptionIsThrownWhenUserEntersAnInvalidBookingCategory(){
		int invalidBookingCategory = 5;
		assertThrows(RuntimeException.class, ()->airLine.bookFlight(invalidBookingCategory, passenger));
	}
	
	@Test
	@DisplayName("Filled Section Booking Test")
	public void testThatExceptionIsThrownWhenUserTriesToBookASectionWhichIsFilled(){
		BolaAir airLine4 = new BolaAir();
		airLine4.bookFlight(3, passenger);
		airLine4.bookFlight(3, passenger);
		airLine4.bookFlight(3, passenger);
		airLine4.bookFlight(3, passenger);
		airLine4.bookFlight(3, passenger);
		assertThrows(RuntimeException.class, () -> airLine4.bookFlight(3, passenger));
	}
	
	@Test
	@DisplayName("registration test")
	public void newUserCanRegisterAnAccountWithTheAirlineTest(){
		Passenger passenger = new Passenger();
		Passenger passenger1 = new Passenger();
		Passenger passenger2 = new Passenger();
		
		passenger.setFirstName("Abdulmalik");
		passenger.setLastName("Alayande");
		passenger.setFullName();
		passenger.setEmail("alaabdulmalik03@gmail.com");
		passenger.setPhoneNumber("07036174617");
		airLine.registerNewUser(passenger);
		
		passenger1.setFirstName("Abdul");
		passenger1.setLastName("Alayande");
		passenger1.setFullName();
		passenger1.setEmail("alaabdulmalik03@outlook.com");
		passenger1.setPhoneNumber("07041774617");
		airLine.registerNewUser(passenger1);
		
		passenger2.setFirstName("Alabi");
		passenger2.setLastName("Alayande");
		passenger2.setFullName();
		passenger2.setEmail("alaabdulmalik03@yahoo.com");
		passenger2.setPhoneNumber("08054674617");
		airLine.registerNewUser(passenger2);
		
		assertEquals(3, airLine.getNoOfPassengers());
		System.out.println(passenger+"\n"+passenger1+"\n"+passenger2);
	}
	
	@Test
	@Disabled
	@DisplayName("Flight Form generation test")
	public void testThatUserCanFillFlightForm(){
		BolaAir airLine1 = new BolaAir();
		FlightForm form = new FlightForm();
		Passenger passenger1 = new Passenger();
		Date date = new Date();
		passenger1.setFirstName("Abdul");
		passenger1.setLastName("Alayande");
		passenger1.setFullName();
		passenger1.setEmail("alaabdulmalik03@outlook.com");
		passenger1.setPhoneNumber("07041774617");
		
		Date arrivalDate = date.setDate(20, 11, 1994);
		Date departureDate = date.setDate(19, 11, 1994);
		LocalTime setTime = LocalTime.of(12, 34, 10);
		form.setPassengerName(passenger1.getFullName());
		form.setArrivalDate(arrivalDate);
		form.setDepartureDate(departureDate);
		form.setArrivalTime(setTime);
		form.setDestination("Ohio");
		assertEquals(form, airLine1.fillFlightForm(form));
	 }
	 
	 @Test
	 public void testThatUserIdIsBeingGeneratedEachTimeAUserRegistersANewAccount(){
		 passenger.setFirstName("Abdulmalik");
		 passenger.setLastName("Alayande");
		 passenger.setFullName();
		 passenger.setEmail("alaabdulmalik03@gmail.com");
		 passenger.setPhoneNumber("07036174617");
		 airLine.registerNewUser(passenger);
		 airLine.bookFlight(1, passenger);
		 assertNotNull(passenger.getId());
	}
	
	@Test
	public void testThatUserHasToRegisterBeforeBookingAFlight(){
		BolaAir airline12 = new BolaAir();
		Passenger passenger = new Passenger();
		Passenger passenger1 = new Passenger();
		Passenger passenger2 = new Passenger();
		
		passenger.setFirstName("Abdulmalik");
		passenger.setLastName("Alayande");
		passenger.setFullName();
		passenger.setEmail("alaabdulmalik03@gmail.com");
		passenger.setPhoneNumber("07036174617");
		airline12.registerNewUser(passenger);
		airline12.bookFlight(2, passenger);
		
		passenger1.setFirstName("Abdul");
		passenger1.setLastName("Alayande");
		passenger1.setFullName();
		passenger1.setEmail("alaabdulmalik03@outlook.com");
		passenger1.setPhoneNumber("07041774617");
		airline12.registerNewUser(passenger1);
		airline12.bookFlight(1, passenger1);
		
		airline12.bookFlight(3, passenger2);
		
		assertEquals(2, airline12.getNoOfPassengers());
//		assertEquals(1, airline12.bookBusinessClassSeat());
//		assertEquals(1, airline12.bookPremiumEconomyClassSeat());
//		assertEquals(1, airline12.bookEconomyClassSeat());
	}
}
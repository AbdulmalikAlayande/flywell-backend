package AirLineReservationTest;

import data.model.AirplaneSeats;
import AirLineReservation.BolaAir;
import data.model.Passenger;
import data.model.TravelClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class SecondBolaAirTest {
	
	BolaAir airLine;
	Passenger passenger;
	AirplaneSeats airplaneSeats;
	
	@BeforeEach
	void setUp() {
		airLine = new BolaAir();
		passenger = new Passenger();
		airplaneSeats = new AirplaneSeats();
		passenger.setFirstName("Abdulmalik");
		passenger.setLastName("Alayande");
		passenger.setEmail("alaabdulmalik03@gmail.com");
		passenger.setFullName();
	}
	@Test
	@DisplayName("Number of seats on airplane test")
	public void AirplaneSeatIsTwentyTest(){
		assertEquals(20, airplaneSeats.getNumberOfSeats());
	}
	
	@Disabled
	@Test
	@DisplayName("Initial state of all seats on airplane is unoccupied test")
	public void AllAirplaneSeatIsInitiallyFalseTest(){
		boolean[] seats = AirplaneSeats.getSeats();
		assertEquals(20, airplaneSeats.getNumberOfSeats());
		boolean[] expectedSeats = {false, false, false, false, false, false, false, false, false, false,
				false, false, false, false, false, false, false, false, false, false};
		System.out.println("This is Seats: "+ Arrays.toString(seats));
		System.out.println("This is expected seats: "+ Arrays.toString(expectedSeats));
		assertArrayEquals(seats, expectedSeats);
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
	@DisplayName("First Class booking test")
	void testThatUsersCanBookAFirstClass(){
		for (int i = 0; i < 5; i++){
			airLine.bookFlight(0, passenger);
		}
		assertTrue(AirplaneSeats.getSeats(0));
		assertTrue(AirplaneSeats.getSeats(4));
		assertEquals(5, airLine.firstClassSeatCounter());
	}
	
	@Test
	@DisplayName("Business Class Booking Seat")
	void testThatUsersCanBookABusinessClassSeat(){
		for (int i = 0; i < 5; i++){
			airLine.bookFlight(1, passenger);
		}
		assertTrue(AirplaneSeats.getSeats(5));
		assertTrue(AirplaneSeats.getSeats(9));
		assertEquals(5, airLine.businessClassSeatCounter());
	}

	@Test
	@DisplayName("Premium Economy Class Booking Seat")
	void testThatUsersCanBookAPremiumEconomyClassSeat(){
		for (int i = 0; i < 5; i++){
			airLine.bookFlight(2, passenger);
		}
		assertTrue(AirplaneSeats.getSeats(10));
		assertTrue(AirplaneSeats.getSeats(14));
		assertEquals(5, airLine.premiumEconomyClassSeatCounter());
	}
	@Test
	@DisplayName("Economy Class Booking Seat")
	void testThatUsersCanBookAnEconomyClassSeat(){
		for (int i = 0; i < 5; i++){
			airLine.bookFlight(3, passenger);
		}
		assertTrue(AirplaneSeats.getSeats(15));
		assertTrue(AirplaneSeats.getSeats(19));
		assertEquals(5, airLine.economyClassSeatCounter());
	}
	
	@Test
	@DisplayName("Invalid Booking Category Test")
	void testThatExceptionIsThrownWhenUserEntersAnInvalidBookingCategory(){
		int invalidBookingCategory = 5;
		assertThrows(IllegalArgumentException.class, ()->airLine.bookFlight(invalidBookingCategory, passenger));
	}
	
	@Disabled
	@Test void testThatExceptionIsThrownWhenYouTryToBookAFlightForFilledSection(){
		for (int i = 0; i < 5; i++){
			airLine.bookFlight(3, passenger);
		}
		assertThrows(RuntimeException.class, () -> airLine.bookFlight(3, passenger));
	}
}

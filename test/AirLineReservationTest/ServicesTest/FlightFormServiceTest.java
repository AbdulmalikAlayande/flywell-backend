package AirLineReservationTest.ServicesTest;

import data.model.Flight;
import dtos.Request.BookingRequest;
import dtos.Request.FlightFormRequest;
import dtos.Request.FlightRequest;
import dtos.Request.PassengerRequest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import services.Bookable;
import services.FlightBooking;
import services.FlightFormService;
import services.PassengerService;

public class FlightFormServiceTest {
	FlightFormService flightFormService;
	Bookable bookable;
	BookingRequest bookingRequest;
	PassengerService passengerService;
	PassengerRequest passengerRequest;
	
	FlightFormRequest flightFormRequest;
	@SneakyThrows
	@BeforeEach void startAlltestWith(){
		bookable = new FlightBooking();
		passengerRequest = PassengerRequest.builder()
				                   .userName("dende")
				                   .Email("alaabdulmalik03@gmail.com")
				                   .firstName("Abdulmalik")
				                   .lastName("Alayande")
				                   .password("ayanniyi@20")
				                   .phoneNumber("07036174617")
				                   .build();
		passengerService.registerNewPassenger(passengerRequest);
		bookingRequest = BookingRequest.builder()
				                .bookingCategory(1)
				                .passengerUsername("dende")
				                .build();
		Flight bookedFlight = bookable.bookFlight(bookingRequest);
		flightFormRequest = FlightFormRequest.builder()
				                    .flight(bookedFlight)
				                    .passenger(passengerService.findAPassengerByUserName(passengerRequest.getUserName()))
				                    .build();
		flightFormService.saveFlightForm(flightFormRequest);
	}
}

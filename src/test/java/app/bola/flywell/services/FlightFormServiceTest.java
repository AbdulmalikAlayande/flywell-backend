package app.bola.flywell.services;

import app.bola.flywell.data.model.flight.Flight;
import app.bola.flywell.dtos.Request.*;
import app.bola.flywell.services.flightformservice.FlightFormService;
import app.bola.flywell.services.flightservice.Bookable;
import app.bola.flywell.services.userservice.CustomerService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FlightFormServiceTest {
	FlightFormService flightFormService;
	@Autowired
	Bookable bookable;
	BookingRequest bookingRequest;
	CustomerService passengerService;
	CustomerRequest passengerRequest;
	
	FlightFormRequest flightFormRequest;
	@SneakyThrows
	@BeforeEach void startAllTestWith(){
		passengerRequest = CustomerRequest.builder()
 				                   .email("alaabdulmalik03@gmail.com")
				                   .firstName("Abdulmalik")
				                   .lastName("Alayande")
				                   .password("ayanniyi@20")
				                   .phoneNumber("07036174617")
				                   .build();
		passengerService.registerNewCustomer(passengerRequest);
		bookingRequest = BookingRequest.builder()
				                .bookingCategory(1)
				                .passengerUsername("dende")
				                .build();
		Flight bookedFlight = bookable.bookFlight(bookingRequest);
		flightFormRequest = FlightFormRequest.builder()
				                    .flight(bookedFlight)
//				                    .passenger(passengerService.findAPassengerByUserName(passengerRequest.getUserName()))
				                    .build();
		flightFormService.save(flightFormRequest);
	}
}

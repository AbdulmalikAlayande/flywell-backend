package app.bola.flywell.services.flightservice;

import app.bola.flywell.dto.request.FlightReservationRequest;
import app.bola.flywell.dto.response.FlightReservationResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FlightReservationServiceTest {

    @Autowired
    FlightReservationService flightReservationService;
    FlightReservationResponse response;

    public static Stream<Arguments> buildFlightReservationRequest() {
        return Stream.of(Arguments.of(FlightReservationRequest.builder()

                .build()));
    }

    @ParameterizedTest
    @MethodSource(value = {
            "buildFlightReservationRequest"
    })
    public void testCreateReservationWithValidData(FlightReservationRequest request){
        response = flightReservationService.createNew(request);
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(LocalDate.now(), response.getCreationDate()),
                () -> assertEquals(1, flightReservationService.findAll().size())
        );
    }

    @Test
    public void testCreateReservationWithInvalidFlightInstanceId(){

    }

}
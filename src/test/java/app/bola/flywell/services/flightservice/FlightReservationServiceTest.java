package app.bola.flywell.services.flightservice;

import app.bola.flywell.dto.request.FlightReservationRequest;
import app.bola.flywell.dto.request.PassengerRequest;
import app.bola.flywell.dto.response.FlightInstanceResponse;
import app.bola.flywell.dto.response.FlightReservationResponse;
import app.bola.flywell.dto.response.FlightSeatResponse;
import app.bola.flywell.dto.response.PassengerResponse;
import app.bola.flywell.services.config.TestSetupConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Import(value = {TestSetupConfig.class})
class FlightReservationServiceTest {

    @Autowired
    TestSetupHelper setupHelper;

    @Autowired
    FlightReservationService flightReservationService;

    @Autowired
    FlightInstanceService flightInstanceService;

    FlightReservationResponse response;


    @BeforeEach
    public void startEachTestWith(){
        setupHelper.clearFlightReservationDb();
        System.out.println("I am here before hanging");
    }

    @AfterEach
    public void endEachTestWith(){
        setupHelper.clearFlightInstanceDb();
        setupHelper.clearAircraftDb();
    }

    @Nested
    class FlightReservationCreationTest {

        @Test
        public void testCreateReservationWithValidData_ReservationIsCreatedAndExistsInDb () {
            response = setupHelper.createFlightReservation();
            assertNotNull(response);
            assertEquals(LocalDate.now(), response.getCreationDate());
            assertEquals(1, flightReservationService.findAll().size());
        }

        @Test
        public void testCreateReservationWithValidData_ReservationContainsMappingsOfPassengersToSeats () {
            response = setupHelper.createFlightReservation();
            assertNotNull(response.getSeatMap());
            assertEquals(7, response.getSeatMap().size());
            response.getSeatMap().forEach((passenger, seat) -> {
                assertNotNull(seat);
                assertNotNull(passenger);
                assertThat(seat).hasNoNullFieldsOrProperties();
                assertThat(passenger).hasNoNullFieldsOrProperties();
            });
        }

        @Test
        public void testCreateReservationWithValidData_EachPassengerIsMappedToTheirRequestedSeat () {

            FlightInstanceResponse flightInstanceResponse = setupHelper.createFlightInstance();
            FlightReservationRequest reservationRequest = TestDataUtil.buildFlightReservationRequest(flightInstanceResponse.getPublicId());

            FlightReservationResponse reservationResponse = flightReservationService.createNew(reservationRequest);

            Map<PassengerRequest, Integer> requestSeatMap = reservationRequest.getSeatMap();
            Map<PassengerResponse, FlightSeatResponse> responseSeatMap = reservationResponse.getSeatMap();

            assertEquals(requestSeatMap.size(), responseSeatMap.size(), "Seat maps should have the same size");

            for (Map.Entry<PassengerRequest, Integer> requestEntry : requestSeatMap.entrySet()) {
                PassengerRequest requestPassenger = requestEntry.getKey();
                Integer requestedSeatNumber = requestEntry.getValue();

                PassengerResponse matchedResponsePassenger = responseSeatMap.keySet().stream()
                        .filter(responsePassenger -> responsePassenger.getFirstname().equals(requestPassenger.getFirstname())
                                && responsePassenger.getLastname().equals(requestPassenger.getLastname())
                                && responsePassenger.getPassportNumber().equals(requestPassenger.getPassportNumber()))
                        .findFirst()
                        .orElseThrow(() -> new AssertionError("Passenger not found in response seat map: " + requestPassenger));

                FlightSeatResponse flightSeatResponse = responseSeatMap.get(matchedResponsePassenger);
                assertNotNull(flightSeatResponse, "Seat response should not be null");
                assertEquals(requestedSeatNumber, flightSeatResponse.getSeatNumber(),
                        "Requested seat number does not match the reserved seat number for passenger: " + requestPassenger);
            }
        }

        @Test
        public void testCreateReservationWithValidData_OneOfTheRequestedSeatIsOccupied_ReservationCreationFails () {

        }

        @Test
        public void testCreateReservationWithInvalidFlightInstanceId () {

        }
    }

    @Nested
    class FlightReservationCancellationTest{

        @Test
        public void testCancelReservationWithValidData_ReservationIsCancelled () {
            flightReservationService.cancelReservation(response.getFlightId(), response.getPublicId());
            assertNull(flightReservationService.findByPublicId(response.getPublicId()));
        }


        @Test
        public void testCancelReservationForAlreadyCancelledReservation_ReservationIsCancelled () {
        }

        @Test
        public void testCancelReservationForNonexistentReservationId_ReservationCancellationFails_ExceptionIsThrown () {
        }

        @Test
        public void testCancelReservationForPastFlight_ReservationCancellationFails_ExceptionIsThrown () {
        }

    }

}
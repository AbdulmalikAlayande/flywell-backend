package app.bola.flywell.services.flightservice;

import app.bola.flywell.data.model.enums.Gender;
import app.bola.flywell.dto.request.PassengerRequest;
import app.bola.flywell.dto.response.AircraftResponse;
import app.bola.flywell.dto.response.FlightInstanceResponse;
import app.bola.flywell.dto.response.FlightReservationResponse;
import app.bola.flywell.dto.response.FlightResponse;
import app.bola.flywell.services.aircraft.AircraftService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.TestComponent;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Map;
import java.util.UUID;

@TestComponent
@AllArgsConstructor
public class TestSetupHelper {

    FlightService flightService;
    FlightInstanceService flightInstanceService;
    FlightReservationService flightReservationService;
    AircraftService aircraftService;
    final Logger logger = LoggerFactory.getLogger(TestSetupHelper.class);

    public FlightResponse createFlight(){
        return flightService.createNew(TestDataUtil.buildFlightRequest(3L, "Lagos, Nigeria", "Abuja, Nigeria"));
    }

    public FlightInstanceResponse createFlightInstance(){
        FlightResponse flightResponse = createFlight();
        createAircraft();
        return flightInstanceService.createNew(TestDataUtil.buildFlightInstanceRequest(flightResponse.getPublicId(), LocalDateTime.parse("2025-11-08T12:00:00"), LocalDateTime.parse("2025-11-09T14:00:00"), 2));
    }

    FlightReservationResponse createFlightReservation(){
        FlightInstanceResponse flightInstanceResponse = createFlightInstance();
        return flightReservationService.createNew(TestDataUtil.buildFlightReservationRequest(flightInstanceResponse.getPublicId(), Map.of(
                "2", new PassengerRequest("Abdulmalik", "Alayande", "https://cloud/image.jpg", "Halal", "43RET", "Nigerian", Gender.MALE, LocalDate.of(2003, Calendar.DECEMBER, 30), LocalDate.of(2027, Calendar.AUGUST, 12))
        )));
    }

    public void createAircraft() {
        String[][] aircraftData = new String[][]{
                {"AirBus 656", "GHR", "AirBus", "2018-10-09"},
                {"Lockheed CA-1206", "DBX", "Lockheed Martin", "2020-06-03"},
                {"Concord CF-781", "NAL", "Concord", "2014-09-10"}
        };

        SecureRandom random = new SecureRandom();
        for(int row = 0, column = 0; row < aircraftData.length; row++){
            AircraftResponse response = aircraftService.createNew(TestDataUtil.buildAircraftRequest(
                    aircraftData[row][column],
                    random.nextInt(200, 400),
                    aircraftData[row][column + 1],
                    aircraftData[row][column + 2],
                    LocalDate.parse(aircraftData[row][column + 3]),
                    UUID.randomUUID().toString()
            ));
            logger.info("Test Aircraft Response:: {}", response);
        }
    }

    public void clearFlightDb(){
        flightService.removeAll();
    }

    public void clearFlightInstanceDb(){
        flightService.removeAll();
        flightInstanceService.removeAll();
    }

    public void clearAircraftDb() {
        aircraftService.removeAll();
    }

    public void clearFlightReservationDb() {
        flightService.removeAll();
        flightInstanceService.removeAll();
        flightReservationService.removeAll();
    }

    public void clearDb(){
        clearFlightDb();
        clearFlightInstanceDb();
        clearAircraftDb();
        clearFlightReservationDb();
    }

    public FlightInstanceResponse createPastFlightInstance() {
        return null;
    }

    public FlightReservationResponse createFlightReservation(String publicId) {
        return null;
    }
}

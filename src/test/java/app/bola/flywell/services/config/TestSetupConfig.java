package app.bola.flywell.services.config;

import app.bola.flywell.services.aircraft.AircraftService;
import app.bola.flywell.services.flightservice.FlightInstanceService;
import app.bola.flywell.services.flightservice.FlightReservationService;
import app.bola.flywell.services.flightservice.FlightService;
import app.bola.flywell.services.flightservice.TestSetupHelper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestSetupConfig {

    @Bean
    public TestSetupHelper testSetupHelper(FlightService flightService, FlightInstanceService flightInstanceService, FlightReservationService flightReservationService, AircraftService aircraftService){
        return new TestSetupHelper(flightService, flightInstanceService, flightReservationService, aircraftService);
    }
}

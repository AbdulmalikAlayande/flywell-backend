package app.bola.flywell.services.flightservice;

import app.bola.flywell.data.model.enums.Gender;
import app.bola.flywell.dto.request.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Map;

public class TestDataUtil {


    public static FlightRequest buildFlightRequest() {
        return FlightRequest.builder()
                .duration(3L)
                .arrivalCity("Lagos, Nigeria")
                .departureCity("Abuja, Nigeria")
                .destinationAirport(buildAirportRequest("Murtala Muhammed Airport", "Nigeria", "23456", "12345"))
                .departureAirport(buildAirportRequest("Nnamdi Azikwe International Airport", "Nigeria", "45632", "12345"))
                .build();
    }

    public static FlightRequest buildFlightRequest(long duration, String arrivalCity, String departureCity) {
        return FlightRequest.builder()
                .duration(duration)
                .arrivalCity(arrivalCity)
                .departureCity(departureCity)
                .destinationAirport(buildAirportRequest("Murtala Muhammed Airport", "Nigeria", "23456", "12345"))
                .departureAirport(buildAirportRequest("Nnamdi Azikwe International Airport", "Nigeria", "45632", "12345"))
                .build();
    }

    public @NotNull AirportRequest createAirport() {
        return null;
    }

    public static AirportRequest buildAirportRequest(String name, String country, String icaoCode, String iataCode){
        return AirportRequest.builder()
                .name(name)
                .countryName(country)
                .icaoCode(icaoCode)
                .iataCode(iataCode)
                .longitude(-34567)
                .latitude(45678)
                .build();
    }

    public static FlightInstanceRequest buildFlightInstanceRequest(String flightId, LocalDateTime departureTime, LocalDateTime arrivalTime, int priority) {
        return FlightInstanceRequest.builder()
                .flightId(flightId)
                .departureTime(departureTime)
                .arrivalTime(arrivalTime)
                .priority(priority)
                .build();
    }

    public static FlightReservationRequest buildFlightReservationRequest(String flightId, Map<String, PassengerRequest> seatMap) {
        return FlightReservationRequest.builder()
                .flightId(flightId)
                .seatMap(seatMap)
                .build();
    }

    public static FlightReservationRequest buildFlightReservationRequest(String flightId){
        return FlightReservationRequest.builder()
                .flightId(flightId)
                .seatMap(Map.of(
                        "2", new PassengerRequest("Abdulmalik", "Alayande", "https://cloud/image.jpg", "Halal", "43RET", "Nigerian", Gender.MALE, LocalDate.of(2003, Calendar.DECEMBER, 30), LocalDate.of(2027, Calendar.AUGUST, 12))
                ))
                .build();
    }

    public static AircraftRequest buildAircraftRequest(String model, int capacity, String locationCode, String manufacturer, LocalDate datePurchased, String registrationNumber){
        return AircraftRequest.builder()
                .model(model)
                .capacity(capacity)
                .locationCode(locationCode)
                .manufacturer(manufacturer)
                .datePurchased(datePurchased)
                .registrationNumber(registrationNumber)
                .build();
    }
}

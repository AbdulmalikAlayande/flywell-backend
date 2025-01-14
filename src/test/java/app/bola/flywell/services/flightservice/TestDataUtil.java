package app.bola.flywell.services.flightservice;

import app.bola.flywell.data.model.enums.Gender;
import app.bola.flywell.dto.request.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Map;

public class TestDataUtil {


    public static FlightRequest buildFlightRequest(long duration, String arrivalCity, String departureCity) {
        return FlightRequest.builder()
                .duration(duration)
                .arrivalCity(arrivalCity)
                .departureCity(departureCity)
                .destinationAirport(buildAirportRequest("Murtala Muhammed Airport", "Nigeria", "23456", "12345"))
                .departureAirport(buildAirportRequest("Nnamdi Azikwe International Airport", "Nigeria", "45632", "12345"))
                .build();
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

    public static FlightReservationRequest buildFlightReservationRequest(String flightId, Map<PassengerRequest, Integer> seatMap) {
        return FlightReservationRequest.builder()
                .flightId(flightId)
                .seatMap(seatMap)
                .build();
    }

    public static FlightReservationRequest buildFlightReservationRequest(String flightId){
        return FlightReservationRequest.builder()
                .flightId(flightId)
                .seatMap(Map.of(
                        new PassengerRequest("Abdulmalik", "Alayande", "https://cloud/image.jpg", "43RET", Gender.MALE, LocalDate.of(2003, Calendar.DECEMBER, 30)), 2,
                        new PassengerRequest("Amirah", "Alayande", "https://cloud/image-1.jpg", "27YT6", Gender.FEMALE, LocalDate.of(2008, Calendar.APRIL, 11)), 17,
                        new PassengerRequest("Sophia", "Alayande", "https://cloud/image-2.jpg", "VG56D", Gender.FEMALE, LocalDate.of(2001, Calendar.DECEMBER, 4)), 43,
                        new PassengerRequest("Zainab", "Alayande", "https://cloud/image-3.jpg", "DF3W2", Gender.FEMALE, LocalDate.of(1999, Calendar.JUNE, 29)), 9,
                        new PassengerRequest("Kudirat", "Alayande", "https://cloud/image-4.jpg", "EDR22", Gender.FEMALE, LocalDate.of(1970, Calendar.NOVEMBER, 25)), 11,
                        new PassengerRequest("Abdullah", "Alayande", "https://cloud/image-5.jpg", "EDR22", Gender.MALE, LocalDate.of(1970, Calendar.JULY, 18)), 78,
                        new PassengerRequest("Oseni", "Ayantunde Alayande", "https://cloud/image-6.jpg", "EDR22", Gender.MALE, LocalDate.of(1970, Calendar.AUGUST, 9)), 102
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

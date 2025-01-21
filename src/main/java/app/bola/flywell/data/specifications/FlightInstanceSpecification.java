package app.bola.flywell.data.specifications;

import app.bola.flywell.data.model.flight.FlightInstance;
import app.bola.flywell.data.model.Airport;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;

public class FlightInstanceSpecification {

    public static Specification<FlightInstance> hasFlightNumber(String flightNumber) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("flightNumber"), flightNumber);
    }

    public static Specification<FlightInstance> hasDepartureDate(Date departureDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("departureDate"), departureDate);
    }

    public static Specification<FlightInstance> hasArrivalDate(Date arrivalDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("arrivalDate"), arrivalDate);
    }

    public static Specification<FlightInstance> hasStatus(String status) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<FlightInstance> hasDepartureAirport(String airportCode) {
        return (root, query, criteriaBuilder) -> {
            Join<FlightInstance, Airport> departureAirport = root.join("departureAirport", JoinType.INNER);
            return criteriaBuilder.equal(departureAirport.get("code"), airportCode);
        };
    }

    public static Specification<FlightInstance> hasArrivalAirport(String airportCode) {
        return (root, query, criteriaBuilder) -> {
            Join<FlightInstance, Airport> arrivalAirport = root.join("arrivalAirport", JoinType.INNER);
            return criteriaBuilder.equal(arrivalAirport.get("code"), airportCode);
        };
    }


    public static Specification<FlightInstance> hasAvailableSeatsGreaterThan(int seatCount) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("availableSeats"), seatCount);
    }

    public static Specification<FlightInstance> isBetweenDepartureDates(Date startDate, Date endDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("departureDate"), startDate, endDate);
    }

    public static Specification<FlightInstance> isBetweenArrivalDates(Date startDate, Date endDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("arrivalDate"), startDate, endDate);
    }

    public static Specification<FlightInstance> isPriceWithinRange(Double minPrice, Double maxPrice) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
    }
}

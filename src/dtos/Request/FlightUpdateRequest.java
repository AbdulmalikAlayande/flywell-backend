package dtos.Request;

import data.model.Passenger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;
import utils.DateTime.Date;

import java.time.LocalTime;
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightUpdateRequest {
	private String flightId;
	@Nullable private LocalTime departureTime;
	@Nullable private Date departureDate;
	@Nullable private LocalTime arrivalTime;
	@Nullable private Date arrivalDate;
	@Nullable private String Airline;
	private int baggageAllowance;
	@Nullable private String destination;
	@Nullable private Passenger[] passengers;
	
	public String getFlightId() {
		return flightId;
	}
	
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	
	@Nullable public LocalTime getDepartureTime() {
		return departureTime;
	}
	
	public void setDepartureTime(@Nullable LocalTime departureTime) {
		this.departureTime = departureTime;
	}
	
	@Nullable public Date getDepartureDate() {
		return departureDate;
	}
	
	public void setDepartureDate(@Nullable Date departureDate) {
		this.departureDate = departureDate;
	}
	
	@Nullable public LocalTime getArrivalTime() {
		return arrivalTime;
	}
	
	public void setArrivalTime(@Nullable LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	@Nullable public Date getArrivalDate() {
		return arrivalDate;
	}
	
	public void setArrivalDate(@Nullable Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	
	@Nullable public String getAirline() {
		return Airline;
	}
	
	public void setAirline(@Nullable String airline) {
		Airline = airline;
	}
	
	public int getBaggageAllowance() {
		return baggageAllowance;
	}
	
	public void setBaggageAllowance(int baggageAllowance) {
		this.baggageAllowance = baggageAllowance;
	}
	
	@Nullable public String getDestination() {
		return destination;
	}
	
	public void setDestination(@Nullable String destination) {
		this.destination = destination;
	}
	
	@Nullable public Passenger[] getPassengers() {
		return passengers;
	}
	
	public void setPassengers(@Nullable Passenger[] passengers) {
		this.passengers = passengers;
	}
}

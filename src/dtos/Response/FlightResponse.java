package dtos.Response;

import utils.DateTime.Date;

import java.time.LocalTime;

public class FlightResponse {
	private String flightId;
	private LocalTime departureTime;
	private Date departureDate;
	private LocalTime arrivalTime;
	private Date arrivalDate;
	private String Airline;
	private int baggageAllowance;
	private String destination;
	
	public String getFlightId() {
		return flightId;
	}
	
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	
	public LocalTime getDepartureTime() {
		return departureTime;
	}
	
	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}
	
	public Date getDepartureDate() {
		return departureDate;
	}
	
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	
	public LocalTime getArrivalTime() {
		return arrivalTime;
	}
	
	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	public Date getArrivalDate() {
		return arrivalDate;
	}
	
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	
	public String getAirline() {
		return Airline;
	}
	
	public void setAirline(String airline) {
		Airline = airline;
	}
	
	public int getBaggageAllowance() {
		return baggageAllowance;
	}
	
	public void setBaggageAllowance(int baggageAllowance) {
		this.baggageAllowance = baggageAllowance;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public void setDestination(String destination) {
		this.destination = destination;
	}
}

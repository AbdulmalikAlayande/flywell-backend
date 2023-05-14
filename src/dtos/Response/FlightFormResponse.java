package dtos.Response;

import data.model.Price;
import data.model.TravelClass;
import utils.DateTime.Date;

import java.time.LocalTime;

public class FlightFormResponse {
	private Price flightPrice;
	private String passengerName;
	private String destination;
	private TravelClass travelClass;
	private int flightSeatNumber;//get the index of the seat, the seat number
	private int passengerConfirmationNumber;
	private LocalTime departureTime;
	private Date departureDate;
	private LocalTime arrivalTime;
	private Date arrivalDate;
	private String Airline;
	private int baggageAllowance;
	
	public Price getFlightPrice() {
		return flightPrice;
	}
	
	public void setFlightPrice(Price flightPrice) {
		this.flightPrice = flightPrice;
	}
	
	public String getPassengerName() {
		return passengerName;
	}
	
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public TravelClass getTravelClass() {
		return travelClass;
	}
	
	public void setTravelClass(TravelClass travelClass) {
		this.travelClass = travelClass;
	}
	
	public int getFlightSeatNumber() {
		return flightSeatNumber;
	}
	
	public void setFlightSeatNumber(int flightSeatNumber) {
		this.flightSeatNumber = flightSeatNumber;
	}
	
	public int getPassengerConfirmationNumber() {
		return passengerConfirmationNumber;
	}
	
	public void setPassengerConfirmationNumber(int passengerConfirmationNumber) {
		this.passengerConfirmationNumber = passengerConfirmationNumber;
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
	
	@Override
	public String toString() {
		return "FlightFormResponse{" + "flightPrice=" + flightPrice +
				            ", passengerName='" + passengerName + '\'' +
				            ", destination='" + destination + '\'' +
				            ", travelClass=" + travelClass +
				            ", flightSeatNumber=" + flightSeatNumber +
				            ", passengerConfirmationNumber=" + passengerConfirmationNumber +
				            ", departureTime=" + departureTime +
				            ", departureDate=" + departureDate +
				            ", arrivalTime=" + arrivalTime +
				            ", arrivalDate=" + arrivalDate +
				            ", Airline='" + Airline + '\'' +
				            ", baggageAllowance=" + baggageAllowance +
				            '}';
	}
}

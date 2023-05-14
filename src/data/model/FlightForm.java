package data.model;

import utils.DateTime.Date;

import java.time.LocalTime;
import java.util.EnumSet;

import static data.model.TravelClass.ECONOMY_CLASS;
import static data.model.TravelClass.FIRST_CLASS;

public class FlightForm {
	private String id;
	private String flightId;
	private String passengerId;
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
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setTravelClass(TravelClass travelClass) {
		this.travelClass = travelClass;
	}
	
	public TravelClass getTravelClass() {
		return travelClass;
	}
	
	public void setTravelClass(int travelClassIntValue){
		for (TravelClass travelClass : EnumSet.range(FIRST_CLASS, ECONOMY_CLASS)){
			if (travelClassIntValue == travelClass.ordinal()) this.travelClass = travelClass;
		}
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
	
	public Price getFlightPrice() {
		return flightPrice;
	}
	
	public void setFlightPrice(Price flightPrice) {
		this.flightPrice = flightPrice;
	}
	
	public int getFlightSeatNumber() {
		return flightSeatNumber;
	}
	
	public void setFlightSeatNumber(int flightSeatNumber) {
		this.flightSeatNumber = flightSeatNumber;
	}
	
	public LocalTime getDepartureTime() {
		return departureTime;
	}
	
	public void setDepartureTime(LocalTime departureTime) {this.departureTime = departureTime;}
	
	public String getFlightId() {
		return flightId;
	}
	
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	
	public String getPassengerId() {
		return passengerId;
	}
	
	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
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
		Date date = new Date();
		date.setDate(arrivalDate.getDay(), arrivalDate.getMonth(), arrivalDate.getYear());
		this.arrivalDate = date;
	}
	
	public int getPassengerConfirmationNumber() {
		return passengerConfirmationNumber;
	}
	
	public void setPassengerConfirmationNumber(int passengerConfirmationNumber) {
		this.passengerConfirmationNumber = passengerConfirmationNumber;
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
		return String.format("%s%s\n%s%s\n%s%s\n%s%s\n%s%s\n%s%s\n%s%s\n%s%s\n%s%d\n%s%s\n%s%s\n%s%s\n%s%d\n%s%d\n%s",
				FlightForm.class.getSimpleName(), "{",
				"Form Id: ", getId(),
				"Airline Name: ", getAirline(),
				"Passenger Name: ", getPassengerName(),
				"Travel Class: ", getTravelClass(),
				"Flight Price: ", getFlightPrice(),
				"Flight Id: ", getFlightId(),
				"Passenger Id: ", getPassengerId(),
				"Seat number: ", getFlightSeatNumber(),
				"Destination: ", getDestination(),
				"Departure Time: ", getDepartureTime(),
				"Departure Date: ", getDepartureDate(),
				"Bag Allowance: ", getBaggageAllowance(),
				"Confirmation Number: ", getPassengerConfirmationNumber(),
				"}");
	}
}

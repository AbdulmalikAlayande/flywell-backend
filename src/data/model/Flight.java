package data.model;

import utils.DateTime.Date;

import java.time.LocalTime;
import java.util.Arrays;


public class Flight {
	private String flightId;
	private LocalTime departureTime;
	private boolean isFullyBooked;
	private Date departureDate;
	private LocalTime arrivalTime;
	private Date arrivalDate;
	private String Airline;
	private int baggageAllowance;
	private String destination;
	private int numberOfPassengers;
	private Passenger[] passengers;
	private final int numberOfSeats = 20;
	private final boolean[] seats = new boolean[numberOfSeats];
	
	public boolean isFullyBooked() {
		return isFullyBooked;
	}
	
	public void setFullyBooked(boolean fullyBooked) {
		isFullyBooked = fullyBooked;
	}
	
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
	
	public Passenger[] getPassengers() {
		return passengers;
	}
	
	public void setPassengers(Passenger[] passengers) {
		this.passengers = passengers;
	}
	
	public int getNumberOfPassengers() {
		return numberOfPassengers;
	}
	
	public void setNumberOfPassengers(int numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}
	
	public boolean[] getSeats() {
		return seats;
	}
	
	public boolean getSeats(int seatIndex){
		return seats[seatIndex];
	}
	
	public int getNumberOfSeats(){
		return seats.length;
	}
	
	public boolean getInitialStateOfSeat(){
		boolean isEmptySeat = false;
		for (boolean seat : seats) {
			if (!seat) {
				isEmptySeat = true;
				break;
			}
		}
		return isEmptySeat;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Flight{\n");
		sb.append(" flightId='").append(flightId).append('\'');
		sb.append("\n departureTime=").append(departureTime);
		sb.append("\n isFullyBooked=").append(isFullyBooked);
		sb.append("\n departureDate=").append(departureDate);
		sb.append(" arrivalTime=").append(arrivalTime);
		sb.append("\n arrivalDate=").append(arrivalDate);
		sb.append(" Airline='").append(Airline).append('\'');
		sb.append("\n baggageAllowance=").append(baggageAllowance);
		sb.append("\n destination='").append(destination).append('\'');
		sb.append("\n numberOfPassengers=").append(numberOfPassengers);
		sb.append("\n passengers=").append(Arrays.toString(passengers));
		sb.append("\n numberOfSeats=").append(numberOfSeats);
		sb.append("\n seats=").append(Arrays.toString(seats));
		sb.append('}');
		return sb.toString();
	}
}

package dtos.Request;

import data.model.Payment;

public class BookingRequest {
	private String passengerUsername;
	private int bookingCategory;
	private Payment payment;
	
	public String getPassengerUsername() {
		return passengerUsername;
	}
	
	public void setPassengerUsername(String passengerUsername) {
		this.passengerUsername = passengerUsername;
	}
	
	public int getBookingCategory() {
		return bookingCategory;
	}
	
	public void setBookingCategory(int bookingCategory) {
		this.bookingCategory = bookingCategory;
	}
	
	public Payment getPayment() {
		return payment;
	}
	
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
}

package data.model;

public class Payment {
	
	private Price price;
	private boolean status;
	private PaymentMethod paymentMethod;
	
	public Price getPrice() {
		return price;
	}
	
	public void setPrice(Price price) {
		this.price = price;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
}

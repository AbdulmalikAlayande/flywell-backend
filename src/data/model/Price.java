package data.model;

public enum Price {
	FIRST_CLASS(200_000),
	BUSINESS_CLASS(150_000),
	PREMIUM_ECONOMY_CLASS(100_000),
	ECONOMY_CLASS(50_000);
	
	private final double price;
	Price(double price) {
		this.price = price;
	}
}

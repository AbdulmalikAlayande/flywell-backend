package data.model;

import java.util.StringJoiner;

public enum TravelClass {
	FIRST_CLASS,
	BUSINESS_CLASS,
	PREMIUM_ECONOMY_CLASS,
	ECONOMY_CLASS;
	
	@Override
	public String toString() {
		return new StringJoiner(", ", TravelClass.class.getSimpleName() + "[", "]")
				       .toString();
	}
}

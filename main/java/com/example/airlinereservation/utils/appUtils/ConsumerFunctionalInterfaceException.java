package com.example.airlinereservation.utils.appUtils;

public interface ConsumerFunctionalInterfaceException<T, E extends Exception> {
	
	void accept (T t) throws E;
}

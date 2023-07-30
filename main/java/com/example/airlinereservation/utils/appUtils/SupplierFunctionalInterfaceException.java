package com.example.airlinereservation.utils.appUtils;

public interface SupplierFunctionalInterfaceException<R, E extends Exception> {
	
	R get() throws E;
}

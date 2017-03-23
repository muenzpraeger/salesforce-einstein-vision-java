package com.winkelmeyer.salesforce.einsteinvision.exceptions;

public class NumberTooSmallException extends Exception {
	
	public NumberTooSmallException (String field, int minValue, int currentValue) {
		super("The minimum allowed size for '" + field + "' is " + minValue + " (current: " + currentValue + ")."); 
	}
	
	public NumberTooSmallException (String field, double minValue, double currentValue) {
		super("The minimum allowed size for '" + field + "' is " + minValue + " (current: " + currentValue + ")."); 
	}

}

package com.winkelmeyer.salesforce.einsteinvision.exceptions;

public class NumberToBigException extends Exception {
	
	public NumberToBigException (String field, int maxValue, int currentValue) {
		super("The maximum allowed size for '" + field + "' is " + maxValue + " (current: " + currentValue + ")."); 
	}
	
	public NumberToBigException (String field, double maxValue, double currentValue) {
		super("The maximum allowed size for '" + field + "' is " + maxValue + " (current: " + currentValue + ")."); 
	}

}

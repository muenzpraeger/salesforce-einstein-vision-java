package com.winkelmeyer.salesforce.einsteinvision.exceptions;

public class NumberTooBigException extends Exception {
	
	public NumberTooBigException (String field, int maxValue, int currentValue) {
		super("The maximum allowed size for '" + field + "' is " + maxValue + " (current: " + currentValue + ")."); 
	}
	
	public NumberTooBigException (String field, double maxValue, double currentValue) {
		super("The maximum allowed size for '" + field + "' is " + maxValue + " (current: " + currentValue + ")."); 
	}

}

package com.winkelmeyer.salesforce.predictivevision.exceptions;

public class NumberToSmallException extends Exception {
	
	public NumberToSmallException (String field, int minValue, int currentValue) {
		super("The minimum allowed size for '" + field + "' is " + minValue + " (current: " + currentValue + ")."); 
	}
	
	public NumberToSmallException (String field, double minValue, double currentValue) {
		super("The minimum allowed size for '" + field + "' is " + minValue + " (current: " + currentValue + ")."); 
	}

}

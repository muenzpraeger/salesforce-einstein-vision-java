package com.winkelmeyer.salesforce.predictivevision.exceptions;

public class StringTooLongException extends Exception {

	public StringTooLongException(String field, int maxValue, int currentValue) {
		super("The maximum allowed length for '" + field + "' is " + maxValue + " (current: " + currentValue + ")."); 
	}
	
}

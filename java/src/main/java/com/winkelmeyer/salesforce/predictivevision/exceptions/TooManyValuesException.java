package com.winkelmeyer.salesforce.predictivevision.exceptions;

public class TooManyValuesException extends Exception {

	public TooManyValuesException(String field, int maxValue, int currentValue) {
		super("The maximum allowed number of entries for '" + field + "' is " + maxValue + " (current: " + currentValue + ")."); 
	}
	
}
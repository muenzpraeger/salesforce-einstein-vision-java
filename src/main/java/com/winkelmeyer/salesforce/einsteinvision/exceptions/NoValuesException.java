package com.winkelmeyer.salesforce.einsteinvision.exceptions;

public class NoValuesException extends Exception {

	public NoValuesException(String field) {
		super("You haven't provided any values for '" + field + "'.");
	}
	
}

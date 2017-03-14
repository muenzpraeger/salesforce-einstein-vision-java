package com.winkelmeyer.salesforce.einsteinvision.exceptions;

public class StringIsEmptyException extends Exception {

	public StringIsEmptyException(String field) {
		super("The field '" + field + "' cannot be empty.");
	}
	
}

package com.winkelmeyer.salesforce.predictivevision.exceptions;

public class NoValuesException extends Exception {

	public NoValuesException(String field) {
		super("You haven't provided any values for '" + field + "'.");
	}
	
}

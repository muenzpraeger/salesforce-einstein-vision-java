package com.winkelmeyer.salesforce.einsteinvision.exceptions;

public class FileTooLargeException extends Exception {

	public FileTooLargeException(String fileName) {
		super("The provided file '" + fileName + " exceeds the maximum file size.");
	}
	
}

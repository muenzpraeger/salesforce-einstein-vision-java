package com.winkelmeyer.salesforce.einsteinvision.exceptions;

public class FileNoZipFileException extends Exception {

	public FileNoZipFileException(String fileName) {
		super("The provided file '" + fileName + "' doesn't seem to be a zip file.");
	}
	
}

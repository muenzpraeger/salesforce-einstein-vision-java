package com.winkelmeyer.salesforce.einsteinvision.http.parts;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.asynchttpclient.request.body.multipart.FilePart;
import org.asynchttpclient.request.body.multipart.Part;

import com.winkelmeyer.salesforce.einsteinvision.exceptions.FileNoZipFileException;
import com.winkelmeyer.salesforce.einsteinvision.exceptions.FileTooLargeException;
import com.winkelmeyer.salesforce.einsteinvision.exceptions.StringIsEmptyException;

public class BodyPartDatasetZipFile {

	private String fileName;

	public BodyPartDatasetZipFile(String fileName) throws Exception {
		if (fileName.isEmpty()) {
			throw new StringIsEmptyException("fileName");
		}
		File file = new File(fileName);
		if (!file.exists()) {
			throw new FileNotFoundException(fileName);
		}
		if (!file.getAbsolutePath().toLowerCase().endsWith(".zip")) {
			throw new FileNoZipFileException(fileName);
		}
		if (file.length()>50000000) {
			throw new FileTooLargeException(fileName);
		}
		this.fileName = fileName;
	}

	public List<Part> build() {
		List<Part> parts = new ArrayList<Part>();
		File file = new File(fileName);
		parts.add(new FilePart("data", file));
		return parts;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) throws Exception {
		if (fileName.isEmpty()) {
			throw new StringIsEmptyException("fileName");
		}
		File file = new File(fileName);
		if (!file.exists()) {
			throw new FileNotFoundException(fileName);
		}
		if (!file.getAbsolutePath().toLowerCase().endsWith(".zip")) {
			throw new FileNoZipFileException(fileName);
		}
		if (file.length()>50000000) {
			throw new FileTooLargeException(fileName);
		}
		this.fileName = fileName;
	}

}

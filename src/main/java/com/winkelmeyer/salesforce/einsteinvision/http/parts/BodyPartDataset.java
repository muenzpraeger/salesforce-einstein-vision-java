package com.winkelmeyer.salesforce.einsteinvision.http.parts;

import java.util.ArrayList;
import java.util.List;

import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.request.body.multipart.StringPart;

import com.winkelmeyer.salesforce.einsteinvision.exceptions.NoValuesException;
import com.winkelmeyer.salesforce.einsteinvision.exceptions.StringIsEmptyException;
import com.winkelmeyer.salesforce.einsteinvision.exceptions.StringTooLongException;
import com.winkelmeyer.salesforce.einsteinvision.exceptions.TooManyValuesException;

public class BodyPartDataset {

	private String name;
	private String labels;
	
    public BodyPartDataset(String name, String[] labels) throws Exception {
    	if (name.isEmpty()) {
    		throw new StringIsEmptyException("name");
    	}
    	if (name.length()>180) {
    		throw new StringTooLongException("name", 180, name.length());
    	}
    	if (labels==null) {
    		throw new NullPointerException("Labels cannot be null");
    	}
    	if (labels.length==0) {
    		throw new NoValuesException("labels");
    	}
    	if (labels.length>1000) {
    		throw new TooManyValuesException("labels", 1000, labels.length);
    	}
        this.name = name;
        this.labels = String.join(",", labels);
    }
    
    public List<Part> build() {
    	List<Part> parts = new ArrayList<Part>();
    	parts.add(new StringPart("name",name));
    	parts.add(new StringPart("labels",labels));
    	return parts;
    }
    
	public String getName() {
		return name;
	}

	public void setName(String name) throws Exception {
    	if (name.isEmpty()) {
    		throw new StringIsEmptyException("name");
    	}
    	if (name.length()>180) {
    		throw new StringTooLongException("name", 180, name.length());
    	}
		this.name = name;
	}
	
	public String getLabels() {
		return labels;
	}

	public void setLabels(String[] labels) throws Exception {
    	if (labels==null) {
    		throw new NullPointerException("Labels cannot be null");
    	}
    	if (labels.length==0) {
    		throw new NoValuesException("labels");
    	}
    	if (labels.length>1000) {
    		throw new TooManyValuesException("labels", 1000, labels.length);
    	}
    	this.labels = String.join(",", labels);
	}
	
}

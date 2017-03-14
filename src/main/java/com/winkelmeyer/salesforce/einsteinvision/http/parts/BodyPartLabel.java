package com.winkelmeyer.salesforce.einsteinvision.http.parts;

import java.util.ArrayList;
import java.util.List;

import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.request.body.multipart.StringPart;

import com.winkelmeyer.salesforce.einsteinvision.exceptions.StringIsEmptyException;
import com.winkelmeyer.salesforce.einsteinvision.exceptions.StringTooLongException;

public class BodyPartLabel {
	
	private String name;
	
    public BodyPartLabel(String name) throws Exception {
    	if (name.isEmpty()) {
    		throw new StringIsEmptyException("name");
    	}
    	if (name.length()>180) {
    		throw new StringTooLongException("name", 180, name.length());
    	}
        this.name = name;
    }
    
    public List<Part> build() {
    	List<Part> parts = new ArrayList<Part>();
    	parts.add(new StringPart("name",name));
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
	
}

package com.winkelmeyer.salesforce.predictivevision.http.parts;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.asynchttpclient.request.body.multipart.FilePart;
import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.request.body.multipart.StringPart;

import com.winkelmeyer.salesforce.predictivevision.exceptions.StringIsEmptyException;
import com.winkelmeyer.salesforce.predictivevision.exceptions.StringTooLongException;

public class BodyPartExample {

	private long labelId;
	private String data;
	private String name;
	
    public BodyPartExample(String name,long labelId, String data) throws Exception {
    	if (name.isEmpty()) {
    		throw new StringIsEmptyException("name");
    	}
    	if (name.length()>180) {
    		throw new StringTooLongException("name", 180, name.length());
    	}
    	if (labelId<1) {
    		throw new NullPointerException("Labels cannot be null");
    	}
    	if (data.isEmpty()) {
    		throw new StringIsEmptyException("data");
    	}
        this.name = name;
        this.data = data;
        this.labelId = labelId;
    }
    
    public List<Part> build() {
    	File file = new File(data);
    	List<Part> parts = new ArrayList<Part>();
    	parts.add(new StringPart("name",name));
    	parts.add(new StringPart("labelId",String.valueOf(labelId)));
    	parts.add(new FilePart("data", file));
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

	public long getLabelId() {
		return labelId;
	}

	public void setLabelId(long labelId) {
    	if (labelId<1) {
    		throw new NullPointerException("Labels cannot be null");
    	}
		this.labelId = labelId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) throws StringIsEmptyException {
    	if (data.isEmpty()) {
    		throw new StringIsEmptyException("data");
    	}
		this.data = data;
	}
	

	
}

package com.winkelmeyer.salesforce.einsteinvision.http.parts;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.asynchttpclient.request.body.multipart.FilePart;
import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.request.body.multipart.StringPart;

public class BodyPartPrediction {
	
	public enum Type {
		BASE64,
		FILE,
		URL
	}
	
	private String modelId;
	private String data;
	private String sampleId;
	private Type type;
	
	public BodyPartPrediction(String modelId, String data, String sampleId, Type type) {
		this.modelId = modelId;
		this.data = data;
		this.sampleId = sampleId;
		this.type = type;
	}
	
	public List<Part> build() {
    	List<Part> parts = new ArrayList<Part>();
    	parts.add(new StringPart("modelId",modelId));
    	if (!sampleId.isEmpty()) {
    		parts.add(new StringPart("sampleId", sampleId));
    	}
		switch(type) {
		case BASE64:
			parts.add(new StringPart("sampleBase64Content", data));
			break;
		case FILE:
			File file = new File(data);
			parts.add(new FilePart("sampleContent", file));
			break;
		case URL:
			parts.add(new StringPart("sampleLocation", data));
			break;
		}
    	return parts;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getSampleId() {
		return sampleId;
	}

	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}

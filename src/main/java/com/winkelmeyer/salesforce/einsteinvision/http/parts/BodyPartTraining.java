package com.winkelmeyer.salesforce.einsteinvision.http.parts;

import java.util.ArrayList;
import java.util.List;

import org.asynchttpclient.request.body.multipart.Part;
import org.asynchttpclient.request.body.multipart.StringPart;

import com.winkelmeyer.salesforce.einsteinvision.exceptions.NoValuesException;
import com.winkelmeyer.salesforce.einsteinvision.exceptions.NumberTooBigException;
import com.winkelmeyer.salesforce.einsteinvision.exceptions.NumberTooSmallException;
import com.winkelmeyer.salesforce.einsteinvision.exceptions.StringIsEmptyException;
import com.winkelmeyer.salesforce.einsteinvision.exceptions.StringTooLongException;

public class BodyPartTraining {
	
	private int datasetId;
	private String name;
	private int epochs;
	private double learningRate;
	
	private boolean hasEpochs = true;
	
	private static double DEFAULT_LEARNING_RATE = 0.0001;
	private static double MIN_LEARNING_RATE = 0.0001;
	private static double MAX_LEARNING_RAGE = 0.01;
	
	public BodyPartTraining(int datasetId, String name, int epochs, double learningRate) throws Exception {
		if (datasetId == 0) {
			throw new NoValuesException("datasetId");
		}
		if (name.isEmpty()) {
    		throw new StringIsEmptyException("name");
    	}
    	if (name.length()>180) {
    		throw new StringTooLongException("name", 180, name.length());
    	}
    	if (epochs==0) {
    		hasEpochs = false;
    	} else if (epochs>100) {
    		throw new NumberTooBigException("epochs", 100, epochs);
    	}
    	if (learningRate==0) {
    		learningRate = DEFAULT_LEARNING_RATE;
    	} else {
    		if (learningRate < MIN_LEARNING_RATE) {
    			throw new NumberTooSmallException("learningRate", MIN_LEARNING_RATE, learningRate);
    		} else if (learningRate > MAX_LEARNING_RAGE) {
    			throw new NumberTooBigException("learningRate", MAX_LEARNING_RAGE, learningRate);
    		}
    	}
    	this.datasetId = datasetId;
        this.name = name;
        this.epochs = epochs;
        this.learningRate = learningRate;
	}
	
    public List<Part> build() {
    	List<Part> parts = new ArrayList<Part>();
    	parts.add(new StringPart("name", name));
    	parts.add(new StringPart("datasetId",String.valueOf(datasetId)));
    	if (hasEpochs) {
    		parts.add(new StringPart("epochs",String.valueOf(epochs)));
    	}
    	parts.add(new StringPart("learningRate",String.valueOf(learningRate)));
    	return parts;
    }
    

	public int getDatasetId() {
		return datasetId;
	}

	public void setDatasetId(int datasetId) throws Exception {
		if (datasetId == 0) {
			throw new NoValuesException("datasetId");
		}
		this.datasetId = datasetId;
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

	public int getEpochs() {
		return epochs;
	}

	public void setEpochs(int epochs) throws Exception {
		if (epochs==0) {
    		hasEpochs = false;
    	} else if (epochs>100) {
    		throw new NumberTooBigException("epochs", 100, epochs);
    	}
		this.epochs = epochs;
	}

	public double getLearningRate() {
		return learningRate;
	}

	public void setLearningRate(double learningRate) throws Exception {
    	if (learningRate==0) {
    		learningRate = DEFAULT_LEARNING_RATE;
    	} else {
    		if (learningRate < MIN_LEARNING_RATE) {
    			throw new NumberTooSmallException("learningRate", MIN_LEARNING_RATE, learningRate);
    		} else if (learningRate > MAX_LEARNING_RAGE) {
    			throw new NumberTooBigException("learningRate", MAX_LEARNING_RAGE, learningRate);
    		}
    	}
		this.learningRate = learningRate;
	}

}

package com.winkelmeyer.salesforce.predictivevision.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "probabilities", "object" })
public class PredictionResult {

	@JsonProperty("probabilities")
	private List<Probability> probabilities = new ArrayList<Probability>();
	@JsonProperty("object")
	private String object;

	
	/**
	 * 
	 * @return The probabilities
	 */
	@JsonProperty("probabilities")
	public List<Probability> getProbabilities() {
		return probabilities;
	}

	/**
	 * 
	 * @param probabilities
	 *            The probabilities
	 */
	@JsonProperty("probabilities")
	public void setProbabilities(List<Probability> probabilities) {
		this.probabilities = probabilities;
	}

	/**
	 * 
	 * @return The object
	 */
	@JsonProperty("object")
	public String getObject() {
		return object;
	}

	/**
	 * 
	 * @param object
	 *            The object
	 */
	@JsonProperty("object")
	public void setObject(String object) {
		this.object = object;
	}

}
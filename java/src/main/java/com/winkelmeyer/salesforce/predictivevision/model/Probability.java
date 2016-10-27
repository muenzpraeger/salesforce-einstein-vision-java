package com.winkelmeyer.salesforce.predictivevision.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "label", "probability" })
public class Probability {

	@JsonProperty("label")
	private String label;
	@JsonProperty("probability")
	private float probability;

	/**
	 * 
	 * @return The label
	 */
	@JsonProperty("label")
	public String getLabel() {
		return label;
	}

	/**
	 * 
	 * @param label
	 *            The label
	 */
	@JsonProperty("label")
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * 
	 * @return The probability
	 */
	@JsonProperty("probability")
	public float getProbability() {
		return probability;
	}

	/**
	 * 
	 * @param probability
	 *            The probability
	 */
	@JsonProperty("probability")
	public void setProbability(float probability) {
		this.probability = probability;
	}

}
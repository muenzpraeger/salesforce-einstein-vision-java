package com.winkelmeyer.salesforce.einsteinvision.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "exampleName", "exampleLabel", "predictedLabel" })
public class EpochResult {
	
	@JsonProperty("exampleName")
	private String exampleName;
	@JsonProperty("exampleLabel")
	private String exampleLabel;
	@JsonProperty("predictedLabel")
	private String predictedLabel;
	
	/**
	 * @return the exampleName
	 */
	@JsonProperty("exampleName")
	public String getExampleName() {
		return exampleName;
	}
	
	/**
	 * @param exampleName the exampleName to set
	 */
	@JsonProperty("exampleName")
	public void setExampleName(String exampleName) {
		this.exampleName = exampleName;
	}
	
	/**
	 * @return the exampleLabel
	 */
	@JsonProperty("exampleLabel")
	public String getExampleLabel() {
		return exampleLabel;
	}
	
	/**
	 * @param exampleLabel the exampleLabel to set
	 */
	@JsonProperty("exampleLabel")
	public void setExampleLabel(String exampleLabel) {
		this.exampleLabel = exampleLabel;
	}
	
	/**
	 * @return the predictedLabel
	 */
	@JsonProperty("predictedLabel")
	public String getPredictedLabel() {
		return predictedLabel;
	}
	
	/**
	 * @param predictedLabel the predictedLabel to set
	 */
	@JsonProperty("predictedLabel")
	public void setPredictedLabel(String predictedLabel) {
		this.predictedLabel = predictedLabel;
	}

}

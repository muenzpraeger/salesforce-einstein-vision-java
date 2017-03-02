package com.winkelmeyer.salesforce.predictivevision.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "labels" })
public class LabelSummary {

	@JsonProperty("labels")
	private List<Label> labels = new ArrayList<Label>();

	/**
	 * 
	 * @return The labels
	 */
	@JsonProperty("labels")
	public List<Label> getLabels() {
		return labels;
	}

	/**
	 * 
	 * @param labels
	 *            The labels
	 */
	@JsonProperty("labels")
	public void setLabels(List<Label> labels) {
		this.labels = labels;
	}


}
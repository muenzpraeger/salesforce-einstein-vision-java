package com.winkelmeyer.salesforce.einsteinvision.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "epoch", "epochResults", "metricsData" })
public class ModelLearningCurve {

	@JsonProperty("epoch")
	private int epoch;
	@JsonProperty("epochResults")
	private List<EpochResult> epochResults;
	@JsonProperty("metricsData")
	private ModelMetrics metricsData;
	
	/**
	 * @return the epoch
	 */
	@JsonProperty("epoch")
	public int getEpoch() {
		return epoch;
	}
	
	/**
	 * @param epoch the epoch to set
	 */
	@JsonProperty("epoch")
	public void setEpoch(int epoch) {
		this.epoch = epoch;
	}
	
	/**
	 * @return the testAccuracy
	 */
	@JsonProperty("epochResults")
	public List<EpochResult> getTestepochResultsAccuracy() {
		return epochResults;
	}
	
	/**
	 * @param testAccuracy the testAccuracy to set
	 */
	@JsonProperty("epochResults")
	public void setEpochResults(List<EpochResult> epochResults) {
		this.epochResults = epochResults;
	}
	
	/**
	 * @return the metricsData
	 */
	@JsonProperty("metricsData")
	public ModelMetrics getMetricsData() {
		return metricsData;
	}
	
	/**
	 * @param metricsData the modelMetrics to set
	 */
	@JsonProperty("metricsData")
	public void setMetricsData(ModelMetrics metricsData) {
		this.metricsData = metricsData;
	}

}

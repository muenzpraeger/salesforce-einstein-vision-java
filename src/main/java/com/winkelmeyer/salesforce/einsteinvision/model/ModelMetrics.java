package com.winkelmeyer.salesforce.einsteinvision.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "f1", "testAccuracy", "trainingLoss", "confusionMatrix", "trainingAccuracy", "labels" })
public class ModelMetrics {

	@JsonProperty("f1")
	private List<Double> f1 = new ArrayList<Double>();
	@JsonProperty("testAccuracy")
	private Double testAccuracy;
	@JsonProperty("trainingLoss")
	private Double trainingLoss;
	@JsonProperty("confusionMatrix")
	private List<List<Integer>> confusionMatrix = new ArrayList<List<Integer>>();
	@JsonProperty("trainingAccuracy")
	private Double trainingAccuracy;
	@JsonProperty("labels")
	private List<String> labels;

	/**
	 * 
	 * @return The f1
	 */
	@JsonProperty("f1")
	public List<Double> getF1() {
		return f1;
	}

	/**
	 * 
	 * @param f1
	 *            The f1
	 */
	@JsonProperty("f1")
	public void setF1(List<Double> f1) {
		this.f1 = f1;
	}

	/**
	 * 
	 * @return The testAccuracy
	 */
	@JsonProperty("testAccuracy")
	public Double getTestAccuracy() {
		return testAccuracy;
	}

	/**
	 * 
	 * @param testAccuracy
	 *            The testAccuracy
	 */
	@JsonProperty("testAccuracy")
	public void setTestAccuracy(Double testAccuracy) {
		this.testAccuracy = testAccuracy;
	}

	/**
	 * 
	 * @return The trainingLoss
	 */
	@JsonProperty("trainingLoss")
	public Double getTrainingLoss() {
		return trainingLoss;
	}

	/**
	 * 
	 * @param trainingLoss
	 *            The trainingLoss
	 */
	@JsonProperty("trainingLoss")
	public void setTrainingLoss(Double trainingLoss) {
		this.trainingLoss = trainingLoss;
	}

	/**
	 * 
	 * @return The confusionMatrix
	 */
	@JsonProperty("confusionMatrix")
	public List<List<Integer>> getConfusionMatrix() {
		return confusionMatrix;
	}

	/**
	 * 
	 * @param confusionMatrix
	 *            The confusionMatrix
	 */
	@JsonProperty("confusionMatrix")
	public void setConfusionMatrix(List<List<Integer>> confusionMatrix) {
		this.confusionMatrix = confusionMatrix;
	}

	/**
	 * 
	 * @return The trainingAccuracy
	 */
	@JsonProperty("trainingAccuracy")
	public Double getTrainingAccuracy() {
		return trainingAccuracy;
	}

	/**
	 * 
	 * @param trainingAccuracy
	 *            The trainingAccuracy
	 */
	@JsonProperty("trainingAccuracy")
	public void setTrainingAccuracy(Double trainingAccuracy) {
		this.trainingAccuracy = trainingAccuracy;
	}

	/**
	 * @return the labels
	 */
	@JsonProperty("labels")
	public List<String> getLabels() {
		return labels;
	}

	/**
	 * @param labels the labels to set
	 */
	@JsonProperty("labels")
	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

}
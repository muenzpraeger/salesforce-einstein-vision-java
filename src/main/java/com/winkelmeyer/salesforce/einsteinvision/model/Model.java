package com.winkelmeyer.salesforce.predictivevision.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "datasetId", "name", "status", "Progress", "createdAt", "updatedAt", "learningRate", "epochs",
		"queuePosition", "object", "modelId" })
public class Model {
	
	public enum TrainingStatus {
		QUEUED,
		RUNNING,
		SUCCEEDED,
		FAILED
	}

	@JsonProperty("datasetId")
	private Integer datasetId;
	@JsonProperty("name")
	private String name;
	@JsonProperty("status")
	private String status;
	@JsonProperty("progress")
	private Integer progress;
	@JsonProperty("createdAt")
	private String createdAt;
	@JsonProperty("updatedAt")
	private String updatedAt;
	@JsonProperty("learningRate")
	private Double learningRate;
	@JsonProperty("epochs")
	private Integer epochs;
	@JsonProperty("queuePosition")
	private Integer queuePosition;
	@JsonProperty("object")
	private String object;
	@JsonProperty("modelId")
	private String modelId;
	@JsonProperty("failureMsg")
	private String failureMsg;

	/**
	 * 
	 * @return The datasetId
	 */
	@JsonProperty("datasetId")
	public Integer getDatasetId() {
		return datasetId;
	}

	/**
	 * 
	 * @param datasetId
	 *            The datasetId
	 */
	@JsonProperty("datasetId")
	public void setDatasetId(Integer datasetId) {
		this.datasetId = datasetId;
	}

	/**
	 * 
	 * @return The name
	 */
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 *            The name
	 */
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return The status
	 */
	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status
	 *            The status
	 */
	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 * @return The progress
	 */
	@JsonProperty("Progress")
	public Integer getProgress() {
		return progress;
	}

	/**
	 * 
	 * @param progress
	 *            The Progress
	 */
	@JsonProperty("Progress")
	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	/**
	 * 
	 * @return The createdAt
	 */
	@JsonProperty("createdAt")
	public String getCreatedAt() {
		return createdAt;
	}

	/**
	 * 
	 * @param createdAt
	 *            The createdAt
	 */
	@JsonProperty("createdAt")
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * 
	 * @return The updatedAt
	 */
	@JsonProperty("updatedAt")
	public String getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * 
	 * @param updatedAt
	 *            The updatedAt
	 */
	@JsonProperty("updatedAt")
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * 
	 * @return The learningRate
	 */
	@JsonProperty("learningRate")
	public Double getLearningRate() {
		return learningRate;
	}

	/**
	 * 
	 * @param learningRate
	 *            The learningRate
	 */
	@JsonProperty("learningRate")
	public void setLearningRate(Double learningRate) {
		this.learningRate = learningRate;
	}

	/**
	 * 
	 * @return The epochs
	 */
	@JsonProperty("epochs")
	public Integer getEpochs() {
		return epochs;
	}

	/**
	 * 
	 * @param epochs
	 *            The epochs
	 */
	@JsonProperty("epochs")
	public void setEpochs(Integer epochs) {
		this.epochs = epochs;
	}

	/**
	 * 
	 * @return The queuePosition
	 */
	@JsonProperty("queuePosition")
	public Integer getQueuePosition() {
		return queuePosition;
	}

	/**
	 * 
	 * @param queuePosition
	 *            The queuePosition
	 */
	@JsonProperty("queuePosition")
	public void setQueuePosition(Integer queuePosition) {
		this.queuePosition = queuePosition;
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

	/**
	 * 
	 * @return The modelId
	 */
	@JsonProperty("modelId")
	public String getModelId() {
		return modelId;
	}

	/**
	 * 
	 * @param modelId
	 *            The modelId
	 */
	@JsonProperty("modelId")
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	
	/**
	 * 
	 * @return The failureMsg
	 */
	@JsonProperty("failureMsg")
	public String getFailureMsg() {
		return failureMsg;
	}

	/**
	 * 
	 * @param failureMsg
	 *            The failureMsg
	 */
	@JsonProperty("failureMsg")
	public void setFailureMsg(String failureMsg) {
		this.failureMsg = failureMsg;
	}

}
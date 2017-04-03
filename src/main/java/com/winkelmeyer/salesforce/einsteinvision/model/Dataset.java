package com.winkelmeyer.salesforce.einsteinvision.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "available", "id", "name", "createdAt", "updatedAt", "labelSummary", "statusMsg", "totalExamples", "totalLabels", "object" })
public class Dataset {

	@JsonProperty("available")
	private Boolean available;
	@JsonProperty("id")
	private Integer id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("createdAt")
	private String createdAt;
	@JsonProperty("updatedAt")
	private String updatedAt;
	@JsonProperty("labelSummary")
	private LabelSummary labelSummary;
	@JsonProperty("statusMsg")
	private String statusMsg;
	@JsonProperty("totalExamples")
	private Integer totalExamples;
	@JsonProperty("totalLabels")
	private Integer totalLabels;
	@JsonProperty("object")
	private String object;
	@JsonProperty("type")
	private String type;


	public Dataset() {
	}

	/**
	 * 
	 * @return The id
	 */
	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 *            The id
	 */
	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
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
	public void setName(String name) throws Exception {
		this.name = name;
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
	 * @return The labelSummary
	 */
	@JsonProperty("labelSummary")
	public LabelSummary getLabelSummary() {
		return labelSummary;
	}

	/**
	 * 
	 * @param labelSummary
	 *            The labelSummary
	 */
	@JsonProperty("labelSummary")
	public void setLabelSummary(LabelSummary labelSummary) {
		this.labelSummary = labelSummary;
	}

	/**
	 * 
	 * @return The totalExamples
	 */
	@JsonProperty("totalExamples")
	public Integer getTotalExamples() {
		return totalExamples;
	}

	/**
	 * 
	 * @param totalExamples
	 *            The totalExamples
	 */
	@JsonProperty("totalExamples")
	public void setTotalExamples(Integer totalExamples) {
		this.totalExamples = totalExamples;
	}

	/**
	 * 
	 * @return The totalLabels
	 */
	@JsonProperty("totalLabels")
	public Integer getTotalLabels() {
		return totalLabels;
	}

	/**
	 * 
	 * @param totalLabels
	 *            The totalLabels
	 */
	@JsonProperty("totalLabels")
	public void setTotalLabels(Integer totalLabels) {
		this.totalLabels = totalLabels;
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
	 * @return
	 */
	public Boolean getAvailable() {
		return available;
	}

	/**
	 * 
	 * @param available
	 */
	public void setAvailable(Boolean available) {
		this.available = available;
	}

	/**
	 * 
	 * @return
	 */
	public String getStatusMsg() {
		return statusMsg;
	}

	/**
	 * 
	 * @param statusMsg
	 */
	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}
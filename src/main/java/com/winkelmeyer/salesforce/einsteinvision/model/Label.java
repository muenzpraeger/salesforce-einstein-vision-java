package com.winkelmeyer.salesforce.einsteinvision.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.winkelmeyer.salesforce.einsteinvision.exceptions.StringIsEmptyException;
import com.winkelmeyer.salesforce.einsteinvision.exceptions.StringTooLongException;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "datasetId", "name", "numExamples", "object" })
public class Label {

	@JsonProperty("id")
	private Integer id;
	@JsonProperty("datasetId")
	private Integer datasetId;
	@JsonProperty("name")
	private String name;
	@JsonProperty("numExamples")
	private Integer numExamples;
	@JsonProperty("object")
	private String object;

	public Label() {}

	public Label(String name) throws Exception {
		if (name.isEmpty()) {
			throw new StringIsEmptyException("name");
		}
		if (name.length()>180) {
			throw new StringTooLongException("name", 180, name.length());
		}
		this.name = name;
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
	 * @return The numExamples
	 */
	@JsonProperty("numExamples")
	public Integer getNumExamples() {
		return numExamples;
	}

	/**
	 * 
	 * @param numExamples
	 *            The numExamples
	 */
	@JsonProperty("numExamples")
	public void setNumExamples(Integer numExamples) {
		this.numExamples = numExamples;
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
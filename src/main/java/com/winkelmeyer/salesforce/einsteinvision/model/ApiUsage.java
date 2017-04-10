package com.winkelmeyer.salesforce.einsteinvision.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "organizationId", "startsAt", "endsAt", "licenseId", "predictionsMax", "predictionsRemaining", "predictionsUsed" })
public class ApiUsage {
	
	@JsonProperty("id")
	private long id;
	@JsonProperty("organizationId")
	private long organizationId;
	@JsonProperty("startsAt")
	private String startsAt;
	@JsonProperty("endsAt")
	private String endsAt;
	@JsonProperty("licenseId")
	private String licenseId;
	@JsonProperty("predictionsMax")
	private long predictionsMax;
	@JsonProperty("predictionsRemaining")
	private long predictionsRemaining;
	@JsonProperty("predictionsUsed")
	private long predictionsUsed;
	@JsonProperty("planData")
	private PlanData[] planData;
	@JsonProperty("object")
	private String object;
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * @return the organizationId
	 */
	public long getOrganizationId() {
		return organizationId;
	}
	
	/**
	 * @param organizationId the organizationId to set
	 */
	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
	}
	
	/**
	 * @return the startsAt
	 */
	public String getStartsAt() {
		return startsAt;
	}
	
	/**
	 * @param startsAt the startsAt to set
	 */
	public void setStartsAt(String startsAt) {
		this.startsAt = startsAt;
	}
	
	/**
	 * @return the endsAt
	 */
	public String getEndsAt() {
		return endsAt;
	}
	
	/**
	 * @param endsAt the endsAt to set
	 */
	public void setEndsAt(String endsAt) {
		this.endsAt = endsAt;
	}
	
	/**
	 * @return the licenseId
	 */
	public String getLicenseId() {
		return licenseId;
	}
	
	/**
	 * @param licenseId the licenseId to set
	 */
	public void setLicenseId(String licenseId) {
		this.licenseId = licenseId;
	}
	
	/**
	 * @return the predictionsMax
	 */
	public long getPredictionsMax() {
		return predictionsMax;
	}
	
	/**
	 * @param predictionsMax the predictionsMax to set
	 */
	public void setPredictionsMax(long predictionsMax) {
		this.predictionsMax = predictionsMax;
	}
	
	/**
	 * @return the predictionsRemaining
	 */
	public long getPredictionsRemaining() {
		return predictionsRemaining;
	}
	
	/**
	 * @param predictionsRemaining the predictionsRemaining to set
	 */
	public void setPredictionsRemaining(long predictionsRemaining) {
		this.predictionsRemaining = predictionsRemaining;
	}
	
	/**
	 * @return the predictionsUsed
	 */
	public long getPredictionsUsed() {
		return predictionsUsed;
	}
	
	/**
	 * @param predictionsUsed the predictionsUsed to set
	 */
	public void setPredictionsUsed(long predictionsUsed) {
		this.predictionsUsed = predictionsUsed;
	}

	/**
	 * @return the planData
	 */
	public PlanData[] getPlanData() {
		return planData;
	}

	/**
	 * @param planData the planData to set
	 */
	public void setPlanData(PlanData[] planData) {
		this.planData = planData;
	}

	/**
	 * @return the object
	 */
	public String getObject() {
		return object;
	}

	/**
	 * @param object the object to set
	 */
	public void setObject(String object) {
		this.object = object;
	}

}

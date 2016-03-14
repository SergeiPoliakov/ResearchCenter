package com.netcracker.unc.newmvc.dao.models;

public class IncomeConsumptionModel {

	private long fullIncome, maxIncome, minIncome, avgIncome;
	private long fullConsumption, maxConsumption, minConsumption, avgConsumption;
	private String maxIncomeName, minIncomeName;
	private String maxConsumptionName, minConsumptionName;

	public long getFullIncome() {
		return fullIncome;
	}

	public void setFullIncome(long fullIncome) {
		this.fullIncome = fullIncome;
	}

	public long getMaxIncome() {
		return maxIncome;
	}

	public void setMaxIncome(long maxIncome) {
		this.maxIncome = maxIncome;
	}

	public long getMinIncome() {
		return minIncome;
	}

	public void setMinIncome(long minIncome) {
		this.minIncome = minIncome;
	}

	public long getAvgIncome() {
		return avgIncome;
	}

	public void setAvgIncome(long avgIncome) {
		this.avgIncome = avgIncome;
	}

	public long getFullConsumption() {
		return fullConsumption;
	}

	public void setFullConsumption(long fullConsumption) {
		this.fullConsumption = fullConsumption;
	}

	public long getMaxConsumption() {
		return maxConsumption;
	}

	public void setMaxConsumption(long maxConsumption) {
		this.maxConsumption = maxConsumption;
	}

	public long getMinConsumption() {
		return minConsumption;
	}

	public void setMinConsumption(long minConsumption) {
		this.minConsumption = minConsumption;
	}

	public long getAvgConsumption() {
		return avgConsumption;
	}

	public void setAvgConsumption(long avgConsumption) {
		this.avgConsumption = avgConsumption;
	}

	public String getMaxIncomeName() {
		return maxIncomeName;
	}

	public void setMaxIncomeName(String maxIncomeName) {
		this.maxIncomeName = maxIncomeName;
	}

	public String getMinIncomeName() {
		return minIncomeName;
	}

	public void setMinIncomeName(String minIncomeName) {
		this.minIncomeName = minIncomeName;
	}

	public String getMaxConsumptionName() {
		return maxConsumptionName;
	}

	public void setMaxConsumptionName(String maxConsumptionName) {
		this.maxConsumptionName = maxConsumptionName;
	}

	public String getMinConsumptionName() {
		return minConsumptionName;
	}

	public void setMinConsumptionName(String minConsumptionName) {
		this.minConsumptionName = minConsumptionName;
	}

}

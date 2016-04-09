package com.netcracker.unc.newmvc.ejb.models;

import javax.ejb.Stateful;

@Stateful
public class CategoryModel {
	private long objectId = 0;
	private String objectName = null;
	private double minPercent = 0;
	private double maxPercent = 0;
	private double coefficient = 0;
	private double sumCategory = 0;
	private double percent = 0;

	public long getObjectId() {
		return objectId;
	}

	public void setObjectId(long objectId) {
		this.objectId = objectId;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public double getMinPercent() {
		return minPercent;
	}

	public void setMinPercent(double minPercent) {
		this.minPercent = minPercent;
	}

	public double getMaxPercent() {
		return maxPercent;
	}

	public void setMaxPercent(double maxPercent) {
		this.maxPercent = maxPercent;
	}

	public double getCoeficient() {
		return coefficient;
	}

	public void setCoeficient(double coefficient) {
		this.coefficient = coefficient;
	}

	public double getSumCategory() {
		return sumCategory;
	}

	public void setSumCategory(double sumCategory) {
		this.sumCategory = sumCategory;
	}
}

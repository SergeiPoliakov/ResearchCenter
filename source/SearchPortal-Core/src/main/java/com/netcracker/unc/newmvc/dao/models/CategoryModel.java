package com.netcracker.unc.newmvc.dao.models;

import java.math.BigDecimal;
import java.sql.Date;

public class CategoryModel {
	private Integer objectId = 0;
	private String objectName = null;
	private BigDecimal minPercent = null;
	private BigDecimal maxPercent = null;
	private BigDecimal coefficient = null;
	private BigDecimal sumCategory = null;
	private double percent = 0;

	public Integer getObjectId() {
		return objectId;
	}

	public void setObjectId(Integer objectId) {
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

	public BigDecimal getMinPercent() {
		return minPercent;
	}

	public void setMinPercent(BigDecimal minPercent) {
		this.minPercent = minPercent;
	}

	public BigDecimal getMaxPercent() {
		return maxPercent;
	}

	public void setMaxPercent(BigDecimal maxPercent) {
		this.maxPercent = maxPercent;
	}

	public BigDecimal getCoeficient() {
		return coefficient;
	}

	public void setCoeficient(BigDecimal coefficient) {
		this.coefficient = coefficient;
	}

	public BigDecimal getSumCategory() {
		return sumCategory;
	}

	public void setSumCategory(BigDecimal sumCategory) {
		this.sumCategory = sumCategory;
	}
}

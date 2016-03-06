package com.netcracker.unc.priorityModule;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CategoryModelForTable {
	private String objectName;
	private BigDecimal calculatedValue;

	public CategoryModelForTable() {
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public BigDecimal getCalculatedValue() {
		return calculatedValue;
	}

	public void setCalculatedValue(BigDecimal calculatedValue) {
		this.calculatedValue = calculatedValue;
	}

	public CategoryModelForTable(String objectName, BigDecimal calculatedValue) {
		this.objectName = objectName;
		this.calculatedValue = calculatedValue;
	}
}

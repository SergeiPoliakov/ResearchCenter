package com.netcracker.unc.priorityModule;

import java.math.BigInteger;

public class CategoryModelForTable {
	private String objectName;
	//private BigInteger calculatedValue; 
	private int calculatedValue;
	
	public CategoryModelForTable() {
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public int getCalculatedValue() {
		return calculatedValue;
	}

	public void setCalculatedValue(int calculatedValue) {
		this.calculatedValue = calculatedValue;
	}

	public CategoryModelForTable(String objectName, int calculatedValue) {
		this.objectName = objectName;
		this.calculatedValue = calculatedValue;
	}
	
	@Override
	public String toString(){
		
		return "objectName: " + objectName + "; calculatedValue: " + calculatedValue  ;
	}
}

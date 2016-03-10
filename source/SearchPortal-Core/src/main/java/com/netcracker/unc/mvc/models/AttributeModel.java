package com.netcracker.unc.mvc.models;

public class AttributeModel {

	private String attributeName = null;
	private int finObjectTypeID;
	private int attributeID;

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName.toLowerCase();
	}

	public int getFinObjectTypeID() {
		return finObjectTypeID;
	}

	public void setFinObjectTypeID(int finObjectTypeID) {
		this.finObjectTypeID = finObjectTypeID;
	}

	public int getAttributeID() {
		return attributeID;
	}

	public void setAttributeID(int attributeID) {
		this.attributeID = attributeID;
	}

}

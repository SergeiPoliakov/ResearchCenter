package com.netcracker.unc.newmvc.dao;

import java.sql.Date;

public class ParamModel {

	private String value;
	private Date valueDate;
	private int finObjectId = 0;
	private int attributeId = 0;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getValueDate() {
		return valueDate;
	}

	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}

	public int getFinObjectId() {
		return finObjectId;
	}

	public void setFinObjectId(int finObjectId) {
		this.finObjectId = finObjectId;
	}

	public int getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(int attributeId) {
		this.attributeId = attributeId;
	}
	
}

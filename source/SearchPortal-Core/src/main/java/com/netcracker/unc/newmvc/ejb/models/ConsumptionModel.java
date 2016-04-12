package com.netcracker.unc.newmvc.ejb.models;

import java.sql.Date;

import com.netcracker.unc.newmvc.ejb.entities.EntityObject;

public class ConsumptionModel {
	private int consumptionId;
	private int userId;
	private String consumptionName;
	private int consumptionSum;
	private boolean month;
	private Date dateConsumption;
	private EntityObject parentObj;

	public int getConsumptionId() {
		return consumptionId;
	}

	public void setConsumptionId(int consumptionId) {
		this.consumptionId = consumptionId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getConsumptionName() {
		return consumptionName;
	}

	public void setConsumptionName(String consumptionName) {
		this.consumptionName = consumptionName;
	}

	public int getConsumptionSum() {
		return consumptionSum;
	}

	public void setConsumptionSum(int consumptionSum) {
		this.consumptionSum = consumptionSum;
	}

	public boolean isMonth() {
		return month;
	}

	public void setMonth(boolean month) {
		this.month = month;
	}

	public Date getDateConsumption() {
		return dateConsumption;
	}

	public void setDateConsumption(Date dateConsumption) {
		this.dateConsumption = dateConsumption;
	}

	public EntityObject getParentObj() {
		return parentObj;
	}

	public void setParentObj(EntityObject parentObj) {
		this.parentObj = parentObj;
	}
}

package com.netcracker.unc.newmvc.ejb.models;

import java.sql.Date;
import javax.ejb.Stateful;

@Stateful
public class SalaryModel {

	private long userId, objectId, dateCount;
	private String objectName, value;
	private Date lastCheckDate;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getObjectId() {
		return objectId;
	}

	public void setObjectId(long objectId) {
		this.objectId = objectId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public long getDateCount() {
		return dateCount;
	}

	public void setDateCount(long dateCount) {
		this.dateCount = dateCount;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public Date getLastCheckDate() {
		return lastCheckDate;
	}

	public void setLastCheckDate(Date lastCheckDate) {
		this.lastCheckDate = lastCheckDate;
	}

}

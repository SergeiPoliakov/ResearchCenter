package com.netcracker.unc.newmvc.ejb.models;

import javax.ejb.Stateful;

@Stateful
public class ActiveCasesModel {

	private String hierarchy;
	private String caseName;
	private String caseCost;
	private String startDate;
	private String endDate;
	private String parentName;
	private String spaceHierarchy = "";
	private double priority;
	private String priorityStr;
	private int level;
	private long objectId;

	public String getHierarchy() {
		return hierarchy;
	}

	public void setHierarchy(String hierarchy) {
		this.hierarchy = hierarchy;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getCaseCost() {
		return caseCost;
	}

	public void setCaseCost(String caseCost) {
		this.caseCost = caseCost;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getSpaceHierarchy() {
		return spaceHierarchy;
	}

	public void setSpaceHierarchy(String spaceHierarchy) {
		this.spaceHierarchy = spaceHierarchy;
	}

	public double getPriority() {
		return priority;
	}

	public void setPriority(double priority) {
		this.priority = priority;
	}

	public String getPriorityStr() {
		return priorityStr;
	}

	public void setPriorityStr(String priorityStr) {
		this.priorityStr = priorityStr;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public long getObjectId() {
		return objectId;
	}

	public void setObjectId(long objectId) {
		this.objectId = objectId;
	}

}

package com.netcracker.unc.newmvc.dao.models;

public class ActiveCasesModel {

	private String hierarchy = null;
	private String caseName = null;
	private String caseCost = null;
	private String startDate = null;
	private String endDate = null;
	private String parentName = null;
	private String spaceHierarchy = "";
	private double priority;
	private String priorityStr = null;
	private int level;
	int objectId;

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

	public String getSpace() {
		return spaceHierarchy;
	}

	public void setSpace(String space) {
		this.spaceHierarchy = space;
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

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}

}

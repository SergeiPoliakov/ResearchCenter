package com.netcracker.unc.beans.custom.models;

public class ActiveCasesModel {

	private String hierarchy = null;
	private String caseName = null;
	private String caseCost = null;
	private String startDate = null;
	private String endDate = null;
	private String parentName = null;
	private String space = "";
	private double priority;
	private String priorityStr = null;
	private int level;
	int objectId;

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public double getPriority() {
		return priority;
	}

	public void setPriority(double priority) {
		if (priority == 0.75)
			priorityStr = "высокий";
		if (priority == 0.5)
			priorityStr = "средний";
		if (priority == 0.35)
			priorityStr = "низкий";
		this.priority = priority;
	}

	public String getPriorityStr() {
		return priorityStr;
	}

	public void setPriorityStr(String priorityStr) {
		this.priorityStr = priorityStr;
	}

	public String getSpace() {
		return space;
	}

	// set hierarchy spaces for jsp view
	public void setSpace(int i) {
		int count = 0;
		while (count < (i - 2) * 6) {
			space += "&nbsp;";
			count++;
		}
	}

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

}

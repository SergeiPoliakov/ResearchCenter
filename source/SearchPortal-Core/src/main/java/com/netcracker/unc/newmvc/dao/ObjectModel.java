package com.netcracker.unc.newmvc.dao;

import java.util.List;

public class ObjectModel {

	private int finObjectId;
	private int parentId;
	private String objectName;
	private int finObjectTypeId;
	private int userId;
	private String finObjectTypeName;
	private List<ParamModel> allParams;

	public List<ParamModel> getAllParams() {
		return allParams;
	}

	public void setAllParams(List<ParamModel> allParams) {
		this.allParams = allParams;
	}

	public int getFinObjectId() {
		return finObjectId;
	}

	public void setFinObjectId(int finObjectId) {
		this.finObjectId = finObjectId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public int getFinObjectTypeId() {
		return finObjectTypeId;
	}

	public void setFinObjectTypeId(int finObjectTypeId) {
		this.finObjectTypeId = finObjectTypeId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFinObjectTypeName() {
		return finObjectTypeName;
	}

	public void setFinObjectTypeName(String finObjectTypeName) {
		this.finObjectTypeName = finObjectTypeName;
	}
}

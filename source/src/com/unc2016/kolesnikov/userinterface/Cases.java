package com.unc2016.kolesnikov.userinterface;

// класс храниты атрибуты задачи
public class Cases {

	private String caseName = null;
	private String caseType = null;
	private Integer caseTypeId = null;
	private Integer caseId = null;
	private Integer userId = null;
	private String parentTypeName = null;
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getParentTypeName() {
		return parentTypeName;
	}
	public void setParentTypeName(String parentTypeName) {
		this.parentTypeName = parentTypeName;
	}
	public String getCaseName() {
		return caseName;
	}
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	public Integer getCaseTypeId() {
		return caseTypeId;
	}
	public void setCaseTypeId(Integer caseTypeId) {
		this.caseTypeId = caseTypeId;
	}
	public Integer getCaseId() {
		return caseId;
	}
	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}
	
	
	
}

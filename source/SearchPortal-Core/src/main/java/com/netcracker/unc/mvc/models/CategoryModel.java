/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.unc.mvc.models;

import java.math.BigDecimal;
import java.sql.Date;

public class CategoryModel {
	private Integer objectId = 0;
	private String objectName = null;
	private BigDecimal minPercent = null;
	private BigDecimal maxPercent = null;
	private Date finalDate = null;
	private BigDecimal coeficient = null;
	private BigDecimal sumCategory = null;

	public Integer getObjectId() {
		return objectId;
	}

	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public BigDecimal getMinPercent() {
		return minPercent;
	}

	public void setMinPercent(BigDecimal minPercent) {
		this.minPercent = minPercent;
	}

	public BigDecimal getMaxPercent() {
		return maxPercent;
	}

	public void setMaxPercent(BigDecimal maxPercent) {
		this.maxPercent = maxPercent;
	}

	public Date getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}

	public BigDecimal getCoeficient() {
		return coeficient;
	}

	public void setCoeficient(BigDecimal coeficient) {
		this.coeficient = coeficient;
	}

	public BigDecimal getSumCategory() {
		return sumCategory;
	}

	public void setSumCategory(BigDecimal sumCategory) {
		this.sumCategory = sumCategory;
	}

}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.unc.mvc.models;

import java.math.BigDecimal;
import java.sql.Date;

public class CategoryModel {

	public final String Type = "Категория";
	private Integer objectId;
	private String objectName;
	private BigDecimal minPercent;
	private BigDecimal maxPercent;
	private Date finalDate;
	private BigDecimal coeficient;
	private BigDecimal sumCategory;

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

	@Override
	public String toString() {

		StringBuilder classString = new StringBuilder("<br/>Объектный тип Категория.");

		if (objectId != null) {
			classString.append("<br/> objectId = ");
			classString.append(objectId);
		}

		if (objectName != null) {
			classString.append("<br/> objectName = ");
			classString.append(objectName);
		}

		if (sumCategory != null) {
			classString.append("<br/> sumCategory = ");
			classString.append(sumCategory.toString());
		} else {
			classString.append("<br/> sumCategory пуст");
		}

		if (coeficient != null) {
			classString.append("<br/> coeficient = ");
			classString.append(coeficient.toString());
		} else {
			classString.append("<br/> coeficient пуст");
		}

		if (minPercent != null ) {
			classString.append("<br/> minPercent = ");
			classString.append(minPercent.toString());
		} else {
			classString.append("<br/> minPercent пуст");
		}
		
		if (maxPercent != null) {
			classString.append("<br/> maxPercent = ");
			classString.append(maxPercent.toString());
		} else {
			classString.append("<br/> maxPercent пуст");
		}
		
		if (finalDate != null) {
			classString.append("<br/> finalDate = ");
			classString.append(finalDate.toString());
		} else {
			classString.append("<br/> finalDate пуст");
		}
		
		return classString.toString();
	}

	public CategoryModel() {
	}

	public CategoryModel(Integer objectId, String objectName, BigDecimal minPercent, BigDecimal maxPercent,
			Date finalDate, BigDecimal coeficient, BigDecimal sumCategory) {
		this.objectId = objectId;
		this.objectName = objectName;
		this.minPercent = minPercent;
		this.maxPercent = maxPercent;
		this.finalDate = finalDate;
		this.coeficient = coeficient;
	}
}
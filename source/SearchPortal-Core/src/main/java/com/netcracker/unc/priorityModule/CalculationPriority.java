/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.unc.priorityModule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.netcracker.unc.newmvc.dao.models.CategoryModel;

public class CalculationPriority {

	private List<CategoryModel> categoryList;
	private double sumIncome = 0;

	public List<CategoryModel> toCalculate() {
		double onePercent = getOnePercentValue();
			for (CategoryModel cm : categoryList) {
				double percent = calculationIntermediateValue(cm) / onePercent;
				cm.setPercent(percent);
			}
		if (categoryList.isEmpty())
			categoryList = Collections.emptyList();
		return categoryList;
	}
	
	private double getOnePercentValue(){
		double sumValue = 0;
		for (CategoryModel cm : categoryList) {
			sumValue = sumValue + calculationIntermediateValue(cm);
		}
		return sumValue/100;
	}

	private double calculationIntermediateValue(CategoryModel cm) {
		if (sumIncome * (cm.getMaxPercent().doubleValue() / 100) - cm.getSumCategory().doubleValue() < 0) {
			return 0;
		} else {

			double value = cm.getCoeficient().doubleValue()
					* (sumIncome * (cm.getMaxPercent().doubleValue() + cm.getMinPercent().doubleValue()) / (2 * 100)
							- cm.getSumCategory().doubleValue());
			return value;
		}
	}

	public CalculationPriority(List<CategoryModel> categoryList, double sumIncome) {
		this.categoryList = new ArrayList<>(categoryList);
		this.sumIncome = sumIncome;
	}

	public CalculationPriority() {
	}
}

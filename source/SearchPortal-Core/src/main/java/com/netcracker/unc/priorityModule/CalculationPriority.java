/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.unc.priorityModule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.netcracker.unc.mvc.models.CategoryModel;

public class CalculationPriority {

	public static List<ModelForTable> convertToTableView(List<CategoryModel> categoryModel,double sumInvoice, double sumIncome) {

		List<ModelForTable> resultCategoryList;
		ModelForTable modelForTable = null;
		if (categoryModel != null && !categoryModel.isEmpty()) {
			resultCategoryList = new ArrayList<ModelForTable>();
			for (CategoryModel catmod : categoryModel) {
				modelForTable = new ModelForTable();
				modelForTable.setName(catmod.getObjectName());
				modelForTable.setValue(catmod.getSumCategory().doubleValue());
				resultCategoryList.add(modelForTable);
			}
		} else {
			resultCategoryList = Collections.emptyList();
		}
		return resultCategoryList;
	}

	private int getDifferenceCoefficient() {
		return 1;
	}

	private int getSumIncome(List incomeModel) {// <IncomeModel>
		return 0;
	}

	private int getSumAccount(List AccountModel) {// <AccountModel>
		return 0;
	}

	private int getAVGPriorValue() {

		return 0;
	}

	private int calcPriorValueColumn(CategoryModel catmod) {
		return 0;
	}

	@Override
	public String toString() {
		String statusString = "Класс анализирует и возвращает статистику" + " по расходам пользователя.";
		return statusString;
	}
}

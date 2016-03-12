/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.unc.priorityModule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.netcracker.unc.newmvc.dao.CategoryModel;

public class CalculationPriority {

	private ArrayList<CategoryModel> categoryList = null;

	public List<CategoryModel> toCalculate(List<CategoryModel> startList) {
		List<CategoryModel> categoryModels = new ArrayList<CategoryModel>(startList);

		double centerValue = 0;
		double sumCategories = 0;
		for (CategoryModel cm : categoryModels) {
			sumCategories = sumCategories + cm.getSumCategory().doubleValue();
		}
		centerValue = sumCategories / categoryModels.size();

		if (categoryModels.isEmpty())
			categoryModels = Collections.emptyList();
		return categoryModels;
	}

	private void calculationRow() {

	}

	public CalculationPriority(ArrayList<CategoryModel> categoryList) {
		this.categoryList = categoryList;
	}

	public CalculationPriority() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * 
	 * public List<ModelForTable> convertToTableView(List<CategoryModel>
	 * categoryList, double sumInvoice, double sumIncome) { List<ModelForTable>
	 * resultList = new ArrayList<ModelForTable>(); double centerPoint = 0;
	 * double differenceCoefficient = 0; if (categoryList != null &&
	 * !categoryList.isEmpty()) {
	 * 
	 * differenceCoefficient = getDifferenceCoefficient(categoryList,
	 * sumInvoice, sumIncome);
	 * 
	 * for (CategoryModel cm : categoryList) {
	 * addCategoryToIntermediateList(cm); }
	 * 
	 * centerPoint = centerValueInCategories();
	 * 
	 * for (IntermediateModel im : intermediateModelList) {
	 * im.setPercent(getpositionOnAxis(im.getValue(), centerPoint) *
	 * differenceCoefficient); } // intermediateModelList.sort(c); //прикрутить
	 * сортировку for (IntermediateModel im : intermediateModelList) {
	 * ModelForTable modelForTable = new ModelForTable();
	 * modelForTable.setName(im.getObjectName());
	 * modelForTable.setValue(im.getValue());
	 * modelForTable.setParentId(im.getParentId()); }
	 * 
	 * } else { resultList = Collections.emptyList(); }
	 * 
	 * return resultList; }
	 * 
	 * private void addCategoryToIntermediateList(CategoryModel categoryModel) {
	 * if (intermediateModelList == null) { intermediateModelList = new
	 * ArrayList<IntermediateModel>(); } IntermediateModel intermediateModel =
	 * new IntermediateModel();
	 * intermediateModel.setObjectId(categoryModel.getObjectId());
	 * intermediateModel.setObjectName(categoryModel.getObjectName());
	 * intermediateModel.setValue(calculationValue(categoryModel)); }
	 * 
	 * private double calculationValue(CategoryModel cm) { double value = 1;
	 * value = value * cm.getCoeficient().doubleValue(); value = value *
	 * cm.getSumCategory().doubleValue(); return value; }
	 * 
	 * private double centerValueInCategories() { int count = 0; double sum = 0;
	 * if (intermediateModelList != null && !intermediateModelList.isEmpty()) {
	 * for (IntermediateModel inter : intermediateModelList) { sum = inter.value
	 * + sum; count++; } } else count = 1; return sum / count; }
	 * 
	 * private double getDifferenceCoefficient(List<CategoryModel>
	 * categoryModels, double sumIncome, double sumInvoice) { double sum = 0; if
	 * (categoryModels != null && !categoryModels.isEmpty()) { for
	 * (CategoryModel cm : categoryModels) { sum = sum +
	 * cm.getSumCategory().doubleValue(); } }
	 * 
	 * return sum / (sumIncome + sumInvoice); }
	 * 
	 * private double getpositionOnAxis(double value, double centerPoint) {
	 * double x = 0; return x; }
	 */
}

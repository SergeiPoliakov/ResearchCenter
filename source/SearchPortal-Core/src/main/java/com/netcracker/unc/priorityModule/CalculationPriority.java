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

	private List<IntermediateModel> intermediateModelList = null;

	public List<ModelForTable> convertToTableView(List<CategoryModel> categoryList, double sumInvoice,
			double sumIncome) {
		List<ModelForTable> resultList = new ArrayList<ModelForTable>();

		if (categoryList != null && !categoryList.isEmpty()) {
			for (CategoryModel cm : categoryList) {
				addCategoryToIntermediateList(cm);
			}
		} else {
			resultList = Collections.emptyList();
		}
		/*
		 * List<ModelForTable> resultCategoryList; ModelForTable modelForTable =
		 * null; if (categoryModel != null && !categoryModel.isEmpty()) {
		 * resultCategoryList = new ArrayList<ModelForTable>(); for
		 * (CategoryModel catmod : categoryModel) { modelForTable = new
		 * ModelForTable(); modelForTable.setName(catmod.getObjectName());
		 * modelForTable.setValue(catmod.getSumCategory().doubleValue());
		 * resultCategoryList.add(modelForTable); }
		 */
		return resultList;
	}

	private void addCategoryToIntermediateList(CategoryModel categoryModel) {
		if (intermediateModelList == null) {
			intermediateModelList = new ArrayList<IntermediateModel>();
		}
		IntermediateModel intermediateModel = new IntermediateModel();
		intermediateModel.setObjectId(categoryModel.getObjectId());
		intermediateModel.setObjectName(categoryModel.getObjectName());
		intermediateModel.setValue(calculationValue(categoryModel));
	}

	private double calculationValue(CategoryModel cm) {
		double value = 0;
		value = cm.getCoeficient().doubleValue() * cm.getSumCategory().doubleValue();
		return value;
	}

	private double centerValueInCategories() {
		int count = 0;
		double sum = 0;
		if (intermediateModelList != null && !intermediateModelList.isEmpty()) {
			for (IntermediateModel inter : intermediateModelList) {
				sum = inter.value + sum;
				count++;
			}
		} else
			count = 1;
		return sum / count;
	}

	private int getDifferenceCoefficient() {
		return 1;
	}

	private int getAVGPriorValue() {

		return 0;
	}

	private class IntermediateModel {
		private int objectId = 0;
		private String objectName = null;
		private double value = 0;
		private int parentId = 0;

		public int getObjectId() {
			return objectId;
		}

		public void setObjectId(int objectId) {
			this.objectId = objectId;
		}

		public String getObjectName() {
			return objectName;
		}

		public void setObjectName(String objectName) {
			this.objectName = objectName;
		}

		public double getValue() {
			return value;
		}

		public void setValue(double value) {
			this.value = value;
		}

		public int getParentId() {
			return parentId;
		}

		public void setParentId(int parentId) {
			this.parentId = parentId;
		}
	}
}

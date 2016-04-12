package com.netcracker.unc.newmvc.ejb.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.netcracker.unc.newmvc.ejb.dao.EjbDAO;
import com.netcracker.unc.newmvc.ejb.entities.EntityUser;
import com.netcracker.unc.newmvc.ejb.models.CategoryModel;;

@Stateless
@LocalBean
public class ControllerCategories {
	@EJB
	private EjbDAO ejb;

	private List<CategoryModel> categoryList;
	private double sumIncome = 0;

	public List<CategoryModel> getCategories(EntityUser user) {

		List<CategoryModel> categoryModels = new ArrayList<CategoryModel>(ejb.getCategories(user.getUserId()));

		if (categoryModels.isEmpty())
			categoryModels = Collections.emptyList();

		return categoryModels;
	}

	public List<CategoryModel> getPriorities(EntityUser user) {
		List<CategoryModel> categoryModels = new ArrayList<CategoryModel>(ejb.getCategoriesWithSum(user.getUserId()));
		// изменить селект так же получаем лист доходов отдельно
		categoryList = new ArrayList<>(categoryModels);

		toCalculate();
		
		if (categoryList.isEmpty())
			categoryList = Collections.emptyList();
		return categoryList;
	}

	private List<CategoryModel> toCalculate() {
		double onePercent = getOnePercentValue();
		for (CategoryModel cm : categoryList) {
			double percent = calculationIntermediateValue(cm) / onePercent;
			cm.setPercent(percent);
		}
		if (categoryList.isEmpty())
			categoryList = Collections.emptyList();
		return categoryList;
	}

	private double getOnePercentValue() {
		double sumValue = 0;
		for (CategoryModel cm : categoryList) {
			sumValue = sumValue + calculationIntermediateValue(cm);
		}
		return sumValue / 100;
	}

	private double calculationIntermediateValue(CategoryModel cm) {
		if (sumIncome * (cm.getMaxPercent() / 100) - cm.getSumCategory() < 0) {
			return 0;
		} else {

			double value = cm.getCoeficient()
					* (sumIncome * (cm.getMaxPercent() + cm.getMinPercent() / (2 * 100) - cm.getSumCategory()));
			return value;
		}
	}
}

package com.netcracker.unc.newmvc.dao.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.netcracker.unc.newmvc.dao.CategoryDAO;
import com.netcracker.unc.newmvc.dao.models.CategoryModel;
import com.netcracker.unc.newmvc.dao.models.UserModel;
import com.netcracker.unc.priorityModule.CalculationPriority;

public class CategoryController {
	
	private UserModel userModel = null;

	public void deleteCategoty(int objectId){
		CategoryDAO categoryDAO = new CategoryDAO();
		
	}
	
	public List<CategoryModel> getCategories(){
		CategoryDAO categoryDAO = new CategoryDAO();
		List<CategoryModel> categoryModels = new ArrayList<CategoryModel>(categoryDAO.getAllUserCategories(userModel.getUserId()));
		if (categoryModels.isEmpty())
			categoryModels = Collections.emptyList();
		return categoryModels;
	}
	
	public List<CategoryModel> getCategoriesWithPriorities() {
		CategoryDAO categoryDAO = new CategoryDAO();
		List<CategoryModel> categoryModels = new ArrayList<CategoryModel>(categoryDAO.getAllCategoriesWithSumUser(userModel.getUserId()));
		double sumIncome = 700;//затычка. Значение будет получатся из юзера
		CalculationPriority calculationPriority = new CalculationPriority(categoryModels, sumIncome);
		categoryModels = calculationPriority.toCalculate();

		if (categoryModels.isEmpty())
			categoryModels = Collections.emptyList();
		return categoryModels;
	}

	public List<CategoryModel> getCategoriesWithSum(int userId) {
		CategoryDAO categoryDAO = new CategoryDAO();
		List<CategoryModel> categoryModels = new ArrayList<CategoryModel>(categoryDAO.getAllCategoriesWithSumUser(userId));
		if (categoryModels.isEmpty())
			categoryModels = Collections.emptyList();
		return categoryModels;
	}


	public CategoryController() {
	}

	public CategoryController(UserModel user) {
		this.userModel = user;
	}
}

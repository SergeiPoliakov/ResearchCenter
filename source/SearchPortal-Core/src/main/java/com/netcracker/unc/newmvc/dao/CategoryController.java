package com.netcracker.unc.newmvc.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.netcracker.unc.priorityModule.CalculationPriority;

public class CategoryController {

	private int userId = 0;

	public List<CategoryModel> getCategoriesWithPriorities(int userId) {
		CategoryDAO categoryDAO = new CategoryDAO();
		List<CategoryModel> categoryModels = new ArrayList<CategoryModel>(categoryDAO.getAllCategoriesUser(userId));

		CalculationPriority calculationPriority = new CalculationPriority();
		categoryModels = calculationPriority.toCalculate(categoryModels);

		if (categoryModels.isEmpty())
			categoryModels = Collections.emptyList();
		return categoryModels;
	}

	public List<CategoryModel> getCategoriesWithSum(int userId) {
		CategoryDAO categoryDAO = new CategoryDAO();
		List<CategoryModel> categoryModels = new ArrayList<CategoryModel>(categoryDAO.getAllCategoriesUser(userId));
		if (categoryModels.isEmpty())
			categoryModels = Collections.emptyList();
		return categoryModels;
	}

	public List<CategoryModel> getCategoriesWithPriorities() {
		CategoryDAO categoryDAO = new CategoryDAO();
		List<CategoryModel> categoryModels = new ArrayList<CategoryModel>(categoryDAO.getAllCategoriesUser(userId));
		if (categoryModels.isEmpty())
			categoryModels = Collections.emptyList();
		return categoryModels;
	}

	public CategoryController() {
		// TODO Auto-generated constructor stub
	}

	public CategoryController(int idUser) {
		this.userId = idUser;
	}
}

package com.netcracker.unc.newmvc.ejb.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.netcracker.unc.newmvc.ejb.dao.EjbDAO;
import com.netcracker.unc.newmvc.ejb.models.CategoryModel;;

@Stateless
@LocalBean
public class ControllerCategories {
	@EJB
	private EjbDAO ejb;

	public List<CategoryModel> getCategories(long userId) {

		List<CategoryModel> categoryModels = new ArrayList<CategoryModel>(ejb.getCategories(userId));

		if (categoryModels.isEmpty())
			categoryModels = Collections.emptyList();

		return categoryModels;
	}
	/*
	 * public List<CategoryModel> getCategories() { CategoryDAO categoryDAO =
	 * new CategoryDAO(); List<CategoryModel> categoryModels = new
	 * ArrayList<CategoryModel>(
	 * categoryDAO.getAllUserCategories(userModel.getUserId())); if
	 * (categoryModels.isEmpty()) categoryModels = Collections.emptyList();
	 * return categoryModels; }
	 */
}

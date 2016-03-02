package com.netcracker.unc.mvc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.netcracker.unc.mvc.connection.ConnectionFactory;
import com.netcracker.unc.mvc.dao.CategoryDao;
import com.netcracker.unc.mvc.models.CategoryModel;
import com.netcracker.unc.mvc.models.UserModel;

public class CategoryController {

	private UserModel user;

	private CategoryModel categoryModel = null;
	private CategoryDao categoryDao = null;

	private PreparedStatement prepare = null;
	private ResultSet result = null;
	private Connection connect = null;

	public List<CategoryModel> getAllUserCategories() {

		List<CategoryModel> categoryList = new ArrayList<CategoryModel>();

		try {
			connect = ConnectionFactory.getConnection();
			prepare = connect.prepareStatement(SQLQuery.SP_GET_USER_CATEGORIES);
			// prepare.setInt(1, user.get_user_id());
			result = prepare.executeQuery();

			while (result.next()) {
				categoryModel = new CategoryModel();
				categoryModel.setObjectId(result.getInt(1));
				categoryModel.setObjectName(result.getString(2));
				if (result.getObject(3) != null) {
					categoryModel.setCoeficient(result.getBigDecimal(3));
				} else {
					categoryModel.setCoeficient(new BigDecimal(0));
				}
				if (result.getObject(4) != null) {
					categoryModel.setMinPercent(result.getBigDecimal(4));
				} else {
					categoryModel.setMinPercent(new BigDecimal(0));
				}
				
				if (result.getObject(5) != null) {
					categoryModel.setMaxPercent(result.getBigDecimal(5));
				} else {
					categoryModel.setMaxPercent(new BigDecimal(0));
				}
				
				if (result.getObject(6) != null) {
					categoryModel.setFinalDate(result.getDate(6));
				} 
				
				if (result.getObject(7) != null) {
					categoryModel.setSumCategory(result.getBigDecimal(7));
				} else {
					categoryModel.setSumCategory(new BigDecimal(0));
				}
				// category.setCoeficient(Float.parseFloat(result.getString(4)));
				// category.setMinPercent(Integer.parseInt(result.getString(5)));
				// category.setMaxPercent(Integer.parseInt(result.getString(6)));
				// category.setFinalDate(result.getString(7));
				// category.setSumCategory(Integer.parseInt(result.getString(8)));
				categoryList.add(categoryModel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (categoryList == null && categoryList.isEmpty())
				categoryList = Collections.emptyList();

			try {
				if (result != null && !result.isClosed())
					result.close();
				if (prepare != null && !prepare.isClosed())
					prepare.close();
				if (connect != null && !connect.isClosed())
					connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return categoryList;
	}

	public CategoryController() {
		user = new UserModel();
	}

	public CategoryController(UserModel UserFromPage) {
		user = UserFromPage;
	}
}

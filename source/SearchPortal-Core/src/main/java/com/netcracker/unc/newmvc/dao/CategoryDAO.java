package com.netcracker.unc.newmvc.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.netcracker.unc.newmvc.connection.ConnectionFactory;
import com.netcracker.unc.newmvc.dao.models.CategoryModel;
import com.netcracker.unc.newmvc.dao.queries.CategoryQueries;

public class CategoryDAO {

	public List<CategoryModel> getAllCategoriesUser(int userId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<CategoryModel> listOfCategories = new ArrayList<CategoryModel>();
		try {
			connection = ConnectionFactory.getConnection();
			preparedStatement = connection.prepareStatement(CategoryQueries.SP_GET_FULL_CATEGORIES_WITH_SUM);
			//preparedStatement = connection.prepareStatement(CategoryQueries.SP_GET_USER_CATEGORIES_WITH_SUM);
			//preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				CategoryModel categoryModel = new CategoryModel();

				if (resultSet.getObject("OBJECT_ID") != null) {
					categoryModel.setObjectId(resultSet.getInt("OBJECT_ID"));
				}
				if (resultSet.getObject("OBJECT_NAME") != null) {
					categoryModel.setObjectName(resultSet.getString("OBJECT_NAME"));
				}
				if (resultSet.getObject("COEFFICIENT") != null) {
					categoryModel.setCoeficient(resultSet.getBigDecimal("COEFFICIENT"));
				} else {
					categoryModel.setCoeficient(new BigDecimal(0));
				}
				if (resultSet.getObject("MIN_PERCENT") != null) {
					categoryModel.setMinPercent(resultSet.getBigDecimal("MIN_PERCENT"));
				} else {
					categoryModel.setMinPercent(new BigDecimal(0));
				}
				if (resultSet.getObject("MAX_PERCENT") != null) {
					categoryModel.setMaxPercent(resultSet.getBigDecimal("MAX_PERCENT"));
				} else {
					categoryModel.setMaxPercent(new BigDecimal(0));
				}
				if (resultSet.getObject("FINAL_DATE") != null) {
					categoryModel.setFinalDate(resultSet.getDate("FINAL_DATE"));
				}
				if (resultSet.getObject("SUM_CATEGORY") != null) {
					categoryModel.setSumCategory(resultSet.getBigDecimal("SUM_CATEGORY"));
				} else {
					categoryModel.setSumCategory(new BigDecimal(0));
				}
				listOfCategories.add(categoryModel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (listOfCategories.isEmpty()) {
				listOfCategories = Collections.emptyList();
			}

			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
				if (resultSet != null && !resultSet.isClosed()) {
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listOfCategories;
	}
}

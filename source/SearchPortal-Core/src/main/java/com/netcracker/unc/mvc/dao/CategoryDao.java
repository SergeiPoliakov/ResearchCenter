package com.netcracker.unc.mvc.dao;


import com.netcracker.unc.mvc.SQLQuery;
import com.netcracker.unc.mvc.connection.ConnectionFactory;
import com.netcracker.unc.mvc.models.CategoryModel;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CategoryDao extends ObjectDAO {

	public List<CategoryModel> getAllCategoriesUser(int userId) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		List<CategoryModel> listOfCategories = new ArrayList<CategoryModel>();

		try {
			connection = ConnectionFactory.getConnection();
			preparedStatement = connection.prepareStatement(SQLQuery.SP_GET_FULL_CATEGORIES);
			// preparedStatement =
			// connection.prepareStatement(SQLQuery.SP_GET_USER_CATEGORIES);
			// preparedStatement.setInt(1, userId);
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

	@Override
	public List<Object> getAllObjectsDB() {
		/*
		 * try { connect = ConnectionFactory.getConnection(); prepare =
		 * connect.prepareStatement(SQLQuery.SP_GET_FULL_CATEGORIES); result =
		 * prepare.executeQuery(); List<Object> list = new ArrayList<Object>();
		 * while (result.next()) { category = new CategoryModel(); //
		 * category.setObjectId(result.getString(1)); //
		 * category.setObjectName(result.getString(3)); //
		 * category.setCoeficient(Float.parseFloat(result.getString(4))); //
		 * category.setMinPercent(Integer.parseInt(result.getString(5))); //
		 * category.setMaxPercent(Integer.parseInt(result.getString(6))); //
		 * category.setFinalDate(result.getString(7)); //
		 * category.setSumCategory(Integer.parseInt(result.getString(8)));
		 * list.add(category); } return list; } catch (SQLException e) {
		 * e.printStackTrace(); } finally { try { if (result != null &&
		 * !result.isClosed()) result.close(); if (prepare != null &&
		 * !prepare.isClosed()) prepare.close(); if (connect != null &&
		 * !connect.isClosed()) connect.close(); } catch (SQLException e) {
		 * e.printStackTrace(); } }
		 */
		List<Object> list = Collections.emptyList();
		return list;
	}

	@Override
	public Object getObject(Object object) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public void updateObject(Object object) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public void deleteObject(Object object) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	@Override
	public void addObject(Object object) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

}

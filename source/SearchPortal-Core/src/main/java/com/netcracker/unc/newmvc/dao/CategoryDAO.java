package com.netcracker.unc.newmvc.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.netcracker.unc.newmvc.connection.ConnectionFactory;
import com.netcracker.unc.newmvc.dao.models.CategoryModel;
import com.netcracker.unc.newmvc.dao.queries.CategoryQueries;

public class CategoryDAO {

	public boolean updateCategory(int objectId, String categoryName, double coefficient, double minPercent,
			double maxPercent) {
		boolean success = false;
		Connection connection = null;
		CallableStatement callableStatement = null;
		try {
			connection = ConnectionFactory.getConnection();
			callableStatement = connection.prepareCall("{ call UPDATE_CATEGORY(?,?,?,?,?)}");

			callableStatement.setInt(1, objectId);
			callableStatement.setString(2, categoryName);
			callableStatement.setDouble(3, coefficient);
			callableStatement.setDouble(4, minPercent);
			callableStatement.setDouble(5, maxPercent);

			callableStatement.executeQuery();

			success = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
				if (callableStatement != null && !callableStatement.isClosed()) {
					callableStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return success;
	}

	public boolean deleteCategory(int objectId) {
		boolean success = false;
		Connection connection = null;
		CallableStatement callableStatement = null;
		try {
			connection = ConnectionFactory.getConnection();
			callableStatement = connection.prepareCall("{ call DELETE_CATEGORY(?)}");
			callableStatement.setInt(1, objectId);
			callableStatement.executeQuery();
			success = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
				if (callableStatement != null && !callableStatement.isClosed()) {
					callableStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return success;
	}

	public List<CategoryModel> getAllUserCategories(int userId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<CategoryModel> listOfCategories = new ArrayList<CategoryModel>();
		try {
			connection = ConnectionFactory.getConnection();
			preparedStatement = connection.prepareStatement(CategoryQueries.SP_GET_USER_CATEGORIES);
			preparedStatement.setInt(1, userId);
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

				/*
				 * if (resultSet.getObject("SUM_CATEGORY") != null) {
				 * categoryModel.setSumCategory(resultSet.getBigDecimal(
				 * "SUM_CATEGORY")); } else { categoryModel.setSumCategory(new
				 * BigDecimal(0)); }
				 */
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

	public List<CategoryModel> getAllCategoriesWithSumUser(int userId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<CategoryModel> listOfCategories = new ArrayList<CategoryModel>();
		try {
			connection = ConnectionFactory.getConnection();
			preparedStatement = connection.prepareStatement(CategoryQueries.SP_GET_FULL_CATEGORIES_WITH_SUM);
			// preparedStatement =
			// connection.prepareStatement(CategoryQueries.SP_GET_USER_CATEGORIES_WITH_SUM);
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

	public void addCategory(String categoryName, double coef, double minP, double maxP, int userid) {
		Connection connection = ConnectionFactory.getConnection();
		CallableStatement callableStatement = null;
		int id = 0;
		try {
			callableStatement = connection.prepareCall("{ ? = call ADD_CATEGORY(?, ?, ?, ?, ?)}");

			callableStatement.registerOutParameter(1, Types.INTEGER);
			callableStatement.setInt(2, userid);
			callableStatement.setString(3, categoryName);
			callableStatement.setDouble(4, coef);
			callableStatement.setDouble(5, minP);
			callableStatement.setDouble(6, maxP);
			callableStatement.execute();
			id = callableStatement.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
				if (callableStatement != null && !callableStatement.isClosed()) {
					callableStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

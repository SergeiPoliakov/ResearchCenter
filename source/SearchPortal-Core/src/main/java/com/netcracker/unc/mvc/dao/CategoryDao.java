package com.netcracker.unc.mvc.dao;

import com.netcracker.unc.mvc.*;
import com.netcracker.unc.mvc.connection.ConnectionFactory;
import com.netcracker.unc.mvc.models.CategoryModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryDao extends ObjectDAO {

	private PreparedStatement prepare = null;
	private CategoryModel category = null;
	private ResultSet result = null;
	private Connection connect = null;

	public List<CategoryModel> getAllCategoriesUser(int userId) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		List<CategoryModel> listOfCategories = new ArrayList<CategoryModel>();

		try {
			connection = ConnectionFactory.getConnection();
			preparedStatement = connection.prepareStatement(SQLQuery.SP_GET_USER_CATEGORIES);
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				
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
		try {
			connect = ConnectionFactory.getConnection();
			prepare = connect.prepareStatement(SQLQuery.SP_GET_FULL_CATEGORIES);
			result = prepare.executeQuery();
			List<Object> list = new ArrayList<Object>();
			while (result.next()) {
				category = new CategoryModel();
				// category.setObjectId(result.getString(1));
				// category.setObjectName(result.getString(3));
				// category.setCoeficient(Float.parseFloat(result.getString(4)));
				// category.setMinPercent(Integer.parseInt(result.getString(5)));
				// category.setMaxPercent(Integer.parseInt(result.getString(6)));
				// category.setFinalDate(result.getString(7));
				// category.setSumCategory(Integer.parseInt(result.getString(8)));
				list.add(category);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

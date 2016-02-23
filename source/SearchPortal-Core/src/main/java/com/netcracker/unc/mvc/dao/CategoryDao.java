/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.unc.mvc.dao;

import com.netcracker.unc.mvc.*;
import com.netcracker.unc.mvc.connection.ConnectionFactory;
import com.netcracker.unc.mvc.models.CategoryModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dmitry
 */
public class CategoryDao extends ObjectDAO {

	private PreparedStatement prepare = null;
	private CategoryModel category = null;
	private ResultSet result = null;
	private Connection connect = null;

	public String testdb() {
		try {
			connect = ConnectionFactory.getConnection();
			prepare = connect.prepareStatement(SQLQuery.SP_GET_FULL_CATEGORIES);
			result = prepare.executeQuery();
			result.next();
			result.next();
			String t = result.getNString(2);
			return t;
		} catch (SQLException ex) {
			Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Реализация обращения к базе за объектами <Категория>";
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
				category.setObjectId(result.getString(1));
				category.setObjectName(result.getString(3));
				// category.setCoeficient(Float.parseFloat(result.getString(4)));
				category.setMinPercent(Integer.parseInt(result.getString(5)));
				category.setMaxPercent(Integer.parseInt(result.getString(6)));
				category.setFinalDate(result.getString(7));
				// category.setSumCategory(Integer.parseInt(result.getString(8)));
				list.add(category);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
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

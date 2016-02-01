package com.unc2016.kolesnikov.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.unc2016.kolesnikov.mvc.ConnectionFactory;
import com.unc2016.kolesnikov.mvc.UsersPOJO;

public class UsersDAO implements ObjectsDAO, SiteOfPrioritiesObjects {

	private Connection connect = null;
	private PreparedStatement prepare = null;
	private UsersPOJO user = null;
	private ResultSet result = null;

	/*
	private static final String SQL_INSERT = "INSERT INTO pj_users(login, hash_sum, name, account_type, salt) VALUES(?, ?, ?, ?, ?)";
	private static final String SQL_GET = "SELECT * FROM pj_users WHERE login = ?";
	private static final String SQL_DELETE = "DELETE FROM pj_users WHERE user_id = ?";
	private static final String SQL_UPDATE = "UPDATE pj_users SET login = ?, hash_sum = ?, name = ?, account_type = ?, salt= ? WHERE user_id = ?";
	private static final String SQL_VIEW = "SELECT * FROM pj_users"; */

	public UsersDAO() {
		connect = ConnectionFactory.getConnection();
	}

	@Override
	public void addObject(SiteOfPrioritiesObjects object) {
		user = (UsersPOJO) object;

		try {
			prepare = connect.prepareStatement(SQL_Queries.PJ_USERS_SQL_INSERT);
			prepare.setString(1, user.getLogin());
			prepare.setInt(2, user.getHashSum());
			prepare.setString(3, user.getUserName());
			prepare.setString(4, user.getAccountType());
			prepare.setInt(5, user.getSalt());
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public SiteOfPrioritiesObjects getObject(SiteOfPrioritiesObjects object) {
		user = (UsersPOJO) object;

		try {
			prepare = connect.prepareStatement(SQL_Queries.PJ_USERS_SQL_GET);
			prepare.setString(1, user.getLogin());
			result = prepare.executeQuery();
			result.next();

			// create current user from database
			user.setUserId(result.getInt(1));
			user.setLogin(result.getString(2));
			user.setHashSum(result.getInt(3));
			user.setUserName(result.getString(4));
			user.setAccountType(result.getString(5));
			user.setSalt(result.getInt(6));
			return user;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateObject(SiteOfPrioritiesObjects object) {
		user = (UsersPOJO) object;

		try {
			prepare = connect.prepareStatement(SQL_Queries.PJ_USERS_SQL_UPDATE);
			prepare.setString(1, user.getLogin());
			prepare.setInt(2, user.getHashSum());
			prepare.setString(3, user.getUserName());
			prepare.setString(4, user.getAccountType());
			prepare.setInt(5, user.getSalt());
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteObject(SiteOfPrioritiesObjects object) {
		user = (UsersPOJO) object;

		try {
			prepare = connect.prepareStatement(SQL_Queries.PJ_USERS_SQL_DELETE);
			prepare.setInt(1, user.getUserId());
			prepare.executeUpdate();
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<SiteOfPrioritiesObjects> getAllObjectsDB() {
		try {
			prepare = connect.prepareStatement(SQL_Queries.PJ_USERS_SQL_VIEW);
			result = prepare.executeQuery();
			List<SiteOfPrioritiesObjects> list = new ArrayList<SiteOfPrioritiesObjects>();
			user = new UsersPOJO();

			while (result.next()) {
				user.setUserId(result.getInt(1));
				user.setLogin(result.getString(2));
				user.setHashSum(result.getInt(3));
				user.setUserName(result.getString(4));
				user.setAccountType(result.getString(5));
				user.setSalt(result.getInt(6));
				list.add(user);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void connectionClose() {
		try {
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

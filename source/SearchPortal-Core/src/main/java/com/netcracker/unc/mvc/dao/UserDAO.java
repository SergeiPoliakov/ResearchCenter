package com.netcracker.unc.mvc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.netcracker.unc.mvc.SQLQuery;
import com.netcracker.unc.mvc.models.UserModel;

public class UserDAO extends ObjectDAO {

	private PreparedStatement prepare = null;
	private UserModel user = null;
	private ResultSet result = null;

	@Override
	public void addObject(Object object) {
		user = (UserModel) object;

		try {
			prepare = connect.prepareStatement(SQLQuery.PJ_USERS_INSERT);
			prepare.setString(1, user.get_login());
			prepare.setInt(2, user.get_hash_sum());
			prepare.setString(3, user.get_name());
			prepare.setString(4, user.get_account_type());
			prepare.setInt(5, user.get_salt());
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// this method can choose input parameter between user_id and login (check
	// which not empty)
	@Override
	public Object getObject(Object object) {
		user = (UserModel) object;

		try {
			// if we have not user with correct id
			if (user.get_user_id() == 0) {
				prepare = connect.prepareStatement(SQLQuery.PJ_USERS_GET_BY_LOGIN);
				prepare.setString(1, user.get_login());
			}
			// if user have correct id
			else {
				prepare = connect.prepareStatement(SQLQuery.PJ_USERS_GET_BY_ID);
				prepare.setInt(1, user.get_user_id());
			}
			result = prepare.executeQuery();
			result.next();

			// create current user from database
			user.set_user_id(result.getInt(1));
			user.set_login(result.getString(2));
			user.set_hash_sum(result.getInt(3));
			user.set_name(result.getString(4));
			user.set_account_type(result.getString(5));
			user.set_salt(result.getInt(6));
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void updateObject(Object object) {
		user = (UserModel) object;

		try {
			prepare = connect.prepareStatement(SQLQuery.PJ_USERS_UPDATE_BY_ID);
			prepare.setString(1, user.get_login());
			prepare.setInt(2, user.get_hash_sum());
			prepare.setString(3, user.get_name());
			prepare.setString(4, user.get_account_type());
			prepare.setInt(5, user.get_salt());
			prepare.setInt(6, user.get_user_id());
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteObject(Object object) {
		user = (UserModel) object;
		try {
			prepare = connect.prepareStatement(SQLQuery.PJ_USERS_DELETE_BY_ID);
			prepare.setInt(1, user.get_user_id());
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Object> getAllObjectsDB() {
		try {
			prepare = connect.prepareStatement(SQLQuery.PJ_USERS_VIEW_ALL);
			result = prepare.executeQuery();
			List<Object> list = new ArrayList<Object>();

			while (result.next()) {
				user = new UserModel();
				user.set_user_id(result.getInt(1));
				user.set_login(result.getString(2));
				user.set_hash_sum(result.getInt(3));
				user.set_name(result.getString(4));
				user.set_account_type(result.getString(5));
				user.set_salt(result.getInt(6));
				list.add(user);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

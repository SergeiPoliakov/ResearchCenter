package com.netcracker.unc.mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.netcracker.unc.mvc.connection.ConnectionFactory;
import com.netcracker.unc.mvc.dao.CaseDAO;
import com.netcracker.unc.mvc.dao.UserDAO;
import com.netcracker.unc.mvc.models.AttributeModel;
import com.netcracker.unc.mvc.models.CaseModel;
import com.netcracker.unc.mvc.models.UserModel;

public class ObjectController {

	private UserModel user = new UserModel(); // for input user
	private UserModel userDB = null; // for user from database
	private UserDAO userDAO = null;
	private CaseModel casee = null;
	private CaseDAO caseDAO = null;
	private List<Object> listAll = null;

	private AttributeModel attribute = null;

	private PreparedStatement prepare = null;
	private ResultSet result = null;
	private Connection connect = null;

	// this method check input object in database and return true or false
	public boolean checkObject(Object object) {
		userDAO = new UserDAO(); // create connection
		if (object instanceof UserModel) {
			user = (UserModel) object;
			listAll = userDAO.getAllObjectsDB();
			for (Object ob : listAll) {
				userDB = (UserModel) ob;
				if (userDB.get_login().equals(user.get_login())) {
					userDAO.connectionClose();
					return true;
				}
			}
		}
		userDAO.connectionClose();
		return false;
	}

	// this method check user login and password in database and return user
	// model if could find them
	public UserModel checkUserLoginAndPassword(UserModel user) {
		userDAO = new UserDAO(); // create connection
		userDB = new UserModel();
		userDB.set_login(user.get_login());
		userDB = (UserModel) userDAO.getObject(userDB);
		if (userDB != null) {
			if (user.get_login().equals(userDB.get_login()) && user.get_hash_sum() == userDB.get_hash_sum()
					&& user.get_salt() == userDB.get_salt()) {
				userDAO.connectionClose();
				System.out.println(user.get_salt());
				System.out.println(userDB.get_salt());
				return userDB;
			}
		}
		userDAO.connectionClose();
		return null;
	}

	// this method returns all case type attributes by fin_object_type_id
	public List<Object> getCaseTypeAttributes(int object_type_id) {
		connect = ConnectionFactory.getConnection();
		try {
			prepare = connect.prepareStatement(SQLQuery.GET_ATTRIBUTES_BY_OBJECT_TYPE_ID);
			prepare.setInt(1, object_type_id);
			result = prepare.executeQuery();
			List<Object> list = new ArrayList<Object>();

			while (result.next()) {
				attribute = new AttributeModel();
				attribute.set_attribute_name(result.getString(1));
				attribute.set_attribute_id(result.getInt(2));
				attribute.set_fin_object_type_id(result.getInt(3));
				list.add(attribute);
			}
			connect.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// this method return case model of standart user category
	public CaseModel getStandartCategory(UserModel user, String category) {
		connect = ConnectionFactory.getConnection();
		casee = new CaseModel();
		try {
			prepare = connect.prepareStatement(SQLQuery.GET_STANDART_CASE_BY_USER_ID);
			prepare.setInt(1, user.get_user_id());
			prepare.setString(2, category);
			result = prepare.executeQuery();

			while (result.next()) {
				casee.set_fin_object_id(result.getInt(1));
				casee.set_object_name(result.getString(3));
				casee.set_fin_object_type_id(result.getInt(4));
				casee.set_user_id(result.getInt(5));
			}
			connect.close();
			return casee;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<CaseModel> getUserActiveCases(int userId) {
		connect = ConnectionFactory.getConnection();
		try {
			prepare = connect.prepareStatement("SELECT * FROM fin_objects WHERE user_id = ? AND parent_id IS NOT NULL");
			prepare.setInt(1, userId);
			result = prepare.executeQuery();
			List<CaseModel> list = new ArrayList<CaseModel>();
			while (result.next()) {
				casee = new CaseModel();
				casee.set_fin_object_id(result.getInt(1));
				casee.set_parent_id(result.getInt(2));
				casee.set_object_name(result.getString(3));
				casee.set_fin_object_type_id(result.getInt(4));
				casee.set_user_id(result.getInt(5));
				list.add(casee);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		caseDAO.connectionClose();
		return null;
	}

	//this method return general case of current subcase
	public CaseModel getGeneralCaseCategory(int fin_object_id) {
		connect = ConnectionFactory.getConnection();
		try {
			prepare = connect.prepareStatement(
					"SELECT * FROM  fin_objects WHERE CONNECT_BY_ISLEAF = 1  START WITH fin_object_id = ? CONNECT BY PRIOR parent_id = fin_object_id");
			prepare.setInt(1, fin_object_id);
			result = prepare.executeQuery();
			casee = new CaseModel();
			casee.set_fin_object_id(result.getInt(1));
			casee.set_parent_id(result.getInt(2));
			casee.set_object_name(result.getString(3));
			casee.set_fin_object_type_id(result.getInt(4));
			casee.set_user_id(result.getInt(5));
			connect.close();
			return casee;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

package com.unc2016.mvc;

import java.util.List;

import com.unc2016.mvc.dao.UserDAO;
import com.unc2016.mvc.models.UserModel;

public class ObjectController {

	private UserModel user = new UserModel(); // for input user
	private UserModel userDB = null; // for user from database
	private UserDAO userDAO = null;
	private List<Object> listAll = null;

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
}

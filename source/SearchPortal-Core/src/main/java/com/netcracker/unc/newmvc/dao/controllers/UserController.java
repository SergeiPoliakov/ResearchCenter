package com.netcracker.unc.newmvc.dao.controllers;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.netcracker.unc.newmvc.dao.ObjectDAO;
import com.netcracker.unc.newmvc.dao.UserDAO;
import com.netcracker.unc.newmvc.dao.models.ObjectModel;
import com.netcracker.unc.newmvc.dao.models.UserModel;

public class UserController {

	// standard objects
	private final String object1 = "Транспорт";
	private final String object2 = "ЖКХ";
	private final String object3 = "Продукты";
	private final String object4 = "Кредит";
	private final String object5 = "Другое";

	// md5 hash
	public int createSalt(String salt) {
		byte[] bytePassword;
		try {
			bytePassword = salt.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] res = md.digest(bytePassword);
			BigInteger bi = new BigInteger(1, res);
			return bi.intValue();
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public UserModel checkAndGetUser(String login, String password) {
		UserDAO userDAO = new UserDAO();
		UserModel user = userDAO.getUserWithCheckPassword(login, password.hashCode(), createSalt(password));
		return user;
	}

	public boolean createUser(String login, String password, String name) {
		UserDAO userDAO = new UserDAO();
		ObjectDAO objectDAO = new ObjectDAO();
		ObjectModel object;
		UserModel user;

		// check user
		user = userDAO.getUserByLogin(login);
		if (user.getUserId() == 0) {
			user = new UserModel();
			user.setAccountType("user");
			user.setHashSum(password.hashCode());
			user.setSalt(createSalt(password));
			user.setLogin(login);
			user.setName(name);
			userDAO.addUser(user);

			object = new ObjectModel();
			object.setUserId(userDAO.getUserByLogin(user.getLogin()).getUserId());
			object.setFinObjectTypeId(4);

			// add standard objects
			object.setObjectName(object1);
			objectDAO.addObject(object);
			object.setObjectName(object2);
			objectDAO.addObject(object);
			object.setObjectName(object3);
			objectDAO.addObject(object);
			object.setObjectName(object4);
			objectDAO.addObject(object);
			object.setObjectName(object5);
			objectDAO.addObject(object);

			return true;
		} else
			return false;
	}
}

package main.java.com.netcracker.unc.mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.com.netcracker.unc.mvc.connection.ConnectionFactory;
import main.java.com.netcracker.unc.mvc.dao.UserDAO;
import main.java.com.netcracker.unc.mvc.models.AttributeModel;
import main.java.com.netcracker.unc.mvc.models.CaseModel;
import main.java.com.netcracker.unc.mvc.models.UserModel;

public class ObjectController {

	private UserModel user = new UserModel(); // for input user
	private UserModel userDB = null; // for user from database
	private UserDAO userDAO = null;
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

	//this method returns all case type attributes by fin_object_type_id
	public List<Object> getCaseTypeAttributes(CaseModel casee) {
		connect = ConnectionFactory.getConnection();
		try {
			prepare = connect.prepareStatement(SQLQuery.GET_ATTRIBUTES_BY_OBJECT_TYPE_ID);
			prepare.setInt(1, casee.get_fin_object_type_id());
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
	
}

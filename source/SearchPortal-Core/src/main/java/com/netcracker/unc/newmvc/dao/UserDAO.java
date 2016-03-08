package com.netcracker.unc.newmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.netcracker.unc.mvc.connection.ConnectionFactory;

public class UserDAO {

	private final int typeCase = 4; // Задача
	private final String loginCol = "LOGIN";
	private final String accountTypeCol = "ACCOUNT_TYPE";
	private final String hashSumCol = "HASH_SUM";
	private final String userNameCol = "NAME";
	private final String saltCol = "SALT";
	private final String userIdCol = "USER_ID";
	private final String finObjectIdCol = "FIN_OBJECT_ID";
	private final String parentIdCol = "PARENT_ID";
	private final String objectNameCol = "OBJECT_NAME";
	private final String finObjectTypeIdCol = "FIN_OBJECT_TYPE_ID";
	private final String finObjectTypeNameCol = "FIN_OBJECT_TYPE_NAME";
	private final String attributeIdCol = "ATTRIBUTE_ID";
	private final String valueCol = "VALUE";
	private final String valueDateCol = "VALUE_DATE";

	private List<ParamModel> getAllParams(int objectId, Connection connect) throws SQLException {
		ParamModel param;
		List<ParamModel> allParams = new ArrayList<ParamModel>();
		PreparedStatement preparePar = connect.prepareStatement(ObjectQueries.SP_FIN_OBJECTS_GET_BY_ID);
		ResultSet resultPar;
		preparePar.setInt(1, objectId);
		resultPar = preparePar.executeQuery();
		if (resultPar.next()) {
			param = new ParamModel();
			param.setAttributeId(resultPar.getInt(attributeIdCol));
			param.setFinObjectId(resultPar.getInt(finObjectIdCol));
			param.setFinObjectId(objectId);
			param.setValue(resultPar.getString(valueCol));
			param.setValueDate(resultPar.getDate(valueDateCol));
			allParams.add(param);
		}
		while (resultPar.next()) {
			param = new ParamModel();
			param.setAttributeId(resultPar.getInt(attributeIdCol));
			param.setFinObjectId(resultPar.getInt(finObjectIdCol));
			param.setValue(resultPar.getString(valueCol));
			param.setValueDate(resultPar.getDate(valueDateCol));
			allParams.add(param);
		}
		return allParams;
	}

	public void addUser(UserModel user) {
		Connection connect = ConnectionFactory.getConnection();
		try {
			PreparedStatement prepare = connect.prepareStatement(UserQueries.SP_USERS_ADD_USER);
			prepare.setString(1, user.getLogin());
			prepare.setInt(2, user.getHashSum());
			if (user.getName() != null)
				prepare.setString(3, user.getName());
			else
				prepare.setNull(1, java.sql.Types.VARCHAR);
			prepare.setString(4, user.getAccountType());
			prepare.setInt(5, user.getSalt());
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public UserModel getUser(int userId) {
		Connection connect = ConnectionFactory.getConnection();
		UserModel user = new UserModel();
		try {
			PreparedStatement prepare = connect.prepareStatement(UserQueries.SP_USERS_GET_BY_ID);
			prepare.setInt(1, userId);
			ResultSet result = prepare.executeQuery();
			List<ObjectModel> allObjects = new ArrayList<ObjectModel>();
			ObjectModel object;

			if (result.next()) {
				user.setLogin(result.getString(loginCol));
				user.setAccountType(result.getString(accountTypeCol));
				user.setHashSum(result.getInt(hashSumCol));
				user.setName(result.getString(userNameCol));
				user.setSalt(result.getInt(saltCol));
				user.setUserId(result.getInt(userIdCol));
				object = new ObjectModel();
				object.setFinObjectId(result.getInt(finObjectIdCol));
				object.setParentId(result.getInt(parentIdCol));
				object.setUserId(user.getUserId());
				object.setObjectName(result.getString(objectNameCol));
				object.setFinObjectTypeId(result.getInt(finObjectTypeIdCol));
				object.setFinObjectTypeName(result.getString(finObjectTypeNameCol));

				object.setAllParams(getAllParams(object.getFinObjectId(), connect));
				allObjects.add(object);
			}
			while (result.next()) {
				object = new ObjectModel();
				object.setFinObjectId(result.getInt(finObjectIdCol));
				object.setParentId(result.getInt(parentIdCol));
				object.setUserId(user.getUserId());
				object.setObjectName(result.getString(objectNameCol));
				object.setFinObjectTypeId(result.getInt(finObjectTypeIdCol));
				object.setFinObjectTypeName(result.getString(finObjectTypeNameCol));

				object.setAllParams(getAllParams(object.getFinObjectId(), connect));
				allObjects.add(object);
			}
			user.setAllObjects(allObjects);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	public UserModel getUserByLogin(String login) {
		Connection connect = ConnectionFactory.getConnection();
		UserModel user = new UserModel();
		try {
			PreparedStatement prepare = connect.prepareStatement(UserQueries.SP_USERS_GET_BY_LOGIN);
			prepare.setString(1, login.toLowerCase());
			ResultSet result = prepare.executeQuery();
			List<ObjectModel> allObjects = new ArrayList<ObjectModel>();
			ObjectModel object;
			if (result.next()) {
				user.setLogin(result.getString(loginCol));
				user.setAccountType(result.getString(accountTypeCol));
				user.setHashSum(result.getInt(hashSumCol));
				user.setName(result.getString(userNameCol));
				user.setSalt(result.getInt(saltCol));
				user.setUserId(result.getInt(userIdCol));
				object = new ObjectModel();
				object.setFinObjectId(result.getInt(finObjectIdCol));
				object.setParentId(result.getInt(parentIdCol));
				object.setUserId(user.getUserId());
				object.setObjectName(result.getString(objectNameCol));
				object.setFinObjectTypeId(result.getInt(finObjectTypeIdCol));
				object.setFinObjectTypeName(result.getString(finObjectTypeNameCol));

				object.setAllParams(getAllParams(object.getFinObjectId(), connect));
				allObjects.add(object);
			}
			while (result.next()) {
				object = new ObjectModel();
				object.setFinObjectId(result.getInt(finObjectIdCol));
				object.setParentId(result.getInt(parentIdCol));
				object.setUserId(user.getUserId());
				object.setObjectName(result.getString(objectNameCol));
				object.setFinObjectTypeId(result.getInt(finObjectTypeIdCol));
				object.setFinObjectTypeName(result.getString(finObjectTypeNameCol));

				object.setAllParams(getAllParams(object.getFinObjectId(), connect));
				allObjects.add(object);
			}
			user.setAllObjects(allObjects);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	public UserModel getUserWithCheckPassword(String login, int hashSum, int salt) {
		Connection connect = ConnectionFactory.getConnection();
		UserModel user = new UserModel();
		try {
			PreparedStatement prepare = connect.prepareStatement(UserQueries.SP_USERS_GET_BY_LOGIN_AND_PASSWORD);
			prepare.setString(1, login);
			prepare.setInt(2, hashSum);
			prepare.setInt(3, salt);
			ResultSet result = prepare.executeQuery();
			List<ObjectModel> allObjects = new ArrayList<ObjectModel>();
			ObjectModel object;
			if (result.next()) {
				user.setLogin(result.getString(loginCol));
				user.setAccountType(result.getString(accountTypeCol));
				user.setHashSum(result.getInt(hashSumCol));
				user.setName(result.getString(userNameCol));
				user.setSalt(result.getInt(saltCol));
				user.setUserId(result.getInt(userIdCol));
				object = new ObjectModel();
				object.setFinObjectId(result.getInt(finObjectIdCol));
				object.setParentId(result.getInt(parentIdCol));
				object.setUserId(user.getUserId());
				object.setObjectName(result.getString(objectNameCol));
				object.setFinObjectTypeId(result.getInt(finObjectTypeIdCol));
				object.setFinObjectTypeName(result.getString(finObjectTypeNameCol));

				object.setAllParams(getAllParams(object.getFinObjectId(), connect));
				allObjects.add(object);
			}
			while (result.next()) {
				object = new ObjectModel();
				object.setFinObjectId(result.getInt(finObjectIdCol));
				object.setParentId(result.getInt(parentIdCol));
				object.setUserId(user.getUserId());
				object.setObjectName(result.getString(objectNameCol));
				object.setFinObjectTypeId(result.getInt(finObjectTypeIdCol));
				object.setFinObjectTypeName(result.getString(finObjectTypeNameCol));

				object.setAllParams(getAllParams(object.getFinObjectId(), connect));
				allObjects.add(object);
			}
			user.setAllObjects(allObjects);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	public List<ObjectModel> getUserActiveCases(int userId) {
		Connection connect = ConnectionFactory.getConnection();
		List<ObjectModel> list = new ArrayList<ObjectModel>();
		ObjectModel object;
		try {
			PreparedStatement prepare = connect.prepareStatement(UserQueries.SP_USER_GET_ACTIVE_CASES_BY_ID);
			prepare.setInt(1, userId);
			prepare.setInt(2, typeCase);
			ResultSet result = prepare.executeQuery();
			while (result.next()) {
				object = new ObjectModel();
				object.setFinObjectId(result.getInt(finObjectIdCol));
				object.setParentId(result.getInt(parentIdCol));
				object.setObjectName(result.getString(objectNameCol));
				object.setFinObjectTypeId(result.getInt(finObjectTypeIdCol));
				object.setUserId(result.getInt(userIdCol));
				list.add(object);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}

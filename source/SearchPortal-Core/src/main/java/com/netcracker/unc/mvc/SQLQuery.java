package com.netcracker.unc.mvc;

public class SQLQuery {

	// block constructor
	private SQLQuery() {

	}

	//// for pj_users table
	public static final String PJ_USERS_INSERT = "INSERT INTO pj_users(login, hash_sum, name, account_type, salt) VALUES(?, ?, ?, ?, ?)";
	public static final String PJ_USERS_GET_BY_LOGIN = "SELECT * FROM pj_users WHERE login like ?";
	public static final String PJ_USERS_GET_BY_ID = "SELECT * FROM pj_users WHERE user_id = ?";
	public static final String PJ_USERS_DELETE_BY_ID = "DELETE FROM pj_users WHERE user_id = ?";
	public static final String PJ_USERS_UPDATE_BY_ID = "UPDATE pj_users SET login = ?, hash_sum = ?, name = ?, account_type = ?, salt= ? WHERE user_id = ?";
	public static final String PJ_USERS_VIEW_ALL = "SELECT * FROM pj_users";
	public static final String PJ_USERS_GET_BY_LOGIN_AND_PASSWORD = "SELECT * FROM pj_users WHERE login like ? AND hash_sum = ? AND salt = ?";

	//// for fin_object_types
	public static final String FIN_OBJECT_TYPES_INSERT = "INSERT INTO fin_object_types(fin_object_type_name) VALUES(?)";
	public static final String FIN_OBJECT_TYPES_VIEW_ALL = "SELECT * FROM fin_object_types";
	public static final String FIN_OBJECT_TYPES_GET_BY_NAME = "SELECT * FROM fin_object_types WHERE LOWER(fin_object_type_name) = ?";
	public static final String FIN_OBJECT_TYPES_GET_BY_ID = "SELECT * FROM fin_object_types WHERE fin_object_type_id = ?";
	public static final String FIN_OBJECT_TYPES_UPDATE_BY_ID = "UPDATE fin_object_types SET fin_object_type_name = ? WHERE fin_object_type_id = ?";
	public static final String FIN_OBJECT_TYPES_DELETE_BY_ID = "DELETE FROM fin_object_types WHERE fin_object_type_id = ?";

	//// for fin_objects
	public static final String FIN_OBJECTS_INSERT = "INSERT INTO fin_objects(parent_id, object_name, fin_object_type_id, user_id) VALUES(?, ?, ?, ?)";
	public static final String FIN_OBJECTS_GET_BY_ID = "SELECT * FROM fin_objects WHERE fin_object_id = ?";
	public static final String FIN_OBJECTS_GET_BY_NAME = "SELECT * FROM fin_objects WHERE LOWER(object_name) = ?";
	public static final String FIN_OBJECTS_DELETE_BY_ID = "DELETE FROM fin_objects WHERE user_id = ?";
	public static final String FIN_OBJECTS_UPDATE_BY_ID = "UPDATE fin_objects SET parent_id = ?, object_name = ?, fin_object_type_id = ?, user_id = ? WHERE fin_object_id = ?";
	public static final String FIN_OBJECTS_VIEW_ALL = "SELECT * FROM fin_objects";

	//// for pj_attributes
	public static final String PJ_ATTRIBUTES_INSERT = "INSERT INTO pj_attributes(attribute_name, fin_object_type_id, attribute_id) VALUES(?, ?, ?)";
	public static final String PJ_ATTRIBUTES_GET_BY_ID = "SELECT * FROM pj_attributes WHERE attribute_id = ?";
	public static final String PJ_ATTRIBUTES_GET_BY_NAME = "SELECT * FROM pj_attributes WHERE attribute_name like ?";
	public static final String PJ_ATTRIBUTES_DELETE_BY_ID = "DELETE FROM pj_attributes WHERE attribute_id = ?";
	public static final String PJ_ATTRIBUTES_UPDATE_BY_ID = "UPDATE fin_objects SET attribute_name = ?, fin_object_type_id = ? WHERE attribute_id = ?";
	public static final String PJ_ATTRIBUTES_VIEW_ALL = "SELECT * FROM pj_attributes";

	//// for params
	public static final String PARAMS_INSERT = "INSERT INTO params(value1, value_date, fin_object_id, attribute_id) VALUES(?, ?, ?, ?)";
	public static final String PARAMS_GET_BY_OBJECT_ID = "SELECT * FROM params WHERE fin_object_id = ?";
	public static final String PARAMS_GET_BY_ATTRIBUTE_ID = "SELECT * FROM params WHERE attribute_id = ?";
	public static final String PARAMS_DELETE_BY_OBJECT_ID = "DELETE FROM params WHERE fin_object_id = ?";
	public static final String PARAMS_DELETE_BY_ATTRIBUTE_ID = "DELETE FROM params WHERE attribute_id = ?";
	public static final String PARAMS_UPDATE_BY_OBJECT_ID = "UPDATE params SET value1 = ?, value_date = ?, attribute_id = ? WHERE fin_object_id = ?";
	public static final String PARAMS_VIEW_ALL = "SELECT * FROM params";

	//// for transactions
	public static final String TRANSACTIONS_INSERT = "INSERT INTO transactions(transaction_date, fin_object_id, cost, user_id) VALUES(?, ?, ?, ?)";
	public static final String TRANSACTIONS_GET_BY_ID = "SELECT * FROM transactions WHERE transaction_id = ?";
	public static final String TRANSACTIONS_DELETE_BY_ID = "DELETE FROM transactions WHERE transaction_id = ?";
	public static final String TRANSACTIONS_UPDATE_BY_ID = "UPDATE transactions SET transaction_date = ?, fin_object_id = ?, cost = ?, user_id = ? WHERE transaction_id = ?";
	public static final String TRANSACTIONS_VIEW_ALL = "SELECT * FROM transactions";

	/**
	 * controller queries
	 */
	
	//pj_attributes
	public static final String GET_ATTRIBUTES_BY_OBJECT_TYPE_ID = "SELECT * FROM pj_attributes WHERE fin_object_type_id = ?";
	
	//fin_objects
	public static final String GET_STANDART_CASE_BY_USER_ID = "SELECT * FROM fin_objects WHERE user_id = ? AND LOWER(object_name) = ?";

}

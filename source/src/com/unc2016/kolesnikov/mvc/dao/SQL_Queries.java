package com.unc2016.kolesnikov.mvc.dao;

/**
 * contains sql queries
 * 
 * @author Kolesnikov
 *
 */
public class SQL_Queries {

	// block constructor
	private SQL_Queries() {

	}
	
	//// for pj_users table
	public static final String PJ_USERS_SQL_INSERT = "INSERT INTO pj_users(login, hash_sum, name, account_type, salt) VALUES(?, ?, ?, ?, ?)";
	public static final String PJ_USERS_SQL_GET = "SELECT * FROM pj_users WHERE login = ?";
	public static final String PJ_USERS_SQL_DELETE = "DELETE FROM pj_users WHERE user_id = ?";
	public static final String PJ_USERS_SQL_UPDATE = "UPDATE pj_users SET login = ?, hash_sum = ?, name = ?, account_type = ?, salt= ? WHERE user_id = ?";
	public static final String PJ_USERS_SQL_VIEW = "SELECT * FROM pj_users";
	
	//// for fin_object_types
	public static final String FIN_OBJECT_TYPES_SQL_INSERT = "INSERT INTO fin_object_types(fin_object_type_name) VALUES(?)";
	public static final String FIN_OBJECT_TYPES_SQL_VIEW = "SELECT * FROM fin_object_types";
	public static final String FIN_OBJECT_TYPES_SQL_GET = "SELECT * FROM fin_object_types WHERE fin_object_type_name like ?";
	
	//// for fin_objects
	public static final String FIN_OBJECTS_SQL_INSERT = "INSERT INTO fin_objects(parent_id, object_name, fin_object_type_id, user_id) VALUES(?, ?, ?, ?)";
	public static final String FIN_OBJECTS_SQL_GET = "SELECT * FROM pj_users WHERE fin_object_id = ?";
}

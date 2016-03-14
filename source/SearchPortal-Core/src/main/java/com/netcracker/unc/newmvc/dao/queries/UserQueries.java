package com.netcracker.unc.newmvc.dao.queries;

public class UserQueries {
	public static final String SP_USERS_ADD_USER = "INSERT INTO SP_users(login, hash_sum, name, account_type, salt, user_id) VALUES(?, ?, ?, ?, ?, SP_USER_ID_SEQ.nextval)";
	public static final String SP_USERS_GET_BY_ID = "SELECT * FROM SP_users a1 inner join SP_fin_objects a2 on a1.user_id = a2.user_id inner join SP_fin_object_types a3 on a2.fin_object_type_id = a3.fin_object_type_id WHERE a1.user_id = ?";
	public static final String SP_USERS_GET_BY_LOGIN_AND_PASSWORD = "SELECT * FROM SP_users a1 inner join SP_fin_objects a2 on a1.user_id = a2.user_id inner join SP_fin_object_types a3 on a2.fin_object_type_id = a3.fin_object_type_id WHERE a1.login = ? AND hash_sum = ? AND salt = ?";
	public static final String SP_USERS_GET_BY_LOGIN = "SELECT * FROM SP_users a1 left join SP_fin_objects a2 on a1.user_id = a2.user_id left join SP_fin_object_types a3 on a2.fin_object_type_id = a3.fin_object_type_id WHERE lower(a1.login) = ?";
	public static final String SP_USER_GET_ACTIVE_CASES_BY_ID = "SELECT * FROM sp_fin_objects a1 inner join sp_params a2 on a1.FIN_OBJECT_ID = a2.FIN_OBJECT_ID WHERE user_id = ? AND a2.VALUE_DATE is not null AND a2.VALUE_DATE > sysdate AND a1.FIN_OBJECT_TYPE_ID = ?";
	public static final String SP_USERS_UPDATE_BY_ID = "UPDATE SP_users SET login = ?, hash_sum = ?, name = ?, account_type = ?, salt= ? WHERE user_id = ?";
}

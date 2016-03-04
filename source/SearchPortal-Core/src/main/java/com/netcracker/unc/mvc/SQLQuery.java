package com.netcracker.unc.mvc;

public class SQLQuery {

	// block constructor
	private SQLQuery() {

	}

	//// for SP_users table
	public static final String SP_USERS_INSERT = "INSERT INTO SP_users(login, hash_sum, name, account_type, salt, user_id) VALUES(?, ?, ?, ?, ?, SP_USER_ID_SEQ.nextval)";
	public static final String SP_USERS_GET_BY_LOGIN = "SELECT * FROM SP_users WHERE login like ?";
	public static final String SP_USERS_GET_BY_ID = "SELECT * FROM SP_users WHERE user_id = ?";
	public static final String SP_USERS_DELETE_BY_ID = "DELETE FROM SP_users WHERE user_id = ?";
	public static final String SP_USERS_UPDATE_BY_ID = "UPDATE SP_users SET login = ?, hash_sum = ?, name = ?, account_type = ?, salt= ? WHERE user_id = ?";
	public static final String SP_USERS_VIEW_ALL = "SELECT * FROM SP_users";
	public static final String SP_USERS_GET_BY_LOGIN_AND_PASSWORD = "SELECT * FROM SP_users WHERE login like ? AND hash_sum = ? AND salt = ?";

	//// for SP_fin_object_types
	public static final String SP_FIN_OBJECT_TYPES_INSERT = "INSERT INTO SP_fin_object_types(fin_object_type_name, fin_object_type_id) VALUES(?, SP_FIN_OBJECT_TYPE_ID_SEQ.nextval)";
	public static final String SP_FIN_OBJECT_TYPES_VIEW_ALL = "SELECT * FROM SP_fin_object_types";
	public static final String SP_FIN_OBJECT_TYPES_GET_BY_NAME = "SELECT * FROM SP_fin_object_types WHERE LOWER(fin_object_type_name) = ?";
	public static final String SP_FIN_OBJECT_TYPES_GET_BY_ID = "SELECT * FROM SP_fin_object_types WHERE fin_object_type_id = ?";
	public static final String SP_FIN_OBJECT_TYPES_UPDATE_BY_ID = "UPDATE SP_fin_object_types SET fin_object_type_name = ? WHERE fin_object_type_id = ?";
	public static final String SP_FIN_OBJECT_TYPES_DELETE_BY_ID = "DELETE FROM SP_fin_object_types WHERE fin_object_type_id = ?";

	//// for SP_fin_objects
	public static final String SP_FIN_OBJECTS_INSERT = "INSERT INTO SP_fin_objects(parent_id, object_name, fin_object_type_id, user_id, fin_object_id) VALUES(?, ?, ?, ?, SP_FIN_OBJECT_ID_SEQ .nextval)";
	public static final String SP_FIN_OBJECTS_GET_BY_ID = "SELECT * FROM SP_fin_objects WHERE fin_object_id = ?";
	public static final String SP_FIN_OBJECTS_GET_LAST_BY_NAME = "SELECT * FROM SP_fin_objects WHERE LOWER(object_name) = ? AND ROWNUM = 1 ORDER BY fin_object_id DESC";
	public static final String SP_FIN_OBJECTS_DELETE_BY_ID = "DELETE FROM SP_fin_objects WHERE user_id = ?";
	public static final String SP_FIN_OBJECTS_UPDATE_BY_ID = "UPDATE SP_fin_objects SET parent_id = ?, object_name = ?, fin_object_type_id = ?, user_id = ? WHERE fin_object_id = ?";
	public static final String SP_FIN_OBJECTS_VIEW_ALL = "SELECT * FROM SP_fin_objects";

	//// for SP_attributes
	public static final String SP_ATTRIBUTES_INSERT = "INSERT INTO SP_attributes(attribute_name, fin_object_type_id, attribute_id) VALUES(?, ?, SP_ATTRIBUTE_ID_SEQ.nextval)";
	public static final String SP_ATTRIBUTES_GET_BY_ID = "SELECT * FROM SP_attributes WHERE attribute_id = ?";
	public static final String SP_ATTRIBUTES_GET_BY_NAME = "SELECT * FROM SP_attributes WHERE LOWER(attribute_name) like ?";
	public static final String SP_ATTRIBUTES_DELETE_BY_ID = "DELETE FROM SP_attributes WHERE attribute_id = ?";
	public static final String SP_ATTRIBUTES_UPDATE_BY_ID = "UPDATE SP_fin_objects SET attribute_name = ?, fin_object_type_id = ? WHERE attribute_id = ?";
	public static final String SP_ATTRIBUTES_VIEW_ALL = "SELECT * FROM SP_attributes";

	//// for SP_params
	public static final String SP_PARAMS_INSERT = "INSERT INTO SP_params(value, value_date, fin_object_id, attribute_id) VALUES(?, ?, ?, ?)";
	public static final String SP_PARAMS_GET_BY_OBJECT_ID = "SELECT * FROM SP_params WHERE fin_object_id = ?";
	public static final String SP_PARAMS_GET_BY_ATTRIBUTE_ID = "SELECT * FROM SP_params WHERE attribute_id = ?";
	public static final String SP_PARAMS_DELETE_BY_OBJECT_ID = "DELETE FROM SP_params WHERE fin_object_id = ?";
	public static final String SP_PARAMS_DELETE_BY_ATTRIBUTE_ID = "DELETE FROM SP_params WHERE attribute_id = ?";
	public static final String SP_PARAMS_UPDATE_BY_ATTRIBUTE_ID_AND_OBJECT_ID = "UPDATE SP_params SET value = ?, value_date = ? WHERE attribute_id = ? AND fin_object_id = ?";
	public static final String SP_PARAMS_VIEW_ALL = "SELECT * FROM SP_params";

	//// for transactions
	public static final String SP_TRANSACTIONS_INSERT = "INSERT INTO SP_transactions(transaction_date, fin_object_id, cost, user_id, transaction_id) VALUES(?, ?, ?, ?, SP_Transaction_ID_SEQ.nextval)";
	public static final String SP_TRANSACTIONS_GET_BY_ID = "SELECT * FROM SP_transactions WHERE transaction_id = ?";
	public static final String SP_TRANSACTIONS_DELETE_BY_ID = "DELETE FROM SP_transactions WHERE transaction_id = ?";
	public static final String SP_TRANSACTIONS_UPDATE_BY_ID = "UPDATE SP_transactions SET transaction_date = ?, fin_object_id = ?, cost = ?, user_id = ? WHERE transaction_id = ?";
	public static final String SP_TRANSACTIONS_VIEW_ALL = "SELECT * FROM SP_transactions";

	/**
	 * controller queries
	 */

	// SP_attributes
	public static final String SP_GET_ATTRIBUTES_BY_OBJECT_TYPE_ID = "SELECT * FROM SP_attributes WHERE fin_object_type_id = ?";

	// fin_objects
	public static final String SP_GET_STANDART_CASE_BY_USER_ID = "SELECT * FROM SP_fin_objects WHERE user_id = ? AND LOWER(object_name) = ?";

	/*
	 *
	 * Priority module
	 *
	 */

	public static final String SP_GET_FULL_CATEGORIES = "SELECT \n" + " main_fo.FIN_OBJECT_ID as object_ID, \n"
			+ " main_fo.USER_ID,\n" + " main_fo.OBJECT_NAME,\n" + " (\n" + "  SELECT select_par.VALUE\n"
			+ "   FROM SP_FIN_OBJECTS select_fo \n" + "    INNER JOIN SP_ATTRIBUTES select_pja\n"
			+ "     ON select_fo.FIN_OBJECT_TYPE_ID = select_pja.FIN_OBJECT_TYPE_ID\n"
			+ "    INNER JOIN SP_PARAMS select_par\n" + "     ON select_pja.ATTRIBUTE_ID = select_par.ATTRIBUTE_ID\n"
			+ "   WHERE select_pja.ATTRIBUTE_NAME='Коэффициент приоритета'\n"
			+ "    AND main_fo.FIN_OBJECT_ID = select_fo.FIN_OBJECT_ID\n"
			+ "    AND main_fo.FIN_OBJECT_ID = select_par.FIN_OBJECT_ID\n" + " ) as coefficient,\n" + " (\n"
			+ "  SELECT select_par.VALUE\n" + "   FROM SP_FIN_OBJECTS select_fo \n"
			+ "    INNER JOIN SP_ATTRIBUTES select_pja\n"
			+ "     ON select_fo.FIN_OBJECT_TYPE_ID = select_pja.FIN_OBJECT_TYPE_ID\n"
			+ "    INNER JOIN SP_PARAMS select_par\n" + "     ON select_pja.ATTRIBUTE_ID = select_par.ATTRIBUTE_ID\n"
			+ "   WHERE select_pja.ATTRIBUTE_NAME='Минимальный % от расхода'\n"
			+ "    AND main_fo.FIN_OBJECT_ID = select_fo.FIN_OBJECT_ID\n"
			+ "    AND main_fo.FIN_OBJECT_ID = select_par.FIN_OBJECT_ID\n" + " ) as min_percent,\n" + " (\n"
			+ "  SELECT select_par.VALUE\n" + "  FROM SP_FIN_OBJECTS select_fo \n"
			+ "   INNER JOIN SP_ATTRIBUTES select_pja\n"
			+ "    ON select_fo.FIN_OBJECT_TYPE_ID = select_pja.FIN_OBJECT_TYPE_ID\n"
			+ "   INNER JOIN SP_PARAMS select_par\n" + "    ON select_pja.ATTRIBUTE_ID = select_par.ATTRIBUTE_ID\n"
			+ "  WHERE select_pja.ATTRIBUTE_NAME='Максимальный % от расхода'\n"
			+ "   AND main_fo.FIN_OBJECT_ID = select_fo.FIN_OBJECT_ID\n"
			+ "   AND main_fo.FIN_OBJECT_ID = select_par.FIN_OBJECT_ID\n" + " ) as max_percent,\n"
			+ " (SELECT SYSDATE FROM DUAL\n"
			+ " ) as final_date, --время до конца расчетн. периода может тоже будет влиять\n" + " (\n" + "  SELECT\n"
			+ "   SUM(obj_params.VALUE) as sum_root\n" + "  FROM (\n" + "   SELECT \n"
			+ "    f_ob.USER_ID as user_id,\n" + "    CONNECT_BY_ROOT f_ob.FIN_OBJECT_ID as root_object_id,\n"
			+ "    f_ob.FIN_OBJECT_ID as object_id,\n" + "    f_ob.FIN_OBJECT_TYPE_ID as object_type\n"
			+ "   FROM SP_FIN_OBJECTS f_ob\n" + "   START WITH f_ob.FIN_OBJECT_ID = ANY(\n"
			+ "    SELECT join_fo.FIN_OBJECT_ID\n" + "    FROM SP_FIN_OBJECTS join_fo \n"
			+ "     INNER JOIN SP_FIN_OBJECT_TYPES join_fot\n"
			+ "      ON join_fo.FIN_OBJECT_TYPE_ID = join_fot.FIN_OBJECT_TYPE_ID\n"
			+ "    WHERE join_fot.FIN_OBJECT_TYPE_NAME = 'Категория'\n" + "   )\n"
			+ "  CONNECT BY PRIOR f_ob.FIN_OBJECT_ID = f_ob.PARENT_ID\n" + " ) table_objects_and_its_roots \n"
			+ "  INNER JOIN SP_PARAMS obj_params\n"
			+ "   ON table_objects_and_its_roots.object_id = obj_params.FIN_OBJECT_ID\n"
			+ "  INNER JOIN SP_ATTRIBUTES obj_atr\n"
			+ "   ON obj_atr.FIN_OBJECT_TYPE_ID = table_objects_and_its_roots.object_type  \n"
			+ " WHERE obj_atr.ATTRIBUTE_NAME = 'Сумма расхода' AND\n"
			+ "  obj_params.ATTRIBUTE_ID = obj_atr.ATTRIBUTE_ID AND \n"
			+ "  table_objects_and_its_roots.root_object_id = main_fo.FIN_OBJECT_ID\n"
			+ " GROUP BY table_objects_and_its_roots.root_object_id\n" + ") as sum_category\n"
			+ "FROM SP_FIN_OBJECTS main_fo \n" + " LEFT JOIN SP_FIN_OBJECT_TYPES main_fot\n"
			+ "  ON main_fo.FIN_OBJECT_TYPE_ID = main_fot.FIN_OBJECT_TYPE_ID\n"
			+ "WHERE main_fot.FIN_OBJECT_TYPE_NAME = 'Категория'";

	public static final String SP_GET_USER_CATEGORIES = "SELECT \n" + " main_fo.FIN_OBJECT_ID as object_ID, \n"
			+ " main_fo.OBJECT_NAME,\n" + " (\n" + "  SELECT select_par.VALUE\n" + "   FROM SP_FIN_OBJECTS select_fo \n"
			+ "    INNER JOIN SP_ATTRIBUTES select_pja\n"
			+ "     ON select_fo.FIN_OBJECT_TYPE_ID = select_pja.FIN_OBJECT_TYPE_ID\n"
			+ "    INNER JOIN SP_PARAMS select_par\n" + "     ON select_pja.ATTRIBUTE_ID = select_par.ATTRIBUTE_ID\n"
			+ "   WHERE select_pja.ATTRIBUTE_NAME='Коэффициент приоритета'\n"
			+ "    AND main_fo.FIN_OBJECT_ID = select_fo.FIN_OBJECT_ID\n"
			+ "    AND main_fo.FIN_OBJECT_ID = select_par.FIN_OBJECT_ID\n" + " ) as coefficient,\n" + " (\n"
			+ "  SELECT select_par.VALUE\n" + "   FROM SP_FIN_OBJECTS select_fo \n"
			+ "    INNER JOIN SP_ATTRIBUTES select_pja\n"
			+ "     ON select_fo.FIN_OBJECT_TYPE_ID = select_pja.FIN_OBJECT_TYPE_ID\n"
			+ "    INNER JOIN SP_PARAMS select_par\n" + "     ON select_pja.ATTRIBUTE_ID = select_par.ATTRIBUTE_ID\n"
			+ "   WHERE select_pja.ATTRIBUTE_NAME='Минимальный % от расхода'\n"
			+ "    AND main_fo.FIN_OBJECT_ID = select_fo.FIN_OBJECT_ID\n"
			+ "    AND main_fo.FIN_OBJECT_ID = select_par.FIN_OBJECT_ID\n" + " ) as min_percent,\n" + " (\n"
			+ "  SELECT select_par.VALUE\n" + "  FROM SP_FIN_OBJECTS select_fo \n"
			+ "   INNER JOIN SP_ATTRIBUTES select_pja\n"
			+ "    ON select_fo.FIN_OBJECT_TYPE_ID = select_pja.FIN_OBJECT_TYPE_ID\n"
			+ "   INNER JOIN SP_PARAMS select_par\n" + "    ON select_pja.ATTRIBUTE_ID = select_par.ATTRIBUTE_ID\n"
			+ "  WHERE select_pja.ATTRIBUTE_NAME='Максимальный % от расхода'\n"
			+ "   AND main_fo.FIN_OBJECT_ID = select_fo.FIN_OBJECT_ID\n"
			+ "   AND main_fo.FIN_OBJECT_ID = select_par.FIN_OBJECT_ID\n" + " ) as max_percent,\n"
			+ " (SELECT SYSDATE FROM DUAL\n"
			+ " ) as final_date, --время до конца расчетн. периода может тоже будет влиять\n" + " (\n" + "  SELECT\n"
			+ "   SUM(obj_params.VALUE) as sum_root\n" + "  FROM (\n" + "   SELECT \n"
			+ "    f_ob.USER_ID as user_id,\n" + "    CONNECT_BY_ROOT f_ob.FIN_OBJECT_ID as root_object_id,\n"
			+ "    f_ob.FIN_OBJECT_ID as object_id,\n" + "    f_ob.FIN_OBJECT_TYPE_ID as object_type\n"
			+ "   FROM SP_FIN_OBJECTS f_ob\n" + "   START WITH f_ob.FIN_OBJECT_ID = ANY(\n"
			+ "    SELECT join_fo.FIN_OBJECT_ID\n" + "    FROM SP_FIN_OBJECTS join_fo \n"
			+ "     INNER JOIN SP_FIN_OBJECT_TYPES join_fot\n"
			+ "      ON join_fo.FIN_OBJECT_TYPE_ID = join_fot.FIN_OBJECT_TYPE_ID\n"
			+ "    WHERE join_fot.FIN_OBJECT_TYPE_NAME = 'Категория'\n" + "   )\n"
			+ "  CONNECT BY PRIOR f_ob.FIN_OBJECT_ID = f_ob.PARENT_ID\n" + " ) table_objects_and_its_roots \n"
			+ "  INNER JOIN SP_PARAMS obj_params\n"
			+ "   ON table_objects_and_its_roots.object_id = obj_params.FIN_OBJECT_ID\n"
			+ "  INNER JOIN SP_ATTRIBUTES obj_atr\n"
			+ "   ON obj_atr.FIN_OBJECT_TYPE_ID = table_objects_and_its_roots.object_type  \n"
			+ " WHERE obj_atr.ATTRIBUTE_NAME = 'Сумма расхода' AND\n"
			+ "  obj_params.ATTRIBUTE_ID = obj_atr.ATTRIBUTE_ID AND \n"
			+ "  table_objects_and_its_roots.root_object_id = main_fo.FIN_OBJECT_ID\n"
			+ " GROUP BY table_objects_and_its_roots.root_object_id\n" + ") as sum_category\n"
			+ "FROM SP_FIN_OBJECTS main_fo \n" + " LEFT JOIN SP_FIN_OBJECT_TYPES main_fot\n"
			+ "  ON main_fo.FIN_OBJECT_TYPE_ID = main_fot.FIN_OBJECT_TYPE_ID\n"
			+ "WHERE main_fot.FIN_OBJECT_TYPE_NAME = 'Категория'";
	
	
	
	//Invoice object types
	
	 public static final String invoice_id = "5";    
	 public static final String get_invoice_for_user_by_invoice_id = "select FIN_OBJECT_ID, OBJECT_NAME from SP_FIN_OBJECTS where USER_ID = ? and FIN_OBJECT_ID = ?";
	 public static final String get_balance_by_invoice_id = "select p.value from SP_PARAMS p, SP_FIN_OBJECTS o\n"+
			 												"where p.FIN_OBJECT_ID = o.FIN_OBJECT_ID and o.USER_ID=?\n"+ 
			 												"and p.ATTRIBUTE_ID=1 and o.FIN_OBJECT_ID=? and o.FIN_OBJECT_TYPE_ID="+ invoice_id;
	 public static final String get_credit_by_invoice_id = "select p.value from SP_PARAMS p, SP_FIN_OBJECTS o\n"+
			 												"where p.FIN_OBJECT_ID = o.FIN_OBJECT_ID and o.USER_ID=?\n"+ 
			 												"and p.ATTRIBUTE_ID=2 and p.value in ('true','false') and o.FIN_OBJECT_ID=? and o.FIN_OBJECT_TYPE_ID="+ invoice_id;
	 public static final String get_percent_by_invoice_id = "select p.value from SP_PARAMS p, SP_FIN_OBJECTS o\n"+
			 												"where p.FIN_OBJECT_ID = o.FIN_OBJECT_ID and o.USER_ID=?\n"+ 
			 												"and p.ATTRIBUTE_ID=3 and o.FIN_OBJECT_ID=? and o.FIN_OBJECT_TYPE_ID="+ invoice_id;
	 public static final String get_balance_credit_and_percent_by_invoice_id = "select p.value, a.VALUE, r.VALUE\n"+
			 																	"from SP_PARAMS p, SP_PARAMS a, SP_PARAMS r, SP_FIN_OBJECTS o\n"+
			 																	"where p.FIN_OBJECT_ID = o.FIN_OBJECT_ID and  a.FIN_OBJECT_ID = o.FIN_OBJECT_ID and  r.FIN_OBJECT_ID = o.FIN_OBJECT_ID and\n"+
			 																	"o.USER_ID=? and p.ATTRIBUTE_ID=1 and a.ATTRIBUTE_ID=2 and r.ATTRIBUTE_ID=3\n"+
			 																	"and o.FIN_OBJECT_ID=? and a.value in ('true','false')and o.FIN_OBJECT_TYPE_ID= "+ invoice_id;
	 public static final String get_invoices_count_for_user = "select count(fin_object_type_ID from SP_FIN_OBJECTS where USER_ID = ? and FIN_OBJECT_TYPE_ID= "+ invoice_id;
	 public static final String get_all_invoices_for_user_by_user_id = "select fin_object_id, OBJECT_NAME from SP_FIN_OBJECTS where  USER_ID= ? and FIN_OBJECT_TYPE_ID="+invoice_id;
	 public static final String get_all_invoices_and_balances_for_user = "select o.OBJECT_NAME, p.value from SP_FIN_OBJECTS o, SP_PARAMS p where USER_ID= ? and p.ATTRIBUTE_ID=1 and o.FIN_OBJECT_TYPE_ID="+invoice_id;
	 public static final String get_sum_all_balances_for_users_by_user_id = "select SUM(p.VALUE) from SP_FIN_OBJECTS o, SP_PARAMS p where p.FIN_OBJECT_ID=o.FIN_OBJECT_ID and USER_ID=? and p.ATTRIBUTE_ID=1 and o.FIN_OBJECT_TYPE_ID="+invoice_id;
	 public static final String get_invoices_and_balance_and_credit_and_percent = "select o.OBJECT_NAME, p.VALUE, a.VALUE, r.VALUE\n"+
			 																	  "from SP_FIN_OBJECTS o, SP_PARAMS p, SP_PARAMS a, SP_PARAMS r\n"+
			 																	  "where  p.FIN_OBJECT_ID=o.FIN_OBJECT_ID and a.FIN_OBJECT_ID=o.FIN_OBJECT_ID and r.FIN_OBJECT_ID=o.FIN_OBJECT_ID and o.USER_ID= ?\n"+ 
			 																	  "and p.ATTRIBUTE_ID=1 and a.ATTRIBUTE_ID=2 and r.ATTRIBUTE_ID=3 and o.FIN_OBJECT_TYPE_ID="+invoice_id;
	 public static final String set_new_invoice_for_user = "insert into SP_FIN_OBJECTS(FIN_OBJECT_ID, OBJECT_NAME, FIN_OBJECT_TYPE_ID, user_id) values(?, ?, ?, ?); ";
	 public static final String set_new_balance_in_invoice = "insert into SP_PARAMS(ATTRIBUTE_ID, FIN_OBJECT_ID, VALUE) values (1, ?, ?)";
	 public static final String set_new_credit_in_invoice = "insert into SP_PARAMS(ATTRIBUTE_ID, FIN_OBJECT_ID, VALUE) values (2, ?, ?)";
	 public static final String set_new_percent_in_invoice = "insert into SP_PARAMS(ATTRIBUTE_ID, FIN_OBJECT_ID, VALUE) values (3, ?, ?)";

	 public static final String update_invoise_by_id = "update SP_FIN_OBJECTS set Object_name = ? where user_id = ?";
	 public static final String update_balance_in_invoice = "UPDATE SP_params SET value = ? WHERE ATTRIBUTE_ID=1 and fin_object_id =?";
	 public static final String update_credit_in_invoice = "UPDATE SP_params SET value = ? WHERE fin_object_id = ? and ATTRIBUTE_ID=2";
	 public static final String update_percent_in_invoice = "UPDATE SP_params SET value = ? WHERE fin_object_id = ? and ATTRIBUTE_ID=3";

	 public static final String delete_invoice_by_id = " delete from SP_FIN_OBJECTS where FIN_OBJECT_ID = ? and user_id= ?";
}

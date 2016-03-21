package com.netcracker.unc.newmvc.dao.queries;

public class InvoiceQueries {
	
	public static final String invoice_id = "5";
	public static final String getInvoiceForUserByInvoiceId = "select FIN_OBJECT_ID, OBJECT_NAME from SP_FIN_OBJECTS where USER_ID = ? and FIN_OBJECT_ID = ?";
	public static final String get_balance_by_invoice_id = "select p.value from SP_PARAMS p, SP_FIN_OBJECTS o\n"
			+ "where p.FIN_OBJECT_ID = o.FIN_OBJECT_ID and o.USER_ID=?\n"
			+ "and p.ATTRIBUTE_ID=1 and o.FIN_OBJECT_ID=? and o.FIN_OBJECT_TYPE_ID=" + invoice_id;
	public static final String get_credit_by_invoice_id = "select p.value from SP_PARAMS p, SP_FIN_OBJECTS o\n"
			+ "where p.FIN_OBJECT_ID = o.FIN_OBJECT_ID and o.USER_ID=?\n"
			+ "and p.ATTRIBUTE_ID=2 and p.value in ('true','false') and o.FIN_OBJECT_ID=? and o.FIN_OBJECT_TYPE_ID="
			+ invoice_id;
	public static final String get_percent_by_invoice_id = "select p.value from SP_PARAMS p, SP_FIN_OBJECTS o\n"
			+ "where p.FIN_OBJECT_ID = o.FIN_OBJECT_ID and o.USER_ID=?\n"
			+ "and p.ATTRIBUTE_ID=3 and o.FIN_OBJECT_ID=? and o.FIN_OBJECT_TYPE_ID=" + invoice_id;
	public static final String getBalanceCreditAndPercentByInvoiceId = "select p.value, a.VALUE, r.VALUE\n"
			+ "from SP_PARAMS p, SP_PARAMS a, SP_PARAMS r, SP_FIN_OBJECTS o\n"
			+ "where p.FIN_OBJECT_ID = o.FIN_OBJECT_ID and  a.FIN_OBJECT_ID = o.FIN_OBJECT_ID and  r.FIN_OBJECT_ID = o.FIN_OBJECT_ID and\n"
			+ "o.USER_ID=? and p.ATTRIBUTE_ID=1 and a.ATTRIBUTE_ID=2 and r.ATTRIBUTE_ID=3\n"
			+ "and o.FIN_OBJECT_ID=? and a.value in ('true','false')and o.FIN_OBJECT_TYPE_ID= " + invoice_id;
	public static final String getInvoicesCountForUser = "select count(fin_object_type_ID from SP_FIN_OBJECTS where USER_ID = ? and FIN_OBJECT_TYPE_ID= "
			+ invoice_id;
	public static final String getAllInvoicesForUserByUserId = "select fin_object_id, OBJECT_NAME from SP_FIN_OBJECTS where  USER_ID= ? and FIN_OBJECT_TYPE_ID="
			+ invoice_id;
	public static final String get_all_invoices_and_balances_for_user = "select o.OBJECT_NAME, p.value from SP_FIN_OBJECTS o, SP_PARAMS p where USER_ID= ? and p.ATTRIBUTE_ID=1 and o.FIN_OBJECT_TYPE_ID="
			+ invoice_id;
	public static final String getSumAllBalancesForUsersByUserId = "select SUM(p.VALUE) from SP_FIN_OBJECTS o, SP_PARAMS p where p.FIN_OBJECT_ID=o.FIN_OBJECT_ID and USER_ID=? and p.ATTRIBUTE_ID=1 and o.FIN_OBJECT_TYPE_ID="
			+ invoice_id;
	public static final String get_sum_all_consumption_for_users_by_user_id = "SELECT SUM(par.VALUE) FROM SP_FIN_OBJECTS fo INNER JOIN SP_ATTRIBUTES atr ON fo.FIN_OBJECT_TYPE_ID = atr.FIN_OBJECT_TYPE_ID INNER JOIN SP_PARAMS par ON atr.ATTRIBUTE_ID = par.ATTRIBUTE_ID WHERE FIN_OBJECT_TYPE_ID = 3 AND date_format(par.VALUE_DATE, '%Y%m') = date_format(now(), '%Y%m')"
			+ invoice_id;
	public static final String get_invoices_and_balance_and_credit_and_percent = "select o.OBJECT_NAME, p.VALUE, a.VALUE, r.VALUE\n"
			+ "from SP_FIN_OBJECTS o, SP_PARAMS p, SP_PARAMS a, SP_PARAMS r\n"
			+ "where  p.FIN_OBJECT_ID=o.FIN_OBJECT_ID and a.FIN_OBJECT_ID=o.FIN_OBJECT_ID and r.FIN_OBJECT_ID=o.FIN_OBJECT_ID and o.USER_ID= ?\n"
			+ "and p.ATTRIBUTE_ID=1 and a.ATTRIBUTE_ID=2 and r.ATTRIBUTE_ID=3 and o.FIN_OBJECT_TYPE_ID=" + invoice_id;
	public static final String setNewInvoiceForUser = "insert into SP_FIN_OBJECTS(FIN_OBJECT_ID, OBJECT_NAME, FIN_OBJECT_TYPE_ID, user_id) values(SP_FIN_OBJECT_ID_SEQ.nextval, ?, 5, ?)";
	public static final String set_new_balance_in_invoice = "insert into SP_PARAMS(ATTRIBUTE_ID, FIN_OBJECT_ID, VALUE) values (1, ?, ?)";
	public static final String set_new_credit_in_invoice = "insert into SP_PARAMS(ATTRIBUTE_ID, FIN_OBJECT_ID, VALUE) values (2, ?, ?)";
	public static final String set_new_percent_in_invoice = "insert into SP_PARAMS(ATTRIBUTE_ID, FIN_OBJECT_ID, VALUE) values (3, ?, ?)";

	public static final String updateInvoiseById = "update SP_FIN_OBJECTS set Object_name = ? where user_id = ?";
	public static final String updateBalanceInInvoice = "UPDATE SP_params SET value = ? WHERE ATTRIBUTE_ID=1 and fin_object_id =?";
	public static final String update_credit_in_invoice = "UPDATE SP_params SET value = ? WHERE fin_object_id = ? and ATTRIBUTE_ID=2";
	public static final String update_percent_in_invoice = "UPDATE SP_params SET value = ? WHERE fin_object_id = ? and ATTRIBUTE_ID=3";

	public static final String deleteInvoiceById = " delete from SP_FIN_OBJECTS where FIN_OBJECT_ID = ? and user_id= ?";

}

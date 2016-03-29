package com.netcracker.unc.newmvc.dao.queries;

public class InvoiceQueries {
	
	public static final String invoice_id = "5";
	public static final String getInvoiceName = "select OBJECT_NAME from SP_FIN_OBJECTS where USER_ID = ? and FIN_OBJECT_ID = ?";
	public static final String getBalance = "select p.value from SP_PARAMS p, SP_FIN_OBJECTS o\n"
			+ "where p.FIN_OBJECT_ID = o.FIN_OBJECT_ID and o.USER_ID=?\n"
			+ "and p.ATTRIBUTE_ID=14 and o.FIN_OBJECT_ID=? and o.FIN_OBJECT_TYPE_ID=" + invoice_id;
	public static final String getCredit = "select p.value from SP_PARAMS p, SP_FIN_OBJECTS o\n"
			+ "where p.FIN_OBJECT_ID = o.FIN_OBJECT_ID and o.USER_ID=?\n"
			+ "and p.ATTRIBUTE_ID=15 and p.value in ('true','false') and o.FIN_OBJECT_ID=? and o.FIN_OBJECT_TYPE_ID="
			+ invoice_id;
	public static final String getPercent = "select p.value from SP_PARAMS p, SP_FIN_OBJECTS o\n"
			+ "where p.FIN_OBJECT_ID = o.FIN_OBJECT_ID and o.USER_ID=?\n"
			+ "and p.ATTRIBUTE_ID=16 and o.FIN_OBJECT_ID=? and o.FIN_OBJECT_TYPE_ID=" + invoice_id;
	public static final String getBalanceCreditAndPercent = "select p.value, a.VALUE, r.VALUE\n"
			+ "from SP_PARAMS p, SP_PARAMS a, SP_PARAMS r, SP_FIN_OBJECTS o\n"
			+ "where p.FIN_OBJECT_ID = o.FIN_OBJECT_ID and  a.FIN_OBJECT_ID = o.FIN_OBJECT_ID and  r.FIN_OBJECT_ID = o.FIN_OBJECT_ID and\n"
			+ "o.USER_ID=? and p.ATTRIBUTE_ID=14 and a.ATTRIBUTE_ID=15 and r.ATTRIBUTE_ID=16\n"
			+ "and o.FIN_OBJECT_ID=? and a.value in ('true','false') and o.FIN_OBJECT_TYPE_ID= " + invoice_id;
	public static final String getInvoicesCount = "select count(fin_object_type_ID) from SP_FIN_OBJECTS where USER_ID = ? and FIN_OBJECT_TYPE_ID= "+ invoice_id;
	public static final String getAllInvoicesByUserId = "select o.FIN_OBJECT_ID, o.OBJECT_NAME, p.value, a.VALUE, r.VALUE\n"
                                                              +"from SP_PARAMS p, SP_PARAMS a, SP_PARAMS r, SP_FIN_OBJECTS o\n"
                                                              +"where p.FIN_OBJECT_ID = o.FIN_OBJECT_ID and  a.FIN_OBJECT_ID = o.FIN_OBJECT_ID and  r.FIN_OBJECT_ID = o.FIN_OBJECT_ID and \n"
                                                              +"o.USER_ID=? and p.ATTRIBUTE_ID=14 and a.ATTRIBUTE_ID=15 and r.ATTRIBUTE_ID=16\n"
                                                              +"and a.value in ('true','false')and o.FIN_OBJECT_TYPE_ID="+ invoice_id;
	public static final String getAllInvoicesAndBalances = "select o.OBJECT_NAME, p.value from SP_FIN_OBJECTS o, SP_PARAMS p where USER_ID= ? and p.ATTRIBUTE_ID=14 and o.FIN_OBJECT_TYPE_ID="
			+ invoice_id;
	public static final String getSumAllBalancesByUserId = "select SUM(p.VALUE) from SP_FIN_OBJECTS o, SP_PARAMS p where p.FIN_OBJECT_ID=o.FIN_OBJECT_ID and USER_ID=? and p.ATTRIBUTE_ID=14 and o.FIN_OBJECT_TYPE_ID="
			+ invoice_id;
	public static final String get_sum_all_consumption_for_users_by_user_id = "SELECT SUM(par.VALUE) FROM SP_FIN_OBJECTS fo INNER JOIN SP_ATTRIBUTES atr ON fo.FIN_OBJECT_TYPE_ID = atr.FIN_OBJECT_TYPE_ID INNER JOIN SP_PARAMS par ON atr.ATTRIBUTE_ID = par.ATTRIBUTE_ID WHERE FIN_OBJECT_TYPE_ID = 3 AND date_format(par.VALUE_DATE, '%Y%m') = date_format(now(), '%Y%m')"
			+ invoice_id;
	public static final String setInvoice = "insert into SP_FIN_OBJECTS(FIN_OBJECT_ID, OBJECT_NAME, FIN_OBJECT_TYPE_ID, user_id) values(SP_FIN_OBJECT_ID_SEQ.nextval, ?, 5, ?)";
	public static final String setBalance = "insert into SP_PARAMS(ATTRIBUTE_ID, FIN_OBJECT_ID, VALUE) values (14,?, ?)";
	public static final String setCredit = "insert into SP_PARAMS(ATTRIBUTE_ID, FIN_OBJECT_ID, VALUE) values (15, ?, ?)";
	public static final String setPercent = "insert into SP_PARAMS(ATTRIBUTE_ID, FIN_OBJECT_ID, VALUE) values (16, ?, ?)";

	public static final String updateInvoise = "update SP_FIN_OBJECTS set Object_name = ? where user_id = ?";
	public static final String updateBalance = "UPDATE SP_params SET value = ? WHERE ATTRIBUTE_ID=14 and fin_object_id =?";
	public static final String updateCredit = "UPDATE SP_params SET value = ? WHERE fin_object_id = ? and ATTRIBUTE_ID=15";
	public static final String updatePercent = "UPDATE SP_params SET value = ? WHERE fin_object_id = ? and ATTRIBUTE_ID=16";

	public static final String deleteInvoiceName = " delete from SP_FIN_OBJECTS where FIN_OBJECT_ID = ?";
	public static final String deleteParamInvoice = "delete from SP_PARAMS WHERE FIN_OBJECT_ID = ?";
}

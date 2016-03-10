package com.netcracker.unc.mvc.dao;

public class CreditQueries {
	public static final String creditID = "6";
	public static final String getAllCreditsByUserID = "select fin_object_id, object_name from SP_FIN_OBJECTS"
						+ "where USER_ID = ? and FIN_OBJECT_TYPE = " + creditID;
	public static final String getCreditByID = "select FIN_OBJECT_ID, OBJECT_NAME, FIN_OBJECT_TYPE_ID from SP_FIN_OBJECTS where USER_ID = ? and FIN_OBJECT_ID = ?";
	
	public static final String setNewCreditForUser = "insert into SP_FIN_OBJECTS(FIN_OBJECT_ID, OBJECT_NAME, FIN_OBJECT_TYPE_ID, USER_ID) values(?, ?, " + creditID + " ?)";
	
	public static final String getCreditValue = "select p.value from sp_params p, sp_fin_objects o where o.FIN_OBJECT_ID = p.FIN_OBJECT_ID and ATTRIBUTE_ID = 50 and o.USER_ID = ? and o.FIN_OBJECT_ID = ?";
	public static final String getCreditBalance = "select p.value from sp_params p, sp_fin_objects o where o.FIN_OBJECT_ID = p.FIN_OBJECT_ID and ATTRIBUTE_ID = 51 and o.USER_ID = ? and o.FIN_OBJECT_ID = ?";
	public static final String getCreditReceivingDate = "select p.value from sp_params p, sp_fin_objects o where o.FIN_OBJECT_ID = p.FIN_OBJECT_ID and ATTRIBUTE_ID = 52 and o.USER_ID = ? and o.FIN_OBJECT_ID = ?";
	public static final String getCreditPercent = "select p.value from sp_params p, sp_fin_objects o where o.FIN_OBJECT_ID = p.FIN_OBJECT_ID and ATTRIBUTE_ID = 53 and o.USER_ID = ? and o.FIN_OBJECT_ID = ?";
	public static final String getCreditPayPeriod = "select p.value from sp_params p, sp_fin_objects o where o.FIN_OBJECT_ID = p.FIN_OBJECT_ID and ATTRIBUTE_ID = 54 and o.USER_ID = ? and o.FIN_OBJECT_ID = ?";
	public static final String getCreditMonthPay = "select p.value from sp_params p, sp_fin_objects o where o.FIN_OBJECT_ID = p.FIN_OBJECT_ID and ATTRIBUTE_ID = 53 and o.USER_ID = ? and o.FIN_OBJECT_ID = ?";
	
	public static final String setCreditValue = "insert into SP_PARAMS(ATTRIBUTE_ID, FIN_OBJECT_ID, VALUE) values(50, ?, ?)";
	public static final String setCreditBalance = "insert into SP_PARAMS(ATTRIBUTE_ID, FIN_OBJECT_ID, VALUE) values(51, ?, ?)";
	public static final String setCreditReceivingDate = "insert into SP_PARAMS(ATTRIBUTE_ID, FIN_OBJECT_ID, VALUE) values(52, ?, ?)";
	public static final String setCreditPercent = "insert into SP_PARAMS(ATTRIBUTE_ID, FIN_OBJECT_ID, VALUE) values(53, ?, ?)";
	public static final String setCreditPayPeriod = "insert into SP_PARAMS(ATTRIBUTE_ID, FIN_OBJECT_ID, VALUE) values(54, ?, ?)";
	public static final String setCreditMonthPay = "insert into SP_PARAMS(ATTRIBUTE_ID, FIN_OBJECT_ID, VALUE) values(55, ?, ?)";
	
	
}

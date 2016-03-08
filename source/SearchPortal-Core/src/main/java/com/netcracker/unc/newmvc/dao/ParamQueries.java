package com.netcracker.unc.newmvc.dao;

public class ParamQueries {

	public static final String SP_PARAMS_ADD = "INSERT INTO SP_params(value, value_date, fin_object_id, attribute_id) VALUES(?, ?, ?, ?)";
	public static final String SP_PARAMS_UPDATE_BY_ATTRIBUTE_ID_AND_OBJECT_ID = "UPDATE SP_params SET value = ?, value_date = ? WHERE attribute_id = ? AND fin_object_id = ?";
}

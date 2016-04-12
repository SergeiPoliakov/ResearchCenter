package com.netcracker.unc.newmvc.ejb.queries;

public class CreditQueries {
	//волшебный идентификатор кредита
	public static final String finObjectTypeId = "6";
	//чтение
	public static final String getAllCreditsByUser = "select obj.fin_object_id as \"obj_id\", obj.object_name as \"obj_name\", obj.parent_id as \"parent\",\n"+
			"val.value as \"val\",  proc.Value as \"proc\", reciv.value_date as \"rec\", period.value as \"period\"\n"+
			"from SP_FIN_OBJECTS obj, SP_PARAMS val, SP_PARAMS proc, SP_PARAMS reciv,\n"+
			"SP_PARAMS period\n"+
			"where obj.user_id = ? and val.fin_object_id = obj.fin_object_id and\n"+
			"proc.fin_object_id = obj.fin_object_id and reciv.fin_object_id = obj.fin_object_id\n"+
			"and period.fin_object_id = obj.fin_object_id and val.attribute_id = 17 and\n"+
			"proc.attribute_id = 18 and reciv.attribute_id = 19 and period.attribute_id = 20 and\n"+
			"obj.fin_object_type_id = 6\n";
	}

package com.netcracker.unc.newmvc.dao.queries;

public class IncomeQueries {
	
	public static final String income_id = "2";
	public static final String getAllIncomeByUserId = "select distinct o.FIN_OBJECT_ID, o.OBJECT_NAME, d.VALUE_DATE, s.VALUE, m.VALUE, o.PARENT_ID, i.OBJECT_NAME\n"+
			"from SP_FIN_OBJECTS o, SP_PARAMS d, SP_PARAMS s, SP_PARAMS m, SP_FIN_OBJECTS i\n"+
			"where o.USER_ID=? \n"+
			"and o.FIN_OBJECT_ID=d.FIN_OBJECT_ID and o.FIN_OBJECT_ID=s.FIN_OBJECT_ID and o.FIN_OBJECT_ID=m.FIN_OBJECT_ID and o.PARENT_ID=i.FIN_OBJECT_ID\n"+
			"and s.ATTRIBUTE_ID=5 and m.ATTRIBUTE_ID=6 and d.ATTRIBUTE_ID=4\n"+
			"and o.FIN_OBJECT_TYPE_ID=" + income_id;
	public static final String getIncome = "o.OBJECT_NAME, d.VALUE_DATE, s.VALUE, m.VALUE, o.PARENT_ID, i.OBJECT_NAME\n"+
			"from SP_FIN_OBJECTS o, SP_PARAMS d, SP_PARAMS s, SP_PARAMS m, SP_FIN_OBJECTS i\n"+
			"where o.USER_ID=? and o.FIN_OBJECT_ID=? \n"+
			"and o.FIN_OBJECT_ID=d.FIN_OBJECT_ID and o.FIN_OBJECT_ID=s.FIN_OBJECT_ID and o.FIN_OBJECT_ID=m.FIN_OBJECT_ID and o.PARENT_ID=i.FIN_OBJECT_ID\n"+
			"and s.ATTRIBUTE_ID=5 and m.ATTRIBUTE_ID=6 and d.ATTRIBUTE_ID=4\n"+
			"and o.FIN_OBJECT_TYPE_ID=" + income_id;
	public static final String getParams = "select d.VALUE_DATE, s.VALUE, m.VALUE \n"+
			"from SP_FIN_OBJECTS o, SP_PARAMS d, SP_PARAMS m, SP_PARAMS s \n"+
			"where o.FIN_OBJECT_ID=m.FIN_OBJECT_ID and o.FIN_OBJECT_ID=s.FIN_OBJECT_ID and o.FIN_OBJECT_ID=d.FIN_OBJECT_ID \n"+
			"and d.ATTRIBUTE_ID=4 and s.ATTRIBUTE_ID=5 and m.ATTRIBUTE_ID=6 and o.FIN_OBJECT_TYPE_ID=2 and o.USER_ID= ? and o.FIN_OBJECT_ID=?";
	
	public static final String setIncome = "insert into SP_FIN_OBJECTS(FIN_OBJECT_ID, FIN_OBJECT_TYPE_ID,OBJECT_NAME,PARENT_ID,USER_ID)"+
											"VALUES (sp_fin_object_id_seq.nextval,2,?,?,?)";
	public static final String setName = "INSERT INTO SP_FIN_OBJECTS (FIN_OBJECT_ID, FIN_OBJECT_TYPE_ID, OBJECT_NAME, PARENT_ID,USER_ID)\n"+
           "VALUES(SP_FIN_OBJECT_ID_SEQ.nextval, 2, ?,?,?);\n";
	public static final String setIsEveyMonth = "INSERT INTO SP_PARAMS(ATTRIBUTE_ID,FIN_OBJECT_ID, VALUE) VALUES (6,?,?)";
	public static final String setSum = "INSERT INTO SP_PARAMS(ATTRIBUTE_ID,FIN_OBJECT_ID, VALUE) VALUES (5,?,?)";
	public static final String setDate = "INSERT INTO SP_PARAMS(ATTRIBUTE_ID,FIN_OBJECT_ID, VALUE_DATE VALUES (4,?,sysdate)";
	
	public static final String updateName = "UPDATE sp_fin_objects set OBJECT_NAME = ? where fin_object_id = ?";
	public static final String updateDate = "UPDATE SP_PARAMS set VALUE_DATE= ? where FIN_OBJECT_ID=? and ATTRIBUTE_ID=4";
	public static final String updateSum = "UPDATE SP_PARAMS set VALUE= ? where FIN_OBJECT_ID=? and ATTRIBUTE_ID=5";
	public static final String updateIsEveryMonth = "UPDATE SP_PARAMS set VALUE= ? where FIN_OBJECT_ID=? and ATTRIBUTE_ID=6";
	
	public static final String deleteIncome = " delete from SP_FIN_OBJECTS where FIN_OBJECT_ID = ?";
	public static final String deleteParams = "delete from SP_PARAMS WHERE FIN_OBJECT_ID = ?";
	
}

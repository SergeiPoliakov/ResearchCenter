package com.netcracker.unc.newmvc.ejb.queries;

public class CreditQueries {
	//волшебный идентификатор кредита
	public static final String finObjectTypeId = "6";
	//добавление
	public static final String addCredit = "insert into SP_FIN_OBJECTS(FIN_OBJECT_ID, OBJECT_NAME, FIN_OBJECT_TYPE_ID, user_id) values(SP_FIN_OBJECT_ID_SEQ.nextval, ?, 6, ?)";
	public static final String addValue = "insert into SP_PARAMS(ATTRIBUTE_ID, FIN_OBJECT_ID, VALUE) values (17,?, ?)";
	public static final String addBalance = "insert into SP_PARAMS(ATTRIBUTE_ID, FIN_OBJECT_ID, VALUE) values (18,?, ?)";
	public static final String addPercent = "insert into SP_PARAMS(ATTRIBUTE_ID, FIN_OBJECT_ID, VALUE) values (19,?, ?)";
	public static final String addDate = "insert into SP_PARAMS(ATTRIBUTE_ID, FIN_OBJECT_ID, VALUE) values (20,?, ?)";
	public static final String addPeriod = "insert into SP_PARAMS(ATTRIBUTE_ID, FIN_OBJECT_ID, VALUE) values (21,?, ?)";
	//чтение
	public static final String getAllCreditsByUser = "select obj.FIN_OBJECT_ID, obj.OBJECT_NAME, value.VALUE as value, balance.VALUE as balance, percent.VALUE as percent, date.VALUE as date, period.VALUE as period\n"
                                                              +"from SP_PARAMS value, SP_PARAMS balance, SP_PARAMS percent, SP_PARAMS date, SP_PARAMS period, SP_FIN_OBJECTS obj\n"
                                                              +"where value.FIN_OBJECT_ID = obj.FIN_OBJECT_ID and balance.FIN_OBJECT_ID = obj.FIN_OBJECT_ID and percent.FIN_OBJECT_ID = obj.FIN_OBJECT_ID and date.FIN_OBJECT_ID = obj.FIN_OBJECT_ID and period.FIN_OBJECT_ID = obj.FIN_OBJECT_ID\n"
                                                              +"obj.USER_ID=? and value.ATTRIBUTE_ID=17 and balance.ATTRIBUTE_ID=18 and percent.ATTRIBUTE_ID=19 and date.ATTRIBUTE_ID=20 and period.ATTRIBUTE_ID=21 and\n"
                                                              +" o.FIN_OBJECT_TYPE_ID="+ finObjectTypeId;
    public static final String countCredits = "select count(fin_object_type_ID) from SP_FIN_OBJECTS where USER_ID = ? and FIN_OBJECT_TYPE_ID= "+ finObjectTypeId;
    public static final String getCreditById = "select obj.FIN_OBJECT_ID, obj.OBJECT_NAME, value.VALUE as value, balance.VALUE as balance, percent.VALUE as percent, date.VALUE as date, period.VALUE as period\n"
                                                              +"from SP_PARAMS value, SP_PARAMS balance, SP_PARAMS percent, SP_PARAMS date, SP_PARAMS period, SP_FIN_OBJECTS obj\n"
                                                              +"where value.FIN_OBJECT_ID = obj.FIN_OBJECT_ID and balance.FIN_OBJECT_ID = obj.FIN_OBJECT_ID and percent.FIN_OBJECT_ID = obj.FIN_OBJECT_ID and date.FIN_OBJECT_ID = obj.FIN_OBJECT_ID and period.FIN_OBJECT_ID = obj.FIN_OBJECT_ID\n"
                                                              +"obj.USER_ID=? and value.ATTRIBUTE_ID=17 and balance.ATTRIBUTE_ID=18 and percent.ATTRIBUTE_ID=19 and date.ATTRIBUTE_ID=20 and period.ATTRIBUTE_ID=21 and\n"
                                                              +"obj.FIN_OBJECT_ID= ?  and o.FIN_OBJECT_TYPE_ID="+ finObjectTypeId;
    //удаление
    public static final String deleteCredit = "delete from SP_FIN_OBJECTS where FIN_OBJECT_ID = ?";
    public static final String deleteCreditParams = "delete from SP_PARAMS WHERE FIN_OBJECT_ID = ?";
    //обновление
    public static final String updateCredit = "UPDATE SP_FIN_OBJECTS SET Object_name = ? WHERE user_id = ? and FIN_OBJECT_ID = ?";
	public static final String updateValue = "UPDATE SP_params SET value = ? WHERE fin_object_id = ? and ATTRIBUTE_ID=17";
	public static final String updateBalance = "UPDATE SP_params SET value = ? WHERE fin_object_id = ? and ATTRIBUTE_ID=18";
	public static final String updatePercent = "UPDATE SP_params SET value = ? WHERE fin_object_id = ? and ATTRIBUTE_ID=19";
	public static final String updateDate = "UPDATE SP_params SET value = ? WHERE fin_object_id = ? and ATTRIBUTE_ID=20";
	public static final String updatePeriod = "UPDATE SP_params SET value = ? WHERE fin_object_id = ? and ATTRIBUTE_ID=21";
}

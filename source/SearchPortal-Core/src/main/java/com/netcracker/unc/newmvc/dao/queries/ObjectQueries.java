package com.netcracker.unc.newmvc.dao.queries;

public class ObjectQueries {

	public static final String SP_FIN_OBJECTS_ADD_OBJECT = "INSERT INTO SP_fin_objects(parent_id, object_name, fin_object_type_id, user_id, fin_object_id) VALUES(?, ?, ?, ?, SP_FIN_OBJECT_ID_SEQ .nextval)";
	public static final String SP_FIN_OBJECTS_GET_BY_ID = "SELECT * FROM SP_fin_objects a1 right join sp_params a2 on a1.fin_object_id = a2.fin_object_id WHERE a1.fin_object_id = ?";
	public static final String SP_FIN_OBJECTS_UPDATE_BY_ID = "UPDATE SP_fin_objects SET parent_id = ?, object_name = ?, fin_object_type_id = ?, user_id = ? WHERE fin_object_id = ?";

}

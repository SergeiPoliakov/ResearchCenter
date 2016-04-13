package com.netcracker.unc.newmvc.ejb.queries;

public class CategoryQueries {

	public static final String USER_CATEGORIES = " SELECT "
			+ " FIN_OBJECT_ID as OBJECT_ID, OBJECT_NAME, ( SELECT coef_par.VALUE"
			+ " FROM SP_PARAMS coef_par WHERE coef_par.ATTRIBUTE_ID = 1"
			+ "  AND coef_par.FIN_OBJECT_ID = main_fo.FIN_OBJECT_ID ) as coefficient, ("
			+ " SELECT coef_par.VALUE FROM SP_PARAMS coef_par WHERE coef_par.ATTRIBUTE_ID = 2 "
			+ "  AND coef_par.FIN_OBJECT_ID = main_fo.FIN_OBJECT_ID ) as min_percent, ("
			+ " SELECT coef_par.VALUE FROM SP_PARAMS coef_par WHERE coef_par.ATTRIBUTE_ID = 3 "
			+ "  AND coef_par.FIN_OBJECT_ID = main_fo.FIN_OBJECT_ID ) as max_percent "
			+ "FROM SP_FIN_OBJECTS main_fo WHERE FIN_OBJECT_TYPE_ID =1 AND USER_ID = ? ";

	public static final String USER_CATEGORIES_WITH_SUM ="SELECT FIN_OBJECT_ID as OBJECT_ID, OBJECT_NAME, NVL( (  SELECT   SUM(obj_params.VALUE) as sum_root  FROM (   SELECT     CONNECT_BY_ROOT f_ob.FIN_OBJECT_ID as root_object_id,    f_ob.FIN_OBJECT_ID as object_id,   f_ob.FIN_OBJECT_TYPE_ID as object_type  FROM SP_FIN_OBJECTS f_ob  START WITH f_ob.FIN_OBJECT_ID = ANY(   SELECT join_fo.FIN_OBJECT_ID  FROM SP_FIN_OBJECTS join_fo   WHERE join_fo.FIN_OBJECT_TYPE_ID = 1  ) CONNECT BY PRIOR f_ob.FIN_OBJECT_ID = f_ob.PARENT_ID ) table_objects_and_its_roots   INNER JOIN SP_PARAMS obj_params   ON table_objects_and_its_roots.object_id = obj_params.FIN_OBJECT_ID WHERE obj_params.ATTRIBUTE_ID = 8 AND table_objects_and_its_roots.root_object_id = main_fo.FIN_OBJECT_ID GROUP BY table_objects_and_its_roots.root_object_id ), 0) as sum_category FROM SP_FIN_OBJECTS main_fo WHERE FIN_OBJECT_TYPE_ID = 1 AND main_fo.USER_ID = ?"; //"SELECT FIN_OBJECT_ID as OBJECT_ID, OBJECT_NAME, NVL(( SELECT coef_par.VALUE FROM SP_PARAMS coef_par WHERE coef_par.ATTRIBUTE_ID = 1  AND coef_par.FIN_OBJECT_ID = main_fo.FIN_OBJECT_ID ),  TO_CHAR(0)) as coefficient, NVL((SELECT coef_par.VALUE FROM SP_PARAMS coef_par WHERE coef_par.ATTRIBUTE_ID = 2 AND coef_par.FIN_OBJECT_ID = main_fo.FIN_OBJECT_ID ),  TO_CHAR(0,'999.99')) as min_percent, NVL((SELECT coef_par.VALUE FROM SP_PARAMS coef_par WHERE coef_par.ATTRIBUTE_ID = 3  AND coef_par.FIN_OBJECT_ID = main_fo.FIN_OBJECT_ID ),  TO_CHAR(0)) as max_percent, NVL(( SELECT SUM(obj_params.VALUE) as sum_root FROM ( SELECT CONNECT_BY_ROOT f_ob.FIN_OBJECT_ID as root_object_id, f_ob.FIN_OBJECT_ID as object_id, f_ob.FIN_OBJECT_TYPE_ID as object_type FROM SP_FIN_OBJECTS f_ob START WITH f_ob.FIN_OBJECT_ID = ANY( SELECT join_fo.FIN_OBJECT_ID FROM SP_FIN_OBJECTS join_fo  WHERE join_fo.FIN_OBJECT_TYPE_ID = 1 ) CONNECT BY PRIOR f_ob.FIN_OBJECT_ID = f_ob.PARENT_ID ) table_objects_and_its_roots INNER JOIN SP_PARAMS obj_params ON table_objects_and_its_roots.object_id = obj_params.FIN_OBJECT_ID WHERE obj_params.ATTRIBUTE_ID = 8 AND table_objects_and_its_roots.root_object_id = main_fo.FIN_OBJECT_ID GROUP BY table_objects_and_its_roots.root_object_id),  TO_CHAR(0,'999.99')) as sum_category FROM SP_FIN_OBJECTS main_fo WHERE FIN_OBJECT_TYPE_ID = 1 AND main_fo.USER_ID = ?";

}

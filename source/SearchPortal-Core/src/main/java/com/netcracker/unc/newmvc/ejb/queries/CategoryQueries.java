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

	public static final String USER_CATEGORIES_WITH_SUM = " SELECT "
			+ " FIN_OBJECT_ID as OBJECT_ID, OBJECT_NAME, ( SELECT coef_par.VALUE"
			+ " FROM SP_PARAMS coef_par WHERE coef_par.ATTRIBUTE_ID = 1"
			+ "  AND coef_par.FIN_OBJECT_ID = main_fo.FIN_OBJECT_ID ) as coefficient, ("
			+ " SELECT coef_par.VALUE FROM SP_PARAMS coef_par WHERE coef_par.ATTRIBUTE_ID = 2 "
			+ "  AND coef_par.FIN_OBJECT_ID = main_fo.FIN_OBJECT_ID ) as min_percent, ("
			+ " SELECT coef_par.VALUE FROM SP_PARAMS coef_par WHERE coef_par.ATTRIBUTE_ID = 3 "
			+ "  AND coef_par.FIN_OBJECT_ID = main_fo.FIN_OBJECT_ID ) as max_percent, ( SELECT coef_par.VALUE"
			+ " FROM SP_PARAMS coef_par WHERE coef_par.ATTRIBUTE_ID = 1"
			+ "  AND coef_par.FIN_OBJECT_ID = main_fo.FIN_OBJECT_ID ) as co "
			+ "FROM SP_FIN_OBJECTS main_fo WHERE FIN_OBJECT_TYPE_ID =1 AND USER_ID = ? ";

}

package com.netcracker.unc.newmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.netcracker.unc.newmvc.connection.ConnectionFactory;
import com.netcracker.unc.newmvc.dao.controllers.ActiveCasesController;
import com.netcracker.unc.newmvc.dao.models.ActiveCasesModel;

public class ActiveCasesDAO {

	//// database name object types
	private String category1 = "Транспорт";
	private String category2 = "ЖКХ";
	private String category3 = "Кредит";
	private String category4 = "Продукты";
	private String category5 = "Другое";

	private final int type = 4; // Задача
	private final int atr1 = 12; // Стоимость
	private final int atr2 = 11; // Дата завершения
	private final int atr3 = 10; // Дата создания
	private final int atr4 = 13; // Приоритет
	/////////////

	public List<ActiveCasesModel> getActiveCases(int userId) {

		List<ActiveCasesModel> list = null;

		// column names: hierarchy, level, casename, casecost, startdate,
		// enddate,
		// parentname, objectid, priority
		String query = "select * from (select lpad(object_name, length(object_name)+(level-2)*5, ' ') "
				+ "as hierarchy, level, sp_fin_objects.FIN_OBJECT_ID as objectid from "
				+ "sp_fin_objects where level > 1 start with LOWER(object_name) = "
				+ "? or LOWER(object_name) = ? or LOWER(object_name) = "
				+ "? or LOWER(object_name) = ? or LOWER(object_name) = "
				+ "? connect by prior FIN_OBJECT_ID = PARENT_ID) e1 inner join "
				+ "(select t1.casename, t1.casecost, t1.startdate, t1.enddate, "
				+ "t1.parentname, t1.objid, t2.VALUE as priority from (select "
				+ "q1.object_name as casename, q1.cost as casecost, q2.VALUE_DATE as "
				+ "startdate, q1.enddate as enddate, q4.OBJECT_NAME as parentname, "
				+ "q1.Fin_object_id as objid from (select p1.OBJECT_NAME, p1.VALUE as cost, "
				+ "p1.FIN_OBJECT_ID, p2.VALUE_DATE as enddate, p1.parent_id from "
				+ "(select a1.OBJECT_NAME, a3.VALUE, a1.FIN_OBJECT_ID, a1.PARENT_ID from "
				+ "SP_FIN_OBJECTS a1 inner join sp_fin_object_types a2 on "
				+ "a1.FIN_OBJECT_TYPE_ID = a2.FIN_OBJECT_TYPE_ID inner join sp_params a3 on "
				+ "a1.FIN_OBJECT_ID = a3.FIN_OBJECT_ID inner join SP_ATTRIBUTES a4 on "
				+ "a3.ATTRIBUTE_ID = a4.ATTRIBUTE_ID where a2.FIN_OBJECT_TYPE_ID = ? and "
				+ "a4.attribute_id = ? and a1.USER_ID = ?) p1 inner join SP_PARAMS p2 on "
				+ "p1.FIN_OBJECT_ID = p2.FIN_OBJECT_ID inner join SP_ATTRIBUTES p3 on "
				+ "p2.ATTRIBUTE_ID = p3.ATTRIBUTE_ID where p3.ATTRIBUTE_ID = ?) q1 inner "
				+ "join sp_params q2 on q1.FIN_OBJECT_ID = q2.FIN_OBJECT_ID inner join "
				+ "SP_ATTRIBUTES q3 on q2.ATTRIBUTE_ID = q3.ATTRIBUTE_ID inner join "
				+ "SP_FIN_OBJECTS q4 on q1.parent_id = q4.FIN_OBJECT_ID where "
				+ "q3.ATTRIBUTE_ID = ?) t1 inner join sp_params t2 on "
				+ "t1.objid = t2.FIN_OBJECT_ID inner join SP_ATTRIBUTES t3 on "
				+ "t2.ATTRIBUTE_ID = t3.ATTRIBUTE_ID where t3.ATTRIBUTE_ID = ?) e2 on e1.objectid = e2.objid";

		Connection connect = ConnectionFactory.getConnection();
		try {
			PreparedStatement prepare = connect.prepareStatement(query);

			prepare.setString(1, category1.toLowerCase());
			prepare.setString(2, category2.toLowerCase());
			prepare.setString(3, category3.toLowerCase());
			prepare.setString(4, category4.toLowerCase());
			prepare.setString(5, category5.toLowerCase());

			prepare.setInt(6, type);
			prepare.setInt(7, atr1);
			prepare.setInt(8, userId);
			prepare.setInt(9, atr2);
			prepare.setInt(10, atr3);
			prepare.setInt(11, atr4);

			list = new ArrayList<ActiveCasesModel>();
			ResultSet result = prepare.executeQuery();
			while (result.next()) {
				ActiveCasesModel activeCases = new ActiveCasesModel();
				ActiveCasesController activeCasesController = new ActiveCasesController();

				activeCases = activeCasesController.setSpaceHierarchy(activeCases, result.getInt("LEVEL"));
				activeCases.setHierarchy(result.getString("HIERARCHY"));
				activeCases.setCaseName(result.getString("CASENAME"));
				activeCases.setCaseCost(result.getString("CASECOST"));
				activeCases.setStartDate(result.getDate("STARTDATE").toString());
				activeCases.setEndDate(result.getDate("ENDDATE").toString());
				activeCases.setParentName(result.getString("PARENTNAME"));
				activeCases.setPriority(result.getDouble("PRIORITY"));
				activeCases = activeCasesController.setPriority(activeCases, result.getDouble("PRIORITY"));
				activeCases.setLevel(result.getInt("LEVEL"));
				activeCases.setObjectId(result.getInt("OBJECTID"));

				list.add(activeCases);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}

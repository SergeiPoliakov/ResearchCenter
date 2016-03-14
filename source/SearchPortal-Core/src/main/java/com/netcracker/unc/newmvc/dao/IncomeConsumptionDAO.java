package com.netcracker.unc.newmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.netcracker.unc.newmvc.connection.ConnectionFactory;
import com.netcracker.unc.newmvc.dao.models.IncomeConsumptionModel;

public class IncomeConsumptionDAO {

	// name columns of database
	private final int typeIncomeInt = 2;
	private final String checkRegularIncome = "true";
	private final int typeConsumptionInt = 4;
	private final int attributeIncomeInt = 5;
	private final int attributeConsumptionInt = 12;

	public IncomeConsumptionModel procentForBar(IncomeConsumptionModel inCon, int userId) {

		Connection connect = ConnectionFactory.getConnection();
		// System.out.println(user.get_user_id());
		// column names: 1) maxCos 2) minCost 3) maxName 4) minName 5) avgCost
		// 6) sumCosts
		String query = "select max(to_number(q1.VALUE)) over() as maxcost, min(to_number(q1.VALUE)) "
				+ "over() as mincost, first_value(q2.object_name) "
				+ "over(order by to_number(q1.VALUE) desc) as maxname, "
				+ "first_value(q2.object_name) over(order by to_number(q1.VALUE)) as "
				+ "minname, sum(to_number(q1.VALUE)) over() as sumcost from SP_PARAMS q1 "
				+ "right join (select c1.FIN_OBJECT_ID, c2.fin_object_type_id, "
				+ "c2.OBJECT_NAME from SP_PARAMS c1 inner join SP_FIN_OBJECTS c2 on "
				+ "c1.FIN_OBJECT_ID = c2.FIN_OBJECT_ID where c2.FIN_OBJECT_TYPE_ID = ? "
				+ "and c2.USER_ID = ? and lower(c1.VALUE) = ?) q2 on "
				+ "q1.FIN_OBJECT_ID = q2.fin_object_id where q1.ATTRIBUTE_ID = ?";

		try {
			// for income
			PreparedStatement prepare = connect.prepareStatement(query);
			prepare.setInt(1, typeIncomeInt);
			prepare.setInt(2, userId);
			prepare.setString(3, checkRegularIncome.toLowerCase());
			prepare.setInt(4, attributeIncomeInt);
			ResultSet result = prepare.executeQuery();
			while (result.next()) {
				inCon.setMaxIncome(result.getLong("MAXCOST"));
				inCon.setMinIncome(result.getLong("MINCOST"));
				inCon.setMaxIncomeName(result.getString("MAXNAME"));
				inCon.setMinIncomeName(result.getString("MINNAME"));
				// setAvgIncome(result.getLong("AVGCOST"));
				inCon.setFullIncome(result.getLong("SUMCOST"));
			}
			prepare.close();

			query = "select avg(w1.VALUE) as avgcost from sp_params w1 inner join "
					+ "SP_FIN_OBJECTS w2 on w1.FIN_OBJECT_ID = w2.FIN_OBJECT_ID "
					+ "where w2.FIN_OBJECT_TYPE_ID = ? and w1.ATTRIBUTE_ID = ? " + "and w2.USER_ID = ?";

			prepare = connect.prepareStatement(query);
			prepare.setInt(1, typeIncomeInt);
			prepare.setInt(2, attributeIncomeInt);
			prepare.setInt(3, userId);
			result = prepare.executeQuery();
			while (result.next()) {
				inCon.setAvgIncome(result.getLong("AVGCOST"));
			}
			prepare.close();

			// for consumption
			query = "select max(to_number(q1.VALUE)) over() as maxcost, min(to_number(q1.VALUE)) "
					+ "over() as mincost, first_value(q2.object_name) over(order by "
					+ "to_number(q1.VALUE) desc) as maxname, first_value(q2.object_name) "
					+ "over(order by to_number(q1.VALUE)) as minname, "
					+ "sum(to_number(q1.VALUE)) over() as sumcost, avg(to_number(q1.VALUE)) "
					+ "over() as avgcost from SP_PARAMS q1 right join (select "
					+ "z1.VALUE_DATE, z1.fin_object_id, z2.object_name from sp_params z1 "
					+ "inner join SP_FIN_OBJECTS z2 on z1.fin_object_id = z2.fin_object_id "
					+ "where z1.value_date is not null and z2.FIN_OBJECT_TYPE_ID = ? and "
					+ "z1.value_date > sysdate and z2.USER_ID = ?) q2 on "
					+ "q1.FIN_OBJECT_ID = q2.fin_object_id where q1.ATTRIBUTE_ID = ?";

			prepare = connect.prepareStatement(query);
			prepare.setInt(1, typeConsumptionInt);
			prepare.setInt(2, userId);
			prepare.setInt(3, attributeConsumptionInt);
			result = prepare.executeQuery();

			while (result.next()) {
				inCon.setMaxConsumption(result.getInt("MAXCOST"));
				inCon.setMinConsumption(result.getInt("MINCOST"));
				inCon.setMaxConsumptionName(result.getString("MAXNAME"));
				inCon.setMinConsumptionName(result.getString("MINNAME"));
				inCon.setAvgConsumption(result.getInt("AVGCOST"));
				inCon.setFullConsumption(result.getInt("SUMCOST"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return inCon;
	}
}

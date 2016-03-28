package com.netcracker.unc.newmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.netcracker.unc.newmvc.connection.ConnectionFactory;
import com.netcracker.unc.newmvc.ejb.models.IncomeConsumptionModel;

public class IncomeConsumptionDAO {

	// name columns of database
	private final int typeIncomeInt = 2;
	private final int typeConsumptionInt = 4;
	private final int attributeIncomeInt = 5; // Сумма дохода
	private final int attributeConsumptionInt = 12;

	public IncomeConsumptionModel procentForBar(IncomeConsumptionModel inCon, int userId) {

		Connection connect = ConnectionFactory.getConnection();
		// System.out.println(user.get_user_id());
		// column names: 1) maxCost 2) minCost 3) maxName 4) minName 5) avgCost
		// 6) sumCosts
		String query = "select max(to_number(q1.VALUE)) over() as maxcost, min(to_number(q1.VALUE)) over() as mincost, "
				+ "first_value(q3.object_name) over(order by to_number(q1.VALUE) desc) as maxname, first_value(q3.object_name) "
				+ "over(order by to_number(q1.VALUE)) as minname, sum(to_number(q1.VALUE)) over() as sumcost, avg(to_number(q1.VALUE))over() "
				+ "as avgcost from sp_params q1 right join (select n1.FIN_OBJECT_ID from (select s1.FIN_OBJECT_ID from sp_fin_objects s1 "
				+ "where s1.USER_ID = ? and s1.FIN_OBJECT_TYPE_ID = ?) n1 right join sp_params n2 on n1.FIN_OBJECT_ID = n2.FIN_OBJECT_ID "
				+ "where TRUNC(MONTHS_BETWEEN(sysdate, n2.value_date), 0) = 0) q2 on q1.FIN_OBJECT_ID = q2.FIN_OBJECT_ID right join "
				+ "SP_FIN_OBJECTS q3 on q2.FIN_OBJECT_ID = q3.FIN_OBJECT_ID where q1.ATTRIBUTE_ID = ?";

		try {
			// for income
			PreparedStatement prepare = connect.prepareStatement(query);
			prepare.setInt(1, userId);
			prepare.setInt(2, typeIncomeInt);
			prepare.setInt(3, attributeIncomeInt);
			ResultSet result = prepare.executeQuery();
			while (result.next()) {
				inCon.setMaxIncome(result.getLong("MAXCOST"));
				inCon.setMinIncome(result.getLong("MINCOST"));
				inCon.setMaxIncomeName(result.getString("MAXNAME"));
				inCon.setMinIncomeName(result.getString("MINNAME"));
				// setAvgIncome(result.getLong("AVGCOST"));
				inCon.setFullIncome(result.getLong("SUMCOST"));
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

package com.netcracker.unc.newmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.netcracker.unc.newmvc.connection.ConnectionFactory;
import com.netcracker.unc.newmvc.ejb.models.SalaryModel;

public class SalaryDAO {

	private final int typeCol = 2; // Доход
	private final String valueSalary = "true"; // Ежемесячный доход true/false
	private final int atrInt = 5; // Сумма дохода

	public SalaryModel getLastCheckSalary(int userId) {

		SalaryModel salaryModel = new SalaryModel();

		String query = "select * from (select a1.FIN_OBJECT_ID, a1.OBJECT_NAME, a1.USER_ID, "
				+ "a2.VALUE_DATE, a3.FIN_OBJECT_TYPE_ID, a3.FIN_OBJECT_TYPE_NAME, "
				+ "a2.ATTRIBUTE_ID, TRUNC(MONTHS_BETWEEN(sysdate, a2.value_date), 0) as "
				+ "mounth_count, a5.VALUE from SP_FIN_OBJECTS a1 right join sp_params a2 "
				+ "on a1.FIN_OBJECT_ID = a2.FIN_OBJECT_ID inner join SP_FIN_OBJECT_TYPES a3 "
				+ "on a1.FIN_OBJECT_TYPE_ID = a3.FIN_OBJECT_TYPE_ID inner join sp_params a4 "
				+ "on a1.FIN_OBJECT_ID = a4.FIN_OBJECT_ID inner join sp_params a5 on "
				+ "a1.FIN_OBJECT_ID = a5.FIN_OBJECT_ID where a3.FIN_OBJECT_TYPE_ID = ? and "
				+ "a1.USER_ID = ? and a4.VALUE = ? and a2.VALUE_DATE IS NOT NULL and "
				+ "a5.ATTRIBUTE_ID = ? order by a2.VALUE_DATE desc) where rownum = 1";

		Connection connect = ConnectionFactory.getConnection();
		try {
			PreparedStatement prepare = connect.prepareStatement(query);
			prepare.setInt(1, typeCol);
			prepare.setInt(2, userId);
			prepare.setString(3, valueSalary);
			prepare.setInt(4, atrInt);
			ResultSet result = prepare.executeQuery();
			if (result.next()) {
				salaryModel.setObjectId(result.getInt("FIN_OBJECT_ID"));
				salaryModel.setObjectName(result.getString("OBJECT_NAME"));
				salaryModel.setUserId(userId);
				salaryModel.setLastCheckDate(result.getDate("VALUE_DATE"));
				salaryModel.setDateCount(result.getInt("MOUNTH_COUNT"));
				salaryModel.setValue(result.getString("VALUE"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return salaryModel;
	}

}

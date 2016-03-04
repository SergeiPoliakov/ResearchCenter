package com.netcracker.unc.beans;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ejb.Stateless;
import com.netcracker.unc.mvc.connection.ConnectionFactory;
import com.netcracker.unc.mvc.models.UserModel;

/**
 * this class check salary for last month
 * 
 * @author Kolesnikov
 *
 */
@Stateless
public class CheckSalary {

	private String dateStr = null;
	private long salary;
	private Connection connect = null;
	private PreparedStatement prepare = null;
	private ResultSet result = null;
	private Date date = null;

	// name columns of database
	private String typeStr = "Доход";
	private String caseNameStr = "Зарплата";
	private String checkSalary = "true";
	//////////////

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public String getDate() {
		return dateStr;
	}

	public void setDate(String dateStr) {
		this.dateStr = dateStr;
	}

	public void checkSalary(UserModel user) {
		salary = 0;
		dateStr = null;

		// this table view salary 1)DATE and 2)SALARY for last month
		String query = "select n3.\"DATE\", n3.\"VALUE\" from sp_params n1 inner join SP_FIN_OBJECTS n2 on n1.FIN_OBJECT_ID = n2.fin_object_id "
				+ "inner join (select p2.\"DATE\", p1.\"VALUE\", p2.\"ID\" from sp_params p1 inner join ( "
				+ "select a1.value_date as \"DATE\", a1.FIN_OBJECT_ID as \"ID\" from sp_params a1 inner join sp_fin_objects a2 "
				+ "on a1.FIN_OBJECT_ID = a2.FIN_OBJECT_ID inner join SP_FIN_OBJECT_TYPES a3 on a2.FIN_OBJECT_TYPE_ID = a3.FIN_OBJECT_TYPE_ID "
				+ "where a2.USER_ID = ? and lower(a3.FIN_OBJECT_TYPE_NAME) = ? and lower(a2.OBJECT_NAME) = ? "
				+ "and a1.VALUE_DATE is not null and a1.value_date > add_months(sysdate, -1) order by a1.value_date desc) p2 "
				+ "on p1.FIN_OBJECT_ID = p2.\"ID\" where regexp_like(p1.\"VALUE\", '^[0-9]+$')) n3 on n2.fin_object_id = n3.\"ID\" "
				+ "where lower(n1.\"VALUE\") = ?";

		connect = ConnectionFactory.getConnection();
		try {
			prepare = connect.prepareStatement(query);
			prepare.setInt(1, user.get_user_id());
			prepare.setString(2, typeStr.toLowerCase());
			prepare.setString(3, caseNameStr.toLowerCase());
			prepare.setString(4, checkSalary.toLowerCase());
			result = prepare.executeQuery();
			result.next();
			if (!result.isAfterLast()) {
				date = result.getDate("DATE");
				salary = Long.valueOf(result.getString("VALUE"));
				dateStr = date.toString();
			}

			/*
			 * oldDate =
			 * parse.parse(Calendar.getInstance().getTime().toString()); date =
			 * new Date(oldDate.getTime());
			 * 
			 * System.out.println(date.toString());
			 */

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}

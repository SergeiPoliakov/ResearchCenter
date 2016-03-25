package com.netcracker.unc.newmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.netcracker.unc.newmvc.connection.ConnectionFactory;
import com.netcracker.unc.newmvc.dao.models.StatisticModel;

public class StatisticDAOAlex {

	public StatisticModel procentForPie(StatisticModel inStat, int userId) {

		Connection connect = ConnectionFactory.getConnection();
	
		String query = "SELECT SUM(par.VALUE) AS res "
		+ "FROM SP_FIN_OBJECTS fo INNER JOIN SP_ATTRIBUTES atr ON fo.FIN_OBJECT_TYPE_ID = atr.FIN_OBJECT_TYPE_ID "
		+ "INNER JOIN SP_PARAMS par ON atr.ATTRIBUTE_ID = par.ATTRIBUTE_ID "
		+ "WHERE FIN_OBJECT_TYPE_ID = 3 AND fo.USER_ID = ? "
		+ "AND date_format(par.VALUE_DATE, '%Y%m') = date_format(now(), '%Y%m')";

		try {
			// get sum
			PreparedStatement prepare = connect.prepareStatement(query);
			prepare.setInt(1, userId);
			ResultSet result = prepare.executeQuery();
			while(result.next()) {
				inStat.setReservedMoney(result.getLong("RES"));
			}
			prepare.close();

			query = "select SUM(p.VALUE) AS s"
			+ "from SP_FIN_OBJECTS o, SP_PARAMS p "
			+ "where p.FIN_OBJECT_ID=o.FIN_OBJECT_ID "
			+ "and o.USER_ID=? "
			+ "and p.ATTRIBUTE_ID=1 "
			+ "and o.FIN_OBJECT_TYPE_ID=";
			prepare.setInt(1, userId);
			prepare = connect.prepareStatement(query);
			result = prepare.executeQuery();
			while (result.next()) {
				inStat.setSum(result.getLong("S"));
			}
			prepare.close();
			inStat.setfreeMoney();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return inStat;
	}
/*	public List<transactionModel> transactionTable(List<transactionModel> inTran, int userId) {
		
		String query = "SELECT dateformat(transaction_date, '%d.%m.%Y') AS d, sp_fin_object.object_name AS name, FORMAT(value, 'C', 'ru-ru') AS val "
        + "FROM sp_transactions tr JOIN sp_fin_object fo ON tr.fin_object_id = fo.fin_object_id "
        + "WHERE fo.fin_object_type_id IN (2,3) "
        + "AND tr.user_id = ?";
		
		try {
		prepare.setInt(1, userId);
		PreparedStatement prepare = connect.prepareStatement(query);
		ResultSet result = prepare.executeQuery();
		DateFormat dateFormat = new SimpleDateFormat("dd-mm-YYYY");
		
		while (result.next()) {
			    transactionModel temp = new transactionModel();
				temp.setDate(dateFormat.format((result.getDate("D")));
				temp.setName(result.getLong("NAME"));
				temp.setValue(result.getString("VAL"));
				inTran.add(temp);
			}
			prepare.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
		return inTran;
		
	}*/
}

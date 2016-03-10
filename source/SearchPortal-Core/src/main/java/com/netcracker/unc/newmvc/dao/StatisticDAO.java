package com.netcracker.unc.newmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.netcracker.unc.newmvc.dao.StatisticQueries;
import com.netcracker.unc.mvc.connection.ConnectionFactory;
import com.netcracker.unc.newmvc.dao.UserModel;

public class StatisticDAO {
	
	UserModel user;
	
public StatisticDAO() {
		
	}
	
	public StatisticDAO(UserModel user) {
		this.user = user;
	}
	
	public int getSumBalance() {
		PreparedStatement prepare;
		ResultSet result;
		Connection connect = ConnectionFactory.getConnection();

		try {
			prepare = connect.prepareStatement(StatisticQueries.getSumAllBalancesForUsersByUserId);
			prepare.setInt(1, user.getUserId());
			result = prepare.executeQuery();
			result.next();

			return result.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getConsumptionSum() {
		PreparedStatement prepare;
		ResultSet result;
		Connection connect = ConnectionFactory.getConnection();

		try {
			prepare = connect.prepareStatement(StatisticQueries.getSumAllConsumptionForUsersByUserId);
			prepare.setInt(1, user.get_user_id());
			result = prepare.executeQuery();
			result.next();

			return result.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
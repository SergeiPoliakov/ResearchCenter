package com.netcracker.unc.newmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.netcracker.unc.newmvc.connection.ConnectionFactory;
import com.netcracker.unc.newmvc.dao.models.CreditModel;
import com.netcracker.unc.newmvc.dao.models.UserModel;
import com.netcracker.unc.newmvc.dao.queries.CreditQueries;

public class CreditDAO {

	public List<CreditModel> getAllCredits(UserModel user) throws SQLException {
		Connection connect = ConnectionFactory.getConnection();
		CreditModel temp;
		List<CreditModel> listOfAllCredits = new ArrayList<CreditModel>();

		PreparedStatement statement = connect.prepareStatement(CreditQueries.getAllCreditsByUserID);
		statement.setInt(1, user.getUserId());
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			temp = new CreditModel();
			temp.setCreditID(result.getInt(1));
			temp.setCreditName(result.getString(2));
			listOfAllCredits.add(temp);
		}
		connect.close();
		return listOfAllCredits;
	}

	public void addCredit(Object object, UserModel user) throws SQLException {
		Connection connect = ConnectionFactory.getConnection();
		CreditModel newCredit = (CreditModel) object;
		PreparedStatement statement = connect.prepareStatement(CreditQueries.setNewCreditForUser);
		statement.setInt(1, newCredit.getCreditID());
		statement.setString(2, newCredit.getCreditName());
		statement.setInt(3, user.getUserId());
		statement.executeUpdate();
		connect.close();
	}

	public void addValues(Object object) throws SQLException {
		Connection connect = ConnectionFactory.getConnection();
		CreditModel valuesContainer = (CreditModel) object;
		int creditID = valuesContainer.getCreditID();
		PreparedStatement statement;
		if (valuesContainer.getCreditValue() != 0) {
			statement = connect.prepareStatement(CreditQueries.setCreditValue);
			statement.setInt(1, creditID);
			statement.setInt(2, valuesContainer.getCreditValue());
			statement.executeUpdate();
			statement = null;
		}

		if (valuesContainer.getCreditBalance() != 0) {
			statement = connect.prepareStatement(CreditQueries.setCreditBalance);
			statement.setInt(1, creditID);
			statement.setInt(2, valuesContainer.getCreditBalance());
			statement.executeUpdate();
			statement = null;
		}

		if (valuesContainer.getReceivingDate() != null) {
			statement = connect.prepareStatement(CreditQueries.setCreditReceivingDate);
			statement.setInt(1, creditID);
			statement.setString(2, valuesContainer.getReceivingDate());
			statement.executeUpdate();
			statement = null;
		}

		if (valuesContainer.getCreditPercent() != 0) {
			statement = connect.prepareStatement(CreditQueries.setCreditPercent);
			statement.setInt(1, creditID);
			statement.setDouble(2, valuesContainer.getCreditPercent());
			statement.executeUpdate();
			statement = null;
		}

		if (valuesContainer.getPayPeriod() != 0) {
			statement = connect.prepareStatement(CreditQueries.setCreditPayPeriod);
			statement.setInt(1, creditID);
			statement.setDouble(2, valuesContainer.getPayPeriod());
			statement.executeUpdate();
			statement = null;
		}

		if (valuesContainer.getMonthPay() != 0) {
			statement = connect.prepareStatement(CreditQueries.setCreditMonthPay);
			statement.setInt(1, creditID);
			statement.setDouble(2, valuesContainer.getMonthPay());
			statement.executeUpdate();
			statement = null;
		}
		connect.close();
	}

	public CreditModel getCreditByID(int creditID, UserModel user) throws SQLException {
		Connection connect = ConnectionFactory.getConnection();
		ResultSet result;
		CreditModel temp = new CreditModel();
		temp.setCreditID(creditID);
		PreparedStatement statement = connect.prepareStatement(CreditQueries.getCreditByID);
		statement.setInt(1, user.getUserId());
		statement.setInt(2, temp.getCreditID());
		result = statement.executeQuery();
		result.next();
		temp.setCreditName(result.getString(2));
		temp.setObjectTypeID(result.getInt(3));
		return temp;

	}

	public void getValues(CreditModel unfinishedModel, UserModel user) throws SQLException {
		PreparedStatement statement;
		Connection connection = ConnectionFactory.getConnection();
		ResultSet result;

		int userID = user.getUserId();
		int modelID = unfinishedModel.getCreditID();

		statement = connection.prepareStatement(CreditQueries.getCreditValue);
		statement.setInt(1, userID);
		statement.setInt(2, modelID);
		result = statement.executeQuery();
		result.next();
		unfinishedModel.setCreditValue(result.getInt(1));

		connection.close();
	}
}

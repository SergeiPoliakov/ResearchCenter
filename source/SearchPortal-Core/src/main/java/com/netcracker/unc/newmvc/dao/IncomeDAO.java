package com.netcracker.unc.newmvc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.netcracker.unc.newmvc.connection.ConnectionFactory;
import com.netcracker.unc.newmvc.dao.models.IncomeModel;
import com.netcracker.unc.newmvc.dao.models.IncomeModel;
import com.netcracker.unc.newmvc.dao.models.UserModel;
import com.netcracker.unc.newmvc.dao.queries.IncomeQueries;
import com.netcracker.unc.newmvc.ejb.models.InvoiceModel;
import com.netcracker.unc.newmvc.ejb.queries.InvoiceQueries;
import com.netcracker.unc.newmvc.dao.queries.IncomeQueries;

public class IncomeDAO {
	
	UserModel user;
	
	public IncomeDAO(){
		
	}
	
	public IncomeDAO(UserModel user){
		this.user = user;
	}
	
	public void setUser(UserModel user) {
		this.user = user;
	}
	
	public ArrayList<IncomeModel> getAllIncomes(UserModel user) {
		
		setUser(user);
		IncomeModel income;
		Connection connect = ConnectionFactory.getConnection();
		ArrayList<IncomeModel> listGetAllInvcome = new ArrayList<IncomeModel>();
		try {
			PreparedStatement prepare = connect.prepareStatement(IncomeQueries.getAllIncomeByUserId);
			prepare.setInt(1, user.getUserId());
			ResultSet result = prepare.executeQuery();

			while (result.next()) {
				income = new IncomeModel();
				income.setUserId(user.getUserId());
				income.setIncomeId(result.getInt(1));
				income.setIncomeName(result.getString(2));
				income.setDateIncome(result.getDate(3));
				income.setIncomeSum(result.getInt(4));
				income.setMonth(Boolean.parseBoolean(result.getString(5)));
				income.setInvoiceId(result.getInt(6));
				income.getIncomesInvoice().setInvoiceName(result.getString(7));
				listGetAllInvcome.add(income);
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
		return listGetAllInvcome;
	}

	public void addIncome(IncomeModel incomeJsp, UserModel user) {
		setUser(user);
		PreparedStatement prepare;
		Connection connect = ConnectionFactory.getConnection();
		IncomeModel income = incomeJsp;
		long auto_id;

		try {
			prepare = connect.prepareStatement(IncomeQueries.setIncome, new String[] { "FIN_OBJECT_ID" });
			prepare.setString(1, income.getIncomeName());
			prepare.setInt(2, income.getInvoiceId());
			prepare.setInt(3, user.getUserId());			
			if (prepare.executeUpdate()>0) {

				ResultSet rs = prepare.getGeneratedKeys();
				rs.next();
				auto_id = rs.getLong(1);
				
				prepare = connect.prepareStatement(IncomeQueries.setIsEveyMonth);
				prepare.setLong(1, auto_id);
				prepare.setBoolean(2, income.isMonth());
				prepare.executeUpdate();

				prepare = connect.prepareStatement(IncomeQueries.setSum);
				prepare.setLong(1, auto_id);
				prepare.setInt(2, income.getIncomeSum());
				prepare.executeUpdate();

				prepare = connect.prepareStatement(IncomeQueries.setDate);
				prepare.setLong(1, auto_id);
				prepare.setDate(2, (Date)income.getDateIncome());
				prepare.executeUpdate();
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

	}
	
	public IncomeModel getIncome(int incomeId, UserModel user) {
		PreparedStatement prepare;
		ResultSet result;
		Connection connect = ConnectionFactory.getConnection();
		IncomeModel income = new IncomeModel();
		income.setIncomeId(incomeId);
		income.setUserId(user.getUserId());

		try {
			prepare = connect.prepareStatement(IncomeQueries.getIncome);
			prepare.setInt(1, user.getUserId());
			prepare.setInt(2, incomeId);
			result = prepare.executeQuery();
			result.next();

			income.setIncomeName(result.getString(1));
			income.setDateIncome(result.getDate(2));
			income.setIncomeSum(result.getInt(3));
			income.setMonth(result.getBoolean(4));
			income.setInvoiceId(result.getInt(5));
			income.getIncomesInvoice().setInvoiceName(result.getString(6));

			prepare = connect.prepareStatement(IncomeQueries.getParams);
			prepare.setInt(1, user.getUserId());
			prepare.setInt(2, incomeId);
			result = prepare.executeQuery();
			result.next();

			income.setDateIncome(result.getDate(1));
			income.setIncomeSum(result.getInt(2));
			income.setMonth(Boolean.parseBoolean(result.getString(3)));

			return income;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	

	public void updateIncome(IncomeModel incomeJsp) {
		PreparedStatement prepare;
		Connection connect = ConnectionFactory.getConnection();
		IncomeModel income = (IncomeModel) incomeJsp;

		try {
			prepare = connect.prepareStatement(IncomeQueries.updateName);
			prepare.setString(1, income.getIncomeName());
			prepare.setInt(2, income.getIncomeId());
			prepare.executeUpdate();
			
			prepare = connect.prepareStatement(IncomeQueries.updateSum);
			prepare.setInt(1, income.getIncomeSum());
			prepare.setInt(2, income.getIncomeId());
			prepare.executeUpdate();
			
			prepare = connect.prepareStatement(IncomeQueries.updateIsEveryMonth);
			prepare.setString(1, String.valueOf(income.isMonth()));
			prepare.setInt(2, income.getIncomeId());
			prepare.executeUpdate();
			
			prepare = connect.prepareStatement(IncomeQueries.updateDate);
			prepare.setDate(1, income.getDateIncome());
			prepare.setInt(2, income.getIncomeId());
			prepare.executeUpdate();
			
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
	
	public void deleteIncome(int incomeId) {
		Logger log = Logger.getLogger(IncomeDAO.class.getName());
		
		log.warning("try to get connction...");
		
		PreparedStatement prepare;
		Connection connect = ConnectionFactory.getConnection();
		log.warning("get connction...");
		
		try {
			log.warning("prepare to delete params...");
			prepare = connect.prepareStatement(IncomeQueries.deleteParams);
			prepare.setInt(1, incomeId);
			prepare.executeUpdate();
			
			log.warning("params deleted");
			
			prepare = connect.prepareStatement(IncomeQueries.deleteIncome);
			prepare.setInt(1, incomeId);
			prepare.executeUpdate();
			
			log.warning("object deleted");
			
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
	
	public InvoiceModel getInvoice(int incomeId, UserModel user) {
		PreparedStatement prepare;
		ResultSet result;
		Connection connect = ConnectionFactory.getConnection();
		IncomeModel income = this.getIncome(incomeId, user);
		InvoiceModel invoice = new InvoiceModel();
		invoice.setInvoiceId(income.getInvoiceId());

		try {
			prepare = connect.prepareStatement(InvoiceQueries.getInvoiceName);
			prepare.setInt(1, user.getUserId());
			prepare.setInt(2, income.getInvoiceId());
			result = prepare.executeQuery();
			result.next();

			invoice.setInvoiceName(result.getString(1));

			prepare = connect.prepareStatement(InvoiceQueries.getBalanceCreditAndPercent);
			prepare.setInt(1, user.getUserId());
			prepare.setInt(2, income.getInvoiceId());
			result = prepare.executeQuery();
			result.next();

			invoice.setBalance(result.getInt(1));
			invoice.setCredit(Boolean.valueOf(result.getString(2)));
			invoice.setPercent(result.getInt(3));

			return invoice;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}

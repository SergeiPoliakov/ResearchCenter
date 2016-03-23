package com.netcracker.unc.newmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.netcracker.unc.newmvc.connection.ConnectionFactory;
import com.netcracker.unc.newmvc.dao.models.InvoiceModel;
import com.netcracker.unc.newmvc.dao.models.UserModel;
import com.netcracker.unc.newmvc.dao.queries.InvoiceQueries;

public class InvoiceDAO {

	UserModel user;

	public InvoiceDAO() {

	}

	public InvoiceDAO(UserModel user) {
		this.user = user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public ArrayList<InvoiceModel> getAllInvoice(UserModel user) {
		setUser(user);
		return getAllInvoice();

	}

	public ArrayList<InvoiceModel> getAllInvoice() {

		InvoiceModel invoice;
		Connection connect = ConnectionFactory.getConnection();
		ArrayList<InvoiceModel> listGetAllInvoice = new ArrayList<InvoiceModel>();
		try {
			PreparedStatement prepare = connect.prepareStatement(InvoiceQueries.getAllInvoicesForUserByUserId);
			prepare.setInt(1, user.getUserId());
			ResultSet result = prepare.executeQuery();

			while (result.next()) {
				invoice = new InvoiceModel();
				invoice.setInvoiceId(result.getInt(1));
				invoice.setInvoiceName(result.getString(2));
				invoice.setBalance(result.getInt(3));
				invoice.setCredit(Boolean.parseBoolean(result.getString(4)));
				invoice.setPercent(result.getInt(5));
				listGetAllInvoice.add(invoice);
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
		return listGetAllInvoice;
	}

	public void addInvoice(InvoiceModel invoiceJsp) {
		Logger log = Logger.getLogger(InvoiceDAO.class.getName());
		// setUser(user);
		PreparedStatement prepare;
		Connection connect = ConnectionFactory.getConnection();
		// InvoiceModel invoice = invoiceJsp;
		long auto_id;

		try {
			prepare = connect.prepareStatement(InvoiceQueries.setNewInvoiceForUser, new String[] { "FIN_OBJECT_ID" });
			prepare.setString(1, invoiceJsp.getInvoiceName());
			prepare.setInt(2, user.getUserId());			
			if (prepare.executeUpdate()>0) {

				ResultSet rs = prepare.getGeneratedKeys();
				rs.next();
				auto_id = rs.getLong(1);
				log.warning("New ID: " + auto_id);

				prepare = connect.prepareStatement(InvoiceQueries.setNewBalanceInInvoiceByInvoiceId);
				prepare.setLong(1, auto_id);
				prepare.setInt(2, invoiceJsp.getBalance());
				prepare.executeUpdate();

				prepare = connect.prepareStatement(InvoiceQueries.setNewCreditInInvoiceByInvoiceId);
				prepare.setLong(1, auto_id);
				prepare.setString(2, String.valueOf(invoiceJsp.isCredit()));
				prepare.executeUpdate();

				prepare = connect.prepareStatement(InvoiceQueries.setNewPercentInInvoiceByInvoiceId);
				prepare.setLong(1, auto_id);
				prepare.setDouble(2, invoiceJsp.getPercent());
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

	public InvoiceModel getInvoiceByInoiceId(int invoiceId) {
		PreparedStatement prepare;
		ResultSet result;
		Connection connect = ConnectionFactory.getConnection();
		InvoiceModel invoice = new InvoiceModel();
		invoice.setInvoiceId(invoiceId);

		try {
			prepare = connect.prepareStatement(InvoiceQueries.getInvoiceForUserByInvoiceId);
			prepare.setInt(1, user.getUserId());
			prepare.setInt(2, invoiceId);
			result = prepare.executeQuery();
			result.next();

			invoice.setInvoiceName(result.getString(2));

			prepare = connect.prepareStatement(InvoiceQueries.getBalanceCreditAndPercentByInvoiceId);
			prepare.setInt(1, user.getUserId());
			prepare.setInt(2, invoiceId);
			result = prepare.executeQuery();
			result.next();

			invoice.setBalance(result.getInt(1));
			invoice.setCredit(Boolean.parseBoolean(result.getString(2)));
			invoice.setPercent(result.getDouble(3));

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

	public void updateInvoice(InvoiceModel invoiceJsp) {
		PreparedStatement prepare;
		Connection connect = ConnectionFactory.getConnection();
		InvoiceModel invoice = (InvoiceModel) invoiceJsp;

		try {
			prepare = connect.prepareStatement(InvoiceQueries.updateInvoiseById);
			prepare.setString(1, invoice.getInvoiceName());
			prepare.setInt(2, user.getUserId());
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

	public void deleteInvoice(int invoiceId) {
		Logger log = Logger.getLogger(InvoiceDAO.class.getName());
		
		log.warning("try to get connction...");
		
		PreparedStatement prepare;
		Connection connect = ConnectionFactory.getConnection();
		//InvoiceModel invoice = (InvoiceModel) invoiceJsp;

		log.warning("get connction...");
		
		try {
			log.warning("prepare to delete params...");
			prepare = connect.prepareStatement(InvoiceQueries.deleteParamInvoiceByInvoiceId);
			prepare.setInt(1, invoiceId);
			prepare.executeUpdate();
			
			log.warning("params deleted");
			
			prepare = connect.prepareStatement(InvoiceQueries.deleteInvoiceNameByInvoiceId);
			prepare.setInt(1, invoiceId);
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

	public int getSumBalance() {
		PreparedStatement prepare;
		ResultSet result;
		Connection connect = ConnectionFactory.getConnection();

		try {
			prepare = connect.prepareStatement(InvoiceQueries.getSumAllBalancesForUsersByUserId);
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

	public void updateBalance(InvoiceModel invoiceJsp) {
		PreparedStatement prepare;
		Connection connect = ConnectionFactory.getConnection();
		InvoiceModel invoice = invoiceJsp;

		try {
			prepare = connect.prepareStatement(InvoiceQueries.updateBalanceInInvoice);
			prepare.setInt(1, invoice.getBalance());
			prepare.setInt(2, invoice.getInvoiceId());
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

	public int getCountInvoices() {
		PreparedStatement prepare;
		ResultSet result;
		Connection connect = ConnectionFactory.getConnection();

		try {
			prepare = connect.prepareStatement(InvoiceQueries.getInvoicesCountForUser);
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

}

package com.netcracker.unc.newmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

//import com.netcracker.unc.modules.servlets.InvoiceServlet;
import com.netcracker.unc.newmvc.connection.ConnectionFactory;
import com.netcracker.unc.newmvc.dao.models.UserModel;
import com.netcracker.unc.newmvc.ejb.models.InvoiceModel;
import com.netcracker.unc.newmvc.ejb.queries.InvoiceQueries;

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
	
	public int getSumBalance(UserModel user) {
		setUser(user);
		return getSumBalance();
	}
	
	

	public ArrayList<InvoiceModel> getAllInvoice() {

		InvoiceModel invoice;
		Connection connect = ConnectionFactory.getConnection();
		ArrayList<InvoiceModel> listGetAllInvoice = new ArrayList<InvoiceModel>();
		try {
			PreparedStatement prepare = connect.prepareStatement(InvoiceQueries.getAllInvoicesByUserId);
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
			prepare = connect.prepareStatement(InvoiceQueries.setInvoiceName, new String[] { "FIN_OBJECT_ID" });
			prepare.setString(1, invoiceJsp.getInvoiceName());
			prepare.setInt(2, user.getUserId());			
			if (prepare.executeUpdate()>0) {

				ResultSet rs = prepare.getGeneratedKeys();
				rs.next();
				auto_id = rs.getLong(1);
				log.warning("New ID: " + auto_id);

				prepare = connect.prepareStatement(InvoiceQueries.setBalance);
				prepare.setLong(1, auto_id);
				prepare.setInt(2, invoiceJsp.getBalance());
				prepare.executeUpdate();

				prepare = connect.prepareStatement(InvoiceQueries.setCredit);
				prepare.setLong(1, auto_id);
				prepare.setString(2, String.valueOf(invoiceJsp.isCredit()));
				prepare.executeUpdate();

				prepare = connect.prepareStatement(InvoiceQueries.setPercent);
				prepare.setLong(1, auto_id);
				prepare.setInt(2, invoiceJsp.getPercent());
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

	public InvoiceModel getInvoice(int invoiceId, UserModel user) {
		PreparedStatement prepare;
		ResultSet result;
		Connection connect = ConnectionFactory.getConnection();
		InvoiceModel invoice = new InvoiceModel();
		invoice.setInvoiceId(invoiceId);
		Logger log = Logger.getLogger(InvoiceDAO.class.getName());

		try {
			prepare = connect.prepareStatement(InvoiceQueries.getInvoiceName);
			prepare.setInt(1, user.getUserId());
			prepare.setInt(2, invoiceId);
			result = prepare.executeQuery();
			result.next();

			invoice.setInvoiceName(result.getString(1));
			log.warning("New invoice: " + invoice.getInvoiceName());
			log.warning("New balance1: " + invoice.getBalance());

			prepare = connect.prepareStatement(InvoiceQueries.getBalanceCreditAndPercent);
			prepare.setInt(1, user.getUserId());
			prepare.setInt(2, invoiceId);
			result = prepare.executeQuery();
			result.next();

			invoice.setBalance(result.getInt(1));
			invoice.setCredit(Boolean.parseBoolean(result.getString(2)));
			invoice.setPercent(result.getInt(3));
			
			log.warning("New balance after query: " + invoice.getBalance());

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
		
		public InvoiceModel getInvoice(int invoiceId, int userId) {
			PreparedStatement prepare;
			ResultSet result;
			Connection connect = ConnectionFactory.getConnection();
			InvoiceModel invoice = new InvoiceModel();
			invoice.setInvoiceId(invoiceId);
			Logger log = Logger.getLogger(InvoiceDAO.class.getName());
			log.warning("UserID: " + userId + "/InvoiceID " + invoiceId);

			try {
				prepare = connect.prepareStatement(InvoiceQueries.getInvoiceName);
				prepare.setInt(1, userId);
				prepare.setInt(2, invoiceId);
				result = prepare.executeQuery();
				result.next();

				invoice.setInvoiceName(result.getString(1));
				log.warning("New invoice: " + invoice.getInvoiceName());
				log.warning("New balance1: " + invoice.getBalance());

				prepare = connect.prepareStatement(InvoiceQueries.getBalanceCreditAndPercent);
				prepare.setInt(1, userId);
				prepare.setInt(2, invoiceId);
				result = prepare.executeQuery();
				result.next();

				invoice.setBalance(result.getInt(1));
				invoice.setCredit(Boolean.parseBoolean(result.getString(2)));
				invoice.setPercent(result.getInt(3));
				
				log.warning("New balance after query: " + invoice.getBalance());

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
			prepare = connect.prepareStatement(InvoiceQueries.updateInvoise);
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
			prepare = connect.prepareStatement(InvoiceQueries.deleteParamInvoice);
			prepare.setInt(1, invoiceId);
			prepare.executeUpdate();
			
			log.warning("params deleted");
			
			prepare = connect.prepareStatement(InvoiceQueries.deleteInvoiceName);
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
			prepare = connect.prepareStatement(InvoiceQueries.getSumAllBalancesByUserId);
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
			prepare = connect.prepareStatement(InvoiceQueries.updateBalance);
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
	
	public int getCountInvoices(UserModel user) {
		setUser(user);
		PreparedStatement prepare;
		ResultSet result;
		Connection connect = ConnectionFactory.getConnection();
		/*InvoiceModel invoice = new InvoiceModel();
		invoice.setInvoiceId(invoiceId);*/

		try {

			prepare = connect.prepareStatement(InvoiceQueries.getInvoicesCount);
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

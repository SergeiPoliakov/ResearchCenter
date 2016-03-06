package com.netcracker.unc.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.netcracker.unc.mvc.*;
import com.netcracker.unc.mvc.connection.ConnectionFactory;
import com.netcracker.unc.mvc.models.UserModel;
import com.netcracker.unc.mvc.models.InvoiceModel;

public class InvoiceDAO {
	
	UserModel user;
	
	
	public List<InvoiceModel> getAllInvoice() {
		
		InvoiceModel invoice;
		Connection connect = ConnectionFactory.getConnection();
		List<InvoiceModel> listGetAllInvoice = new ArrayList<InvoiceModel>();
		try {
			PreparedStatement prepare = connect.prepareStatement(SQLQuery.get_all_invoices_for_user_by_user_id);
			prepare.setInt(1, user.get_user_id());
			ResultSet result = prepare.executeQuery();

			while (result.next()) {
				invoice = new InvoiceModel();
				invoice.setInvoiceId(result.getInt(1));
				invoice.setInvoiceName(result.getString(2));
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

	public void addInvoice(Object object) {
		PreparedStatement prepare;
		Connection connect = ConnectionFactory.getConnection();
		InvoiceModel invoice = (InvoiceModel) object;

		try {
			prepare = connect.prepareStatement(SQLQuery.set_new_invoice_for_user);
			prepare.setInt(4,user.get_user_id());
			prepare.setInt(1, invoice.getInvoiceId());
			prepare.setString(2, invoice.getInvoiceName());
			prepare.setInt(3, invoice.getObjectTypeId());
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

	public Object getInvoiceById(int invoiceId, UserModel object) {
		PreparedStatement prepare;
		ResultSet result;
		Connection connect = ConnectionFactory.getConnection();
		InvoiceModel invoice = new InvoiceModel();
		invoice.setInvoiceId(invoiceId);
		user = object;
		
		
		try {
			prepare = connect.prepareStatement(SQLQuery.get_invoice_for_user_by_invoice_id);
			prepare.setInt(1, user.get_user_id());
			prepare.setInt(2, invoiceId);
			result = prepare.executeQuery();
			result.next();


			invoice.setInvoiceName(result.getString(2));
			
			
			prepare = connect.prepareStatement(SQLQuery.get_balance_credit_and_percent_by_invoice_id);
			prepare.setInt(1, user.get_user_id());
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

	public void updateInvoice(Object object) {
		PreparedStatement prepare;
		Connection connect = ConnectionFactory.getConnection();
		InvoiceModel invoice = (InvoiceModel) object;

		try {
			prepare = connect.prepareStatement(SQLQuery.update_invoise_by_id);
			prepare.setString(1, invoice.getInvoiceName());
			prepare.setInt(2,user.get_user_id());
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
		

	public void deleteInvoice(Object object) {
		PreparedStatement prepare;
		Connection connect = ConnectionFactory.getConnection();
		InvoiceModel invoice = (InvoiceModel) object;

		try {
			prepare = connect.prepareStatement(SQLQuery.delete_invoice_by_id);
			prepare.setInt(1, invoice.getInvoiceId());
			prepare.setInt(2, user.get_user_id());
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
	
	public int getSumBalance() {
		PreparedStatement prepare;
		ResultSet result;
		Connection connect = ConnectionFactory.getConnection();

		try {
			prepare = connect.prepareStatement(SQLQuery.get_sum_all_balances_for_users_by_user_id);
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
	
	public void updateBalance(Object object) {
		PreparedStatement prepare;
		Connection connect = ConnectionFactory.getConnection();
		InvoiceModel invoice = (InvoiceModel) object;

		try {
			prepare = connect.prepareStatement(SQLQuery.update_balance_in_invoice);
			prepare.setInt(1, invoice.getBalance());
			prepare.setInt(2,invoice.getInvoiceId());
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
}
	


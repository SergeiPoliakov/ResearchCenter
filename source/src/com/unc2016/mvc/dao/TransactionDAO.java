package com.unc2016.mvc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.unc2016.mvc.SQLQuery;
import com.unc2016.mvc.models.TransactionModel;

public class TransactionDAO extends ObjectDAO {

	private PreparedStatement prepare = null;
	private TransactionModel trans = null;
	private ResultSet result = null;

	@Override
	public void addObject(Object object) {
		trans = (TransactionModel) object;

		try {
			prepare = connect.prepareStatement(SQLQuery.TRANSACTIONS_INSERT);
			prepare.setString(1, trans.get_transaction_date());
			prepare.setInt(2, trans.get_fin_object_id());
			prepare.setInt(3, trans.get_cost());
			prepare.setInt(4, trans.get_user_id());
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object getObject(Object object) {
		trans = (TransactionModel) object;

		try {
			prepare = connect.prepareStatement(SQLQuery.TRANSACTIONS_GET_BY_ID);
			prepare.setInt(1, trans.get_transaction_id());
			result = prepare.executeQuery();
			result.next();

			trans.set_transaction_id(result.getInt(1));
			trans.set_transaction_date(result.getString(2));
			trans.set_fin_object_id(result.getInt(3));
			trans.set_cost(result.getInt(4));
			trans.set_user_id(result.getInt(5));
			return trans;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void updateObject(Object object) {
		trans = (TransactionModel) object;

		try {
			prepare = connect.prepareStatement(SQLQuery.TRANSACTIONS_UPDATE_BY_ID);
			prepare.setString(1, trans.get_transaction_date());
			prepare.setInt(2, trans.get_fin_object_id());
			prepare.setInt(3, trans.get_cost());
			prepare.setInt(4, trans.get_user_id());
			prepare.setInt(5, trans.get_transaction_id());
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteObject(Object object) {
		trans = (TransactionModel) object;

		try {
			prepare = connect.prepareStatement(SQLQuery.TRANSACTIONS_DELETE_BY_ID);
			prepare.setInt(1, trans.get_transaction_id());
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Object> getAllObjectsDB() {
		try {
			prepare = connect.prepareStatement(SQLQuery.PJ_USERS_VIEW_ALL);
			result = prepare.executeQuery();
			List<Object> list = new ArrayList<Object>();
			trans = new TransactionModel();

			while (result.next()) {
				trans.set_transaction_id(result.getInt(1));
				trans.set_transaction_date(result.getString(2));
				trans.set_fin_object_id(result.getInt(3));
				trans.set_cost(result.getInt(4));
				trans.set_user_id(result.getInt(5));
				list.add(trans);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

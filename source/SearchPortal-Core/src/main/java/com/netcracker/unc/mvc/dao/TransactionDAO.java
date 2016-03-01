package com.netcracker.unc.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.netcracker.unc.mvc.SQLQuery;
import com.netcracker.unc.mvc.connection.ConnectionFactory;
import com.netcracker.unc.mvc.models.TransactionModel;

public class TransactionDAO extends ObjectDAO {

    private PreparedStatement prepare = null;
    private TransactionModel trans = null;
    private ResultSet result = null;
    private Connection connect = null;

    @Override
    public void addObject(Object object) {
        connect = ConnectionFactory.getConnection();
        trans = (TransactionModel) object;

        try {
            prepare = connect.prepareStatement(SQLQuery.SP_TRANSACTIONS_INSERT);
            prepare.setString(1, trans.getTransactionDate());
            prepare.setInt(2, trans.getFinObjectId());
            prepare.setInt(3, trans.getCost());
            prepare.setInt(4, trans.getUserId());
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

    @Override
    public Object getObject(Object object) {
        connect = ConnectionFactory.getConnection();
        trans = (TransactionModel) object;

        try {
            prepare = connect.prepareStatement(SQLQuery.SP_TRANSACTIONS_GET_BY_ID);
            prepare.setInt(1, trans.getTransactionId());
            result = prepare.executeQuery();
            result.next();

            trans.setTransactionId(result.getInt(1));
            trans.setTransactionDate(result.getString(2));
            trans.setFinObjectId(result.getInt(3));
            trans.setCost(result.getInt(4));
            trans.setUserId(result.getInt(5));
            return trans;
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

    @Override
    public void updateObject(Object object) {
        connect = ConnectionFactory.getConnection();
        trans = (TransactionModel) object;

        try {
            prepare = connect.prepareStatement(SQLQuery.SP_TRANSACTIONS_UPDATE_BY_ID);
            prepare.setString(1, trans.getTransactionDate());
            prepare.setInt(2, trans.getFinObjectId());
            prepare.setInt(3, trans.getCost());
            prepare.setInt(4, trans.getUserId());
            prepare.setInt(5, trans.getTransactionId());
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

    @Override
    public void deleteObject(Object object) {
        connect = ConnectionFactory.getConnection();
        trans = (TransactionModel) object;

        try {
            prepare = connect.prepareStatement(SQLQuery.SP_TRANSACTIONS_DELETE_BY_ID);
            prepare.setInt(1, trans.getTransactionId());
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

    @Override
    public List<Object> getAllObjectsDB() {
        try {
            connect = ConnectionFactory.getConnection();
            prepare = connect.prepareStatement(SQLQuery.SP_USERS_VIEW_ALL);
            result = prepare.executeQuery();
            List<Object> list = new ArrayList<Object>();

            while (result.next()) {
                trans = new TransactionModel();
                trans.setTransactionId(result.getInt(1));
                trans.setTransactionDate(result.getString(2));
                trans.setFinObjectId(result.getInt(3));
                trans.setCost(result.getInt(4));
                trans.setUserId(result.getInt(5));
                list.add(trans);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

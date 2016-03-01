package com.netcracker.unc.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.netcracker.unc.mvc.SQLQuery;
import com.netcracker.unc.mvc.connection.ConnectionFactory;
import com.netcracker.unc.mvc.models.UserModel;

public class UserDAO extends ObjectDAO {

    private PreparedStatement prepare = null;
    private UserModel user = null;
    private ResultSet result = null;
    private Connection connect = null;

    @Override
    public void addObject(Object object) {
        connect = ConnectionFactory.getConnection();
        user = (UserModel) object;

        try {
            prepare = connect.prepareStatement(SQLQuery.SP_USERS_INSERT);
            prepare.setString(1, user.getLogin());
            prepare.setInt(2, user.getHashSum());
            prepare.setString(3, user.getName());
            prepare.setString(4, user.getAccountType());
            prepare.setInt(5, user.getSalt());
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

    // this method can choose input parameter between user_id and login (check
    // which not empty)
    @Override
    public Object getObject(Object object) {
        connect = ConnectionFactory.getConnection();
        user = (UserModel) object;

        try {
            // if we have not user with correct id
            if (user.getUserId() == 0) {
                prepare = connect.prepareStatement(SQLQuery.SP_USERS_GET_BY_LOGIN);
                prepare.setString(1, user.getLogin());
            }
            // if user have correct id
            else {
                prepare = connect.prepareStatement(SQLQuery.SP_USERS_GET_BY_ID);
                prepare.setInt(1, user.getUserId());
            }
            result = prepare.executeQuery();
            result.next();

            // create current user from database
            user.setUserId(result.getInt(1));
            user.setLogin(result.getString(2));
            user.setHashSum(result.getInt(3));
            user.setName(result.getString(4));
            user.setAccountType(result.getString(5));
            user.setSalt(result.getInt(6));
            return user;
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
        user = (UserModel) object;

        try {
            prepare = connect.prepareStatement(SQLQuery.SP_USERS_UPDATE_BY_ID);
            prepare.setString(1, user.getLogin());
            prepare.setInt(2, user.getHashSum());
            prepare.setString(3, user.getName());
            prepare.setString(4, user.getAccountType());
            prepare.setInt(5, user.getSalt());
            prepare.setInt(6, user.getUserId());
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
        user = (UserModel) object;
        try {
            prepare = connect.prepareStatement(SQLQuery.SP_USERS_DELETE_BY_ID);
            prepare.setInt(1, user.getUserId());
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
                user = new UserModel();
                user.setUserId(result.getInt(1));
                user.setLogin(result.getString(2));
                user.setHashSum(result.getInt(3));
                user.setName(result.getString(4));
                user.setAccountType(result.getString(5));
                user.setSalt(result.getInt(6));
                list.add(user);
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

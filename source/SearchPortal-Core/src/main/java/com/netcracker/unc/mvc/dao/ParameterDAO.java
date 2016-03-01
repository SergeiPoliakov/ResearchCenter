package com.netcracker.unc.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.netcracker.unc.mvc.SQLQuery;
import com.netcracker.unc.mvc.connection.ConnectionFactory;
import com.netcracker.unc.mvc.models.ParameterModel;

public class ParameterDAO extends ObjectDAO {

    private PreparedStatement prepare = null;
    private ParameterModel param = null;
    private ResultSet result = null;
    private Connection connect = null;

    @Override
    public void addObject(Object object) {
        connect = ConnectionFactory.getConnection();
        param = (ParameterModel) object;

        try {
            prepare = connect.prepareStatement(SQLQuery.SP_PARAMS_INSERT);
            if (param.getValue() != null) {
                prepare.setString(1, param.getValue());
            } else {
                prepare.setNull(1, java.sql.Types.VARCHAR);
            }
            if (param.getValueDate() != null) {
                prepare.setDate(2, param.getValueDate());
            } else {
                prepare.setNull(2, java.sql.Types.DATE);
            }
            prepare.setInt(3, param.getFinObjectId());
            prepare.setInt(4, param.getAttributeId());
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

    // this method can choose input parameter between fin_object_id and
    // attribute_id (check which not empty)
    @Override
    public Object getObject(Object object) {
        connect = ConnectionFactory.getConnection();
        param = (ParameterModel) object;

        try {
            // if we have not parameter with correct attribute id
            if (param.getAttributeId() == 0) {
                prepare = connect.prepareStatement(SQLQuery.SP_PARAMS_GET_BY_OBJECT_ID);
                prepare.setInt(1, param.getFinObjectId());
            }
            // if we have not parameter with correct object id
            else {
                prepare = connect.prepareStatement(SQLQuery.SP_PARAMS_GET_BY_ATTRIBUTE_ID);
                prepare.setInt(1, param.getAttributeId());
            }
            result = prepare.executeQuery();
            result.next();

            // create current parameter from database
            param.setValue(result.getString(1));
            param.setValueDate(result.getString(2));
            param.setFinObjectId(result.getInt(3));
            param.setAttributeId(result.getInt(4));
            return param;
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
        param = (ParameterModel) object;

        try {
            prepare = connect.prepareStatement(SQLQuery.SP_PARAMS_UPDATE_BY_OBJECT_ID);
            prepare.setString(1, param.getValue());
            prepare.setDate(2, param.getValueDate());
            prepare.setInt(3, param.getAttributeId());
            prepare.setInt(4, param.getFinObjectId());
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

    // this method can choose input parameter between fin_object_id and
    // attribute_id (check which not empty)
    @Override
    public void deleteObject(Object object) {
        connect = ConnectionFactory.getConnection();
        param = (ParameterModel) object;
        try {
            // if we have not parameter with correct attribute id
            if (param.getAttributeId() == 0) {
                prepare = connect.prepareStatement(SQLQuery.SP_PARAMS_DELETE_BY_OBJECT_ID);
                prepare.setInt(1, param.getFinObjectId());
            }
            // if we have not parameter with correct object id
            else {
                prepare = connect.prepareStatement(SQLQuery.SP_PARAMS_DELETE_BY_ATTRIBUTE_ID);
                prepare.setInt(1, param.getAttributeId());
            }
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
            prepare = connect.prepareStatement(SQLQuery.SP_PARAMS_VIEW_ALL);
            result = prepare.executeQuery();
            List<Object> list = new ArrayList<Object>();

            while (result.next()) {
                param = new ParameterModel();
                param.setValue(result.getString(1));
                param.setValueDate(result.getString(2));
                param.setFinObjectId(result.getInt(3));
                param.setAttributeId(result.getInt(4));
                list.add(param);
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

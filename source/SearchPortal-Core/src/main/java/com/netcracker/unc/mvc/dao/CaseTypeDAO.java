package com.netcracker.unc.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.netcracker.unc.mvc.SQLQuery;
import com.netcracker.unc.mvc.connection.ConnectionFactory;
import com.netcracker.unc.mvc.models.CaseTypeModel;

public class CaseTypeDAO extends ObjectDAO {

    private PreparedStatement prepare = null;
    private CaseTypeModel type = null;
    private ResultSet result = null;
    private Connection connect = null;

    @Override
    public void addObject(Object object) {
        connect = ConnectionFactory.getConnection();
        type = (CaseTypeModel) object;

        try {
            prepare = connect.prepareStatement(SQLQuery.SP_FIN_OBJECT_TYPES_INSERT);
            prepare.setString(1, type.getFinObjectTypeName());
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

    // this method can choose input parameter between fin_object_type_id and
    // fin_object_type_name (check which not empty)
    @Override
    public Object getObject(Object object) {
        connect = ConnectionFactory.getConnection();
        type = (CaseTypeModel) object;

        try {
            // if we have not case type with correct id
            if (type.getFinObjectTypeId() == 0) {
                prepare = connect.prepareStatement(SQLQuery.SP_FIN_OBJECT_TYPES_GET_BY_NAME);
                prepare.setString(1, type.getFinObjectTypeName());
            }
            // if case type have correct id
            else {
                prepare = connect.prepareStatement(SQLQuery.SP_FIN_OBJECT_TYPES_GET_BY_ID);
                prepare.setInt(1, type.getFinObjectTypeId());
            }
            result = prepare.executeQuery();
            result.next();

            // create current case type from database
            type.setFinObjectTypeId(result.getInt(1));
            type.setFinObjectTypeName(result.getString(2));
            return type;
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
        type = (CaseTypeModel) object;

        try {
            prepare = connect.prepareStatement(SQLQuery.SP_FIN_OBJECT_TYPES_UPDATE_BY_ID);
            prepare.setString(1, type.getFinObjectTypeName());
            prepare.setInt(2, type.getFinObjectTypeId());
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
        type = (CaseTypeModel) object;

        try {
            prepare = connect.prepareStatement(SQLQuery.SP_FIN_OBJECT_TYPES_DELETE_BY_ID);
            prepare.setInt(1, type.getFinObjectTypeId());
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
            prepare = connect.prepareStatement(SQLQuery.SP_FIN_OBJECT_TYPES_VIEW_ALL);
            result = prepare.executeQuery();
            List<Object> list = new ArrayList<Object>();

            while (result.next()) {
                type = new CaseTypeModel();
                type.setFinObjectTypeId(result.getInt(1));
                type.setFinObjectTypeName(result.getString(2));
                list.add(type);
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

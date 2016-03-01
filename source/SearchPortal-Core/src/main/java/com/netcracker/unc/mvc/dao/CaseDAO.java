package com.netcracker.unc.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.netcracker.unc.mvc.SQLQuery;
import com.netcracker.unc.mvc.connection.ConnectionFactory;
import com.netcracker.unc.mvc.models.CaseModel;

public class CaseDAO extends ObjectDAO {

    // private Connection connect = null;
    private PreparedStatement prepare = null;
    private CaseModel casee = null;
    private ResultSet result = null;
    private Connection connect = null;

    @Override
    public void addObject(Object object) {
        connect = ConnectionFactory.getConnection();
        casee = (CaseModel) object;

        try {
            prepare = connect.prepareStatement(SQLQuery.SP_FIN_OBJECTS_INSERT);
            if (casee.getParentId() != null) {
                prepare.setInt(1, casee.getParentId());
            } else {
                prepare.setNull(1, java.sql.Types.VARCHAR);
            }
            prepare.setString(2, casee.getObjectName());
            prepare.setInt(3, casee.getFinObjectTypeId());
            prepare.setInt(4, casee.getUserId());
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
        casee = (CaseModel) object;

        try {
            if (casee.getFinObjectId() != 0) {
                prepare = connect.prepareStatement(SQLQuery.SP_FIN_OBJECTS_GET_BY_ID);
                prepare.setInt(1, casee.getFinObjectId());
            } else {
                prepare = connect.prepareStatement(SQLQuery.SP_FIN_OBJECTS_GET_LAST_BY_NAME);
                prepare.setString(1, casee.getObjectName());
            }
            result = prepare.executeQuery();
            result.next();

            // create current case from database
            casee.setFinObjectId(result.getInt(1));
            casee.setParentId(result.getInt(2));
            casee.setObjectName(result.getString(3));
            casee.setFinObjectTypeId(result.getInt(4));
            casee.setUserId(result.getInt(5));
            return casee;
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
        casee = (CaseModel) object;

        try {
            prepare = connect.prepareStatement(SQLQuery.SP_FIN_OBJECTS_UPDATE_BY_ID);
            prepare.setInt(1, casee.getParentId());
            prepare.setString(2, casee.getObjectName());
            prepare.setInt(3, casee.getFinObjectTypeId());
            prepare.setInt(4, casee.getUserId());
            prepare.setInt(5, casee.getFinObjectId());
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
        casee = (CaseModel) object;

        try {
            prepare = connect.prepareStatement(SQLQuery.SP_FIN_OBJECTS_DELETE_BY_ID);
            prepare.setInt(1, casee.getFinObjectId());
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
            prepare = connect.prepareStatement(SQLQuery.SP_FIN_OBJECTS_VIEW_ALL);
            result = prepare.executeQuery();
            List<Object> list = new ArrayList<Object>();

            while (result.next()) {
                casee = new CaseModel();
                casee.setFinObjectId(result.getInt(1));
                casee.setParentId(result.getInt(2));
                casee.setObjectName(result.getString(3));
                casee.setFinObjectTypeId(result.getInt(4));
                casee.setUserId(result.getInt(5));
                list.add(casee);
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

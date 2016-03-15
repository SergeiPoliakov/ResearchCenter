package com.netcracker.unc.newmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.netcracker.unc.newmvc.connection.ConnectionFactory;
import com.netcracker.unc.newmvc.dao.models.ObjectModel;
import com.netcracker.unc.newmvc.dao.queries.ObjectQueries;

public class ObjectDAO {

	public void addObject(ObjectModel object) {
		Connection connect = ConnectionFactory.getConnection();
		try {
			PreparedStatement prepare = connect.prepareStatement(ObjectQueries.SP_FIN_OBJECTS_ADD_OBJECT);
			if (object.getParentId() != 0)
				prepare.setInt(1, object.getParentId());
			else
				prepare.setNull(1, java.sql.Types.VARCHAR);
			prepare.setString(2, object.getObjectName());
			prepare.setInt(3, object.getFinObjectTypeId());
			prepare.setInt(4, object.getUserId());
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

	public void updateObject(ObjectModel object) {
		Connection connect = ConnectionFactory.getConnection();

		try {
			PreparedStatement prepare = connect.prepareStatement(ObjectQueries.SP_FIN_OBJECTS_UPDATE_BY_ID);
			prepare.setInt(1, object.getParentId());
			prepare.setString(2, object.getObjectName());
			prepare.setInt(3, object.getFinObjectTypeId());
			prepare.setInt(4, object.getUserId());
			prepare.setInt(5, object.getFinObjectId());
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

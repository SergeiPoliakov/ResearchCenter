package com.netcracker.unc.newmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.netcracker.unc.mvc.connection.ConnectionFactory;

public class ParamDAO {

	public void addParam(ParamModel param) {
		Connection connect = ConnectionFactory.getConnection();

		try {
			PreparedStatement prepare = connect.prepareStatement(ParamQueries.SP_PARAMS_ADD);
			if (param.getValue() != null)
				prepare.setString(1, param.getValue());
			else
				prepare.setNull(1, java.sql.Types.VARCHAR);
			if (param.getValueDate() != null)
				prepare.setDate(2, param.getValueDate());
			else
				prepare.setNull(2, java.sql.Types.DATE);
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

	public void updateParam(ParamModel param) {
		Connection connect = ConnectionFactory.getConnection();

		try {
			PreparedStatement prepare = connect
					.prepareStatement(ParamQueries.SP_PARAMS_UPDATE_BY_ATTRIBUTE_ID_AND_OBJECT_ID);
			if (param.getValue() != null)
				prepare.setString(1, param.getValue());
			else
				prepare.setNull(1, java.sql.Types.VARCHAR);
			if (param.getValueDate() != null)
				prepare.setDate(2, param.getValueDate());
			else
				prepare.setNull(2, java.sql.Types.DATE);
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
}

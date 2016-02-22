package com.netcracker.unc.mvc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.netcracker.unc.mvc.SQLQuery;
import com.netcracker.unc.mvc.models.CaseTypeModel;

public class CaseTypeDAO extends ObjectDAO {

	private PreparedStatement prepare = null;
	private CaseTypeModel type = null;
	private ResultSet result = null;

	@Override
	public void addObject(Object object) {
		type = (CaseTypeModel) object;

		try {
			prepare = connect.prepareStatement(SQLQuery.SP_USERS_INSERT);
			prepare.setString(1, type.get_fin_object_type_name());
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// this method can choose input parameter between fin_object_type_id and
	// fin_object_type_name (check which not empty)
	@Override
	public Object getObject(Object object) {
		type = (CaseTypeModel) object;

		try {
			// if we have not case type with correct id
			if (type.get_fin_object_type_id() == 0) {
				prepare = connect.prepareStatement(SQLQuery.SP_FIN_OBJECT_TYPES_GET_BY_NAME);
				prepare.setString(1, type.get_fin_object_type_name());
			}
			// if case type have correct id
			else {
				prepare = connect.prepareStatement(SQLQuery.SP_FIN_OBJECT_TYPES_GET_BY_ID);
				prepare.setInt(1, type.get_fin_object_type_id());
			}
			result = prepare.executeQuery();
			result.next();

			// create current case type from database
			type.set_fin_object_type_id(result.getInt(1));
			type.set_fin_object_type_name(result.getString(2));
			return type;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void updateObject(Object object) {
		type = (CaseTypeModel) object;

		try {
			prepare = connect.prepareStatement(SQLQuery.SP_FIN_OBJECT_TYPES_UPDATE_BY_ID);
			prepare.setString(1, type.get_fin_object_type_name());
			prepare.setInt(2, type.get_fin_object_type_id());
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteObject(Object object) {
		type = (CaseTypeModel) object;

		try {
			prepare = connect.prepareStatement(SQLQuery.SP_FIN_OBJECT_TYPES_DELETE_BY_ID);
			prepare.setInt(1, type.get_fin_object_type_id());
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Object> getAllObjectsDB() {
		try {
			prepare = connect.prepareStatement(SQLQuery.SP_FIN_OBJECT_TYPES_VIEW_ALL);
			result = prepare.executeQuery();
			List<Object> list = new ArrayList<Object>();

			while (result.next()) {
				type = new CaseTypeModel();
				type.set_fin_object_type_id(result.getInt(1));
				type.set_fin_object_type_name(result.getString(2));
				list.add(type);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}

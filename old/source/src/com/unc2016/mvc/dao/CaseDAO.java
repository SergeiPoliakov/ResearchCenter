package com.unc2016.mvc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.unc2016.mvc.SQLQuery;
import com.unc2016.mvc.models.CaseModel;

public class CaseDAO extends ObjectDAO {

	//private Connection connect = null;
	private PreparedStatement prepare = null;
	private CaseModel casee = null;
	private ResultSet result = null;

	@Override
	public void addObject(Object object) {
		casee = (CaseModel) object;

		try {
			prepare = connect.prepareStatement(SQLQuery.FIN_OBJECTS_INSERT);
			prepare.setInt(1, casee.get_parent_id());
			prepare.setString(2, casee.get_object_name());
			prepare.setInt(3, casee.get_fin_object_type_id());
			prepare.setInt(4, casee.get_user_id());
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object getObject(Object object) {
		casee = (CaseModel) object;

		try {
			prepare = connect.prepareStatement(SQLQuery.FIN_OBJECTS_GET_BY_ID);
			prepare.setInt(1, casee.get_fin_object_id());
			result = prepare.executeQuery();
			result.next();

			// create current case from database
			casee.set_fin_object_id(result.getInt(1));
			casee.set_parent_id(result.getInt(2));
			casee.set_object_name(result.getString(3));
			casee.set_fin_object_type_id(result.getInt(4));
			casee.set_user_id(result.getInt(5));
			return casee;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void updateObject(Object object) {
		casee = (CaseModel) object;

		try {
			prepare = connect.prepareStatement(SQLQuery.FIN_OBJECTS_UPDATE_BY_ID);
			prepare.setInt(1, casee.get_parent_id());
			prepare.setString(2, casee.get_object_name());
			prepare.setInt(3, casee.get_fin_object_type_id());
			prepare.setInt(4, casee.get_user_id());
			prepare.setInt(5, casee.get_fin_object_id());
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteObject(Object object) {
		casee = (CaseModel) object;
		
		try {
			prepare = connect.prepareStatement(SQLQuery.FIN_OBJECTS_DELETE_BY_ID);
			prepare.setInt(1, casee.get_fin_object_id());
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Object> getAllObjectsDB() {
		try {
			prepare = connect.prepareStatement(SQLQuery.FIN_OBJECTS_VIEW_ALL);
			result = prepare.executeQuery();
			List<Object> list = new ArrayList<Object>();
			casee = new CaseModel();

			while (result.next()) {
				casee.set_fin_object_id(result.getInt(1));
				casee.set_parent_id(result.getInt(2));
				casee.set_object_name(result.getString(3));
				casee.set_fin_object_type_id(result.getInt(4));
				casee.set_user_id(result.getInt(5));
				list.add(casee);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}

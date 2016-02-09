package main.java.com.netcracker.unc.mvc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.com.netcracker.unc.mvc.SQLQuery;
import main.java.com.netcracker.unc.mvc.models.ParameterModel;

public class ParameterDAO extends ObjectDAO {

	private PreparedStatement prepare = null;
	private ParameterModel param = null;
	private ResultSet result = null;

	@Override
	public void addObject(Object object) {
		param = (ParameterModel) object;

		try {
			prepare = connect.prepareStatement(SQLQuery.PARAMS_INSERT);
			prepare.setString(1, param.get_value1());
			prepare.setDate(2, param.get_value_date());
			prepare.setInt(3, param.get_fin_object_id());
			prepare.setInt(4, param.get_attribute_id());
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// this method can choose input parameter between fin_object_id and
	// attribute_id (check which not empty)
	@Override
	public Object getObject(Object object) {
		param = (ParameterModel) object;

		try {
			// if we have not parameter with correct attribute id
			if (param.get_attribute_id() == 0) {
				prepare = connect.prepareStatement(SQLQuery.PARAMS_GET_BY_OBJECT_ID);
				prepare.setInt(1, param.get_fin_object_id());
			}
			// if we have not parameter with correct object id
			else {
				prepare = connect.prepareStatement(SQLQuery.PARAMS_GET_BY_ATTRIBUTE_ID);
				prepare.setInt(1, param.get_attribute_id());
			}
			result = prepare.executeQuery();
			result.next();

			// create current parameter from database
			param.set_value1(result.getString(1));
			param.set_value_date(result.getString(2));
			param.set_fin_object_id(result.getInt(3));
			param.set_attribute_id(result.getInt(4));
			return param;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void updateObject(Object object) {
		param = (ParameterModel) object;

		try {
			prepare = connect.prepareStatement(SQLQuery.PARAMS_UPDATE_BY_OBJECT_ID);
			prepare.setString(1, param.get_value1());
			prepare.setDate(2, param.get_value_date());
			prepare.setInt(3, param.get_attribute_id());
			prepare.setInt(4, param.get_fin_object_id());
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// this method can choose input parameter between fin_object_id and
	// attribute_id (check which not empty)
	@Override
	public void deleteObject(Object object) {
		param = (ParameterModel) object;
		try {
			// if we have not parameter with correct attribute id
			if (param.get_attribute_id() == 0) {
				prepare = connect.prepareStatement(SQLQuery.PARAMS_DELETE_BY_OBJECT_ID);
				prepare.setInt(1, param.get_fin_object_id());
			}
			// if we have not parameter with correct object id
			else {
				prepare = connect.prepareStatement(SQLQuery.PARAMS_DELETE_BY_ATTRIBUTE_ID);
				prepare.setInt(1, param.get_attribute_id());
			}
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Object> getAllObjectsDB() {
		try {
			prepare = connect.prepareStatement(SQLQuery.PARAMS_VIEW_ALL);
			result = prepare.executeQuery();
			List<Object> list = new ArrayList<Object>();

			while (result.next()) {
				param = new ParameterModel();
				param.set_value1(result.getString(1));
				param.set_value_date(result.getString(2));
				param.set_fin_object_id(result.getInt(3));
				param.set_attribute_id(result.getInt(4));
				list.add(param);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

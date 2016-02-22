package com.netcracker.unc.mvc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.netcracker.unc.mvc.SQLQuery;
import com.netcracker.unc.mvc.models.AttributeModel;

public class AttributeDAO extends ObjectDAO {

	private PreparedStatement prepare = null;
	private AttributeModel attribute = null;
	private ResultSet result = null;

	@Override
	public void addObject(Object object) {
		attribute = (AttributeModel) object;

		try {
			prepare = connect.prepareStatement(SQLQuery.SP_ATTRIBUTES_INSERT);
			prepare.setString(1, attribute.get_attribute_name());
			prepare.setInt(2, attribute.get_fin_object_type_id());
			prepare.setInt(1, attribute.get_attribute_id());
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// this method can choose input parameter between attribute_id and
	// attribute_name (check which not empty)
	@Override
	public Object getObject(Object object) {
		attribute = (AttributeModel) object;

		try {
			// if we have not attribute with correct id
			if (attribute.get_attribute_id() == 0) {
				prepare = connect.prepareStatement(SQLQuery.SP_ATTRIBUTES_GET_BY_NAME);
				prepare.setString(1, attribute.get_attribute_name());
			}
			// if attribute have correct id
			else {
				prepare = connect.prepareStatement(SQLQuery.SP_ATTRIBUTES_GET_BY_ID);
				prepare.setInt(1, attribute.get_attribute_id());
			}
			result = prepare.executeQuery();
			result.next();

			// create current attribute from database
			attribute.set_attribute_name(result.getString(1));
			attribute.set_fin_object_type_id(result.getInt(2));
			attribute.set_attribute_id(result.getInt(3));
			return attribute;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void updateObject(Object object) {
		attribute = (AttributeModel) object;

		try {
			prepare = connect.prepareStatement(SQLQuery.SP_ATTRIBUTES_UPDATE_BY_ID);
			prepare.setString(1, attribute.get_attribute_name());
			prepare.setInt(2, attribute.get_fin_object_type_id());
			prepare.setInt(3, attribute.get_attribute_id());
			prepare.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteObject(Object object) {
		attribute = (AttributeModel) object;

		try {
			prepare = connect.prepareStatement(SQLQuery.SP_ATTRIBUTES_DELETE_BY_ID);
			prepare.setInt(1, attribute.get_attribute_id());
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
				attribute = new AttributeModel();
				attribute.set_attribute_name(result.getString(1));
				attribute.set_fin_object_type_id(result.getInt(2));
				attribute.set_attribute_id(result.getInt(3));
				list.add(attribute);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

package com.netcracker.unc.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.netcracker.unc.mvc.SQLQuery;
import com.netcracker.unc.mvc.connection.ConnectionFactory;
import com.netcracker.unc.mvc.models.AttributeModel;

public class AttributeDAO extends ObjectDAO {

	private PreparedStatement prepare = null;
	private AttributeModel attribute = null;
	private ResultSet result = null;
	private Connection connect = null;

	@Override
	public void addObject(Object object) {
		connect = ConnectionFactory.getConnection();
		attribute = (AttributeModel) object;

		try {
			prepare = connect.prepareStatement(SQLQuery.SP_ATTRIBUTES_INSERT);
			prepare.setString(1, attribute.getAttributeName());
			prepare.setInt(2, attribute.getFinObjectTypeID());
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

	// this method can choose input parameter between attribute_id and
	// attribute_name (check which not empty)
	@Override
	public Object getObject(Object object) {
		connect = ConnectionFactory.getConnection();
		attribute = (AttributeModel) object;

		try {
			// if we have not attribute with correct id
			if (attribute.getAttributeID() == 0) {
				prepare = connect.prepareStatement(SQLQuery.SP_ATTRIBUTES_GET_BY_NAME);
				prepare.setString(1, attribute.getAttributeName());
			}
			// if attribute have correct id
			else {
				prepare = connect.prepareStatement(SQLQuery.SP_ATTRIBUTES_GET_BY_ID);
				prepare.setInt(1, attribute.getAttributeID());
			}
			result = prepare.executeQuery();
			result.next();

			// create current attribute from database
			attribute.setAttributeName(result.getString("ATTRIBUTE_NAME"));
			attribute.setFinObjectTypeID(result.getInt("FIN_OBJECT_TYPE_ID"));
			attribute.setAttributeID(result.getInt("ATTRIBUTE_ID"));
			return attribute;
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
		attribute = (AttributeModel) object;

		try {
			prepare = connect.prepareStatement(SQLQuery.SP_ATTRIBUTES_UPDATE_BY_ID);
			prepare.setString(1, attribute.getAttributeName());
			prepare.setInt(2, attribute.getFinObjectTypeID());
			prepare.setInt(3, attribute.getAttributeID());
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
		attribute = (AttributeModel) object;

		try {
			prepare = connect.prepareStatement(SQLQuery.SP_ATTRIBUTES_DELETE_BY_ID);
			prepare.setInt(1, attribute.getAttributeID());
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
			prepare = connect.prepareStatement(SQLQuery.SP_ATTRIBUTES_VIEW_ALL);
			result = prepare.executeQuery();
			List<Object> list = new ArrayList<Object>();

			while (result.next()) {
				attribute = new AttributeModel();
				attribute.setAttributeName(result.getString(1));
				attribute.setFinObjectTypeID(result.getInt(2));
				attribute.setAttributeID(result.getInt(3));
				list.add(attribute);
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

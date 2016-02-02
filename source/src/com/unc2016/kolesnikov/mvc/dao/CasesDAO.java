package com.unc2016.kolesnikov.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.unc2016.kolesnikov.mvc.CasesPOJO;
import com.unc2016.kolesnikov.mvc.ConnectionFactory;

/**
 * data access to fin_objects table
 * 
 * @author Kolesnikov
 *
 */
public class CasesDAO implements ObjectsDAO {

	private Connection connect = null;
	private PreparedStatement prepare = null;
	private CasesPOJO casee = null;
	private ResultSet result = null;

	public CasesDAO() {
		connect = ConnectionFactory.getConnection();
	}

	@Override
	public List<SiteOfPrioritiesObjects> getAllObjectsDB() {
		return null;
	}

	@Override
	public void addObject(SiteOfPrioritiesObjects object) {
		casee = (CasesPOJO) object;
		boolean check = false; // for check duplicate case
		try {
			prepare = connect.prepareStatement(SQL_Queries.FIN_OBJECT_TYPES_SQL_VIEW);
			result = prepare.executeQuery();
			while (result.next()) {
				if (result.getString(2).equals(casee.get_Fin_object_type_name())) {
					check = true;
				}
			}

			// if not duplicate object type
			if (!check) {
				prepare = connect.prepareStatement(SQL_Queries.FIN_OBJECT_TYPES_SQL_INSERT);
				prepare.setString(1, casee.get_Fin_object_type_name());
				prepare.executeUpdate();
				check = false;
			}

			// add new case
			prepare = connect.prepareStatement(SQL_Queries.FIN_OBJECTS_SQL_INSERT);
			prepare.setInt(1, casee.get_Parent_id());
			prepare.setString(2, casee.get_Object_name());
			prepare.setInt(3, casee.get_Fin_object_type_id());
			prepare.setInt(4, casee.get_User_id());
			prepare.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public SiteOfPrioritiesObjects getObject(SiteOfPrioritiesObjects object) {
		return null;
	}

	@Override
	public void updateObject(SiteOfPrioritiesObjects object) {

	}

	@Override
	public void deleteObject(SiteOfPrioritiesObjects object) {

	}

}

package com.unc2016.kolesnikov.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.unc2016.kolesnikov.mvc.CasesTypesPOJO;
import com.unc2016.kolesnikov.mvc.ConnectionFactory;

public class CasesTypesDAO implements ObjectsDAO {

	private Connection connect = null;
	private PreparedStatement prepare = null;
	private CasesTypesPOJO caseType = null;
	private ResultSet result = null;

	public CasesTypesDAO() {
		connect = ConnectionFactory.getConnection();
	}

	@Override
	public List<SiteOfPrioritiesObjects> getAllObjectsDB() {

		List<SiteOfPrioritiesObjects> list = new ArrayList<SiteOfPrioritiesObjects>();
		try {
			prepare = connect.prepareStatement(SQL_Queries.FIN_OBJECT_TYPES_SQL_VIEW);
			result = prepare.executeQuery();
			while (result.next()) {
				caseType = new CasesTypesPOJO();
				caseType.set_fin_object_type_id(result.getInt(1));
				caseType.set_fin_object_type_name(result.getString(2));
				list.add(caseType);
			}
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void addObject(SiteOfPrioritiesObjects object) {
		// TODO Auto-generated method stub

	}

	@Override
	public SiteOfPrioritiesObjects getObject(SiteOfPrioritiesObjects object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateObject(SiteOfPrioritiesObjects object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteObject(SiteOfPrioritiesObjects object) {
		// TODO Auto-generated method stub

	}

}

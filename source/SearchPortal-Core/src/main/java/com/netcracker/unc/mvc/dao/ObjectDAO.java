package main.java.com.netcracker.unc.mvc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import main.java.com.netcracker.unc.mvc.connection.ConnectionFactory;

public abstract class ObjectDAO {

	protected Connection connect = null;

	public ObjectDAO() {
		connect = ConnectionFactory.getConnection();
	}

	public abstract List<Object> getAllObjectsDB();

	public abstract void addObject(Object object);

	public abstract Object getObject(Object object);

	public abstract void updateObject(Object object);

	public abstract void deleteObject(Object object);

	public void connectionClose() {
		try {
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

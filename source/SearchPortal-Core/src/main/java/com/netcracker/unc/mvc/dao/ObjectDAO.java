package com.netcracker.unc.mvc.dao;

import java.sql.Connection;
import java.util.List;

public abstract class ObjectDAO {

	protected Connection connect = null;

	public abstract List<Object> getAllObjectsDB();

	public abstract void addObject(Object object);

	public abstract Object getObject(Object object);

	public abstract void updateObject(Object object);

	public abstract void deleteObject(Object object);

}

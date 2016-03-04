package com.netcracker.unc.beans;

import java.util.List;

public interface ObjectDAO {

	public abstract List<Object> getAllObjectsDB();

	public abstract void addObject(Object object);

	public abstract Object getObject(Object object);

	public abstract void updateObject(Object object);

	public abstract void deleteObject(Object object);

}

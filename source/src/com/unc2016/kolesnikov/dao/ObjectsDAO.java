package com.unc2016.kolesnikov.dao;

import java.util.List;

public interface ObjectsDAO {

	// take all objects from database
	public List<SiteOfPrioritiesObjects> getAllObjectsDB();
	
	// take object by number id
	public void addObject(SiteOfPrioritiesObjects object);
	
	// take object by number id
	public SiteOfPrioritiesObjects getObject(SiteOfPrioritiesObjects object);
	
	// update object in database
	public void updateObject(SiteOfPrioritiesObjects object);
	
	// delete object from database
	public void deleteObject(SiteOfPrioritiesObjects object);
	
}

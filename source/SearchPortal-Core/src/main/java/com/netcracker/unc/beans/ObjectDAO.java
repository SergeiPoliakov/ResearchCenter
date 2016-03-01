package com.netcracker.unc.beans;

import java.util.List;

public interface ObjectDAO {

    List<Object> getAllObjectsDB();

    void addObject(Object object);

    Object getObject(Object object);

    void updateObject(Object object);

    void deleteObject(Object object);

}

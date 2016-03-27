package com.netcracker.unc.newmvc.ejb.controllers;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.netcracker.unc.newmvc.ejb.dao.EjbDAO;
import com.netcracker.unc.newmvc.ejb.entities.EntityAttribute;
import com.netcracker.unc.newmvc.ejb.entities.EntityObject;
import com.netcracker.unc.newmvc.ejb.entities.EntityObjectType;
import com.netcracker.unc.newmvc.ejb.entities.EntityParam;
import com.netcracker.unc.newmvc.ejb.entities.EntityUser;

@Stateless
public class ControllerObjects {
	@EJB
	private EjbDAO ejb;

	// database attributes
	private final int salaryAtr = 12;
	private final int endDateAtr = 11;
	private final int priorityAtr = 13;
	private final int caseType = 4; // Задача
	private final int createDate = 10; // Дата создания
	private final int endDate = 11; // Дата завершения
	private final int cost = 12;// Стоимость
	private final int priority = 13;// Приоритет
	private final SimpleDateFormat parse = new SimpleDateFormat("yyyy-MM-dd");

	public void createCase(String caseNameStr, String caseTypeStr, long caseParentInt, String casePriorityStr,
			String caseDateStr, String caseCostStr, EntityUser user) {

		EntityObject objectCategory = null;
		ArrayList<Long> objectsId = new ArrayList<Long>();
		for (EntityObject obj : user.getUserObjects()) {
			if (obj.getObjectName().toLowerCase().equals(caseTypeStr))
				objectCategory = obj;
			objectsId.add(obj.getFinObjectId());
		}

		EntityObject object = new EntityObject();

		// create model case
		if (caseParentInt == 0) {
			object.setParentObject(objectCategory.getParentObject());
		} else
			object.setParentObject((EntityObject) ejb.getObject(EntityObject.class, caseParentInt));

		// for found new creating object
		Set<EntityObject> oldObjects = user.getUserObjects();

		object.setObjectName(caseNameStr);
		object.setObjectType((EntityObjectType) ejb.getObject(EntityObjectType.class, caseType));
		object.setUser((EntityUser) ejb.getObject(EntityUser.class, user.getUserId()));
		ejb.addObject(object);

		object = getLastCreatingObject(oldObjects, user.getUserId());

		EntityParam param = new EntityParam();

		param.setAttribute((EntityAttribute) ejb.getObject(EntityAttribute.class, createDate));
		param = setParamCurrentDate(param);
		param.setObject((EntityObject) ejb.getObject(EntityObject.class, object.getFinObjectId()));
		ejb.addObject(param);

		param = new EntityParam();
		param.setAttribute((EntityAttribute) ejb.getObject(EntityAttribute.class, endDate));
		param.setObject((EntityObject) ejb.getObject(EntityObject.class, object.getFinObjectId()));
		param = setParamDate(caseDateStr, param);
		ejb.addObject(param);

		param = new EntityParam();
		param.setAttribute((EntityAttribute) ejb.getObject(EntityAttribute.class, cost));
		param.setObject((EntityObject) ejb.getObject(EntityObject.class, object.getFinObjectId()));
		param.setValue(caseCostStr);
		ejb.addObject(param);

		param = new EntityParam();
		param.setAttribute((EntityAttribute) ejb.getObject(EntityAttribute.class, priority));
		param.setObject((EntityObject) ejb.getObject(EntityObject.class, object.getFinObjectId()));
		param.setValue(casePriorityStr);
		ejb.addObject(param);

	}

	// return last creating object
	private EntityObject getLastCreatingObject(Set<EntityObject> previouesObjects, long userId) {
		EntityObject object = new EntityObject();
		EntityUser user = (EntityUser) ejb.getObject(EntityUser.class, userId);
		ArrayList<Long> previouesObjectsId = new ArrayList<Long>();
		long objectId = 0;

		for (EntityObject obj : previouesObjects)
			previouesObjectsId.add(obj.getFinObjectId());

		for (EntityObject obj : user.getUserObjects()) {
			boolean check = false;
			for (Long id : previouesObjectsId) {
				if (id == obj.getFinObjectId()) {
					check = true;
					break;
				}
			}
			if (!check) {
				objectId = obj.getFinObjectId();
				break;
			}
		}

		for (EntityObject obj : user.getUserObjects()) {
			if (obj.getFinObjectId() == objectId)
				object = obj;
		}

		return object;
	}

	private EntityParam setParamDate(String date, EntityParam param) {
		java.util.Date oldDate;
		try {
			oldDate = parse.parse(date);
			param.setValueDate(new Date(oldDate.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return param;
	}

	private EntityParam setParamCurrentDate(EntityParam param) {
		try {
			java.util.Date oldDate;
			String currentDate = parse.format(new java.util.Date());
			oldDate = parse.parse(currentDate);
			param.setValueDate(new Date(oldDate.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return param;
	}
}

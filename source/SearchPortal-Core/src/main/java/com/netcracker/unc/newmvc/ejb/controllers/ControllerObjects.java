package com.netcracker.unc.newmvc.ejb.controllers;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	// private final int salaryAtr = 12;
	// private final int endDateAtr = 11;
	// private final int priorityAtr = 13;
	private final int caseType = 4; // Задача
	private final int createDate = 10; // Дата создания
	private final int endDate = 11; // Дата завершения
	private final int cost = 12;// Стоимость
	private final int priority = 13;// Приоритет
	private final SimpleDateFormat parse = new SimpleDateFormat("yyyy-MM-dd");

	public void createCase(String caseNameStr, long caseTypeLong, long caseParentLong, String casePriorityStr,
			String caseDateStr, String caseCostStr, EntityUser user) {

		EntityObject objectCategory = (EntityObject) ejb.getObject(EntityObject.class, caseTypeLong);

		EntityObject object = new EntityObject();

		// create model case
		if (caseParentLong == 0) {
			object.setParentObject(objectCategory);
		} else
			object.setParentObject((EntityObject) ejb.getObject(EntityObject.class, caseParentLong));

		object.setObjectName(caseNameStr);
		object.setObjectType((EntityObjectType) ejb.getObject(EntityObjectType.class, caseType));
		object.setUser((EntityUser) ejb.getObject(EntityUser.class, user.getUserId()));
		ejb.addObject(object);
		ejb.updateReferencesToObjects();

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

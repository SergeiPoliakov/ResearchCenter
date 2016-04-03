package com.netcracker.unc.newmvc.ejb.controllers;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.netcracker.unc.newmvc.ejb.dao.EjbDAO;
import com.netcracker.unc.newmvc.ejb.entities.EntityAttribute;
import com.netcracker.unc.newmvc.ejb.entities.EntityObject;
import com.netcracker.unc.newmvc.ejb.entities.EntityObjectType;
import com.netcracker.unc.newmvc.ejb.entities.EntityParam;
import com.netcracker.unc.newmvc.ejb.entities.EntityUser;
import com.netcracker.unc.newmvc.ejb.models.ActiveCasesModel;

@Stateless
@LocalBean
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

	// standard priorities from database
	private final double high = 0.75;
	private final double average = 0.5;
	private final double low = 0.35;

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

	public EntityParam setParamCurrentDate(EntityParam param) {
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

	public List<ActiveCasesModel> getUserActiveCases(long userId) {
		return ejb.getActiveUserObjects(userId, this);
	}

	// set hierarchy spaces for jsp view
	public ActiveCasesModel setSpaceHierarchy(ActiveCasesModel activeCases, long i) {
		int count = 0;
		while (count < (i - 2) * 6) {
			activeCases.setSpaceHierarchy(activeCases.getSpaceHierarchy() + "&nbsp;");
			count++;
		}
		return activeCases;
	}

	public ActiveCasesModel setPriority(ActiveCasesModel activeCases, double priority) {
		if (priority == high)
			activeCases.setPriorityStr("высокий");
		if (priority == average)
			activeCases.setPriorityStr("средний");
		if (priority == low)
			activeCases.setPriorityStr("низкий");
		activeCases.setPriority(priority);
		return activeCases;
	}

	public void updateUserCase(String nameCase, String costCase, String dateCase, String priorityCase, long objectId,
			EntityUser user) {

		if (priorityCase.equals("высокий"))
			priorityCase = String.valueOf(high);
		if (priorityCase.equals("средний"))
			priorityCase = String.valueOf(average);
		if (priorityCase.equals("низкий"))
			priorityCase = String.valueOf(low);

		EntityObject object = (EntityObject) ejb.findObject(EntityObject.class, objectId);

		for (EntityParam param : object.getObjectParams()) {
			if (param.getAttribute().getAttributeId() == cost) {
				param.setValue(costCase);
				ejb.updateObject(param);
			}
			if (param.getAttribute().getAttributeId() == endDate) {
				param = setParamDate(dateCase, param);
				ejb.updateObject(param);
			}
			if (param.getAttribute().getAttributeId() == priority) {
				param.setValue(priorityCase);
				ejb.updateObject(param);
			} else
				continue;
		}

		// change case
		object.setObjectName(nameCase.trim());
		ejb.updateObject(object);
		ejb.updateReferencesToObjects();
	}
}

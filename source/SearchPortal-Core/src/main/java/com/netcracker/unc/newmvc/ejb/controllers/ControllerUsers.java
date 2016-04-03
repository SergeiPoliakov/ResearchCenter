package com.netcracker.unc.newmvc.ejb.controllers;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Hibernate;
import com.netcracker.unc.newmvc.ejb.dao.EjbDAO;
import com.netcracker.unc.newmvc.ejb.entities.EntityAttribute;
import com.netcracker.unc.newmvc.ejb.entities.EntityObject;
import com.netcracker.unc.newmvc.ejb.entities.EntityObjectType;
import com.netcracker.unc.newmvc.ejb.entities.EntityParam;
import com.netcracker.unc.newmvc.ejb.entities.EntityUser;
import com.netcracker.unc.newmvc.ejb.models.IncomeConsumptionModel;
import com.netcracker.unc.newmvc.ejb.models.SalaryModel;

@Stateless
@LocalBean
public class ControllerUsers {
	@EJB
	private EjbDAO ejb;
	@EJB
	private ControllerObjects objContr;

	// standard objects
	private final String object1 = "Транспорт";
	private final String object2 = "ЖКХ";
	private final String object3 = "Продукты";
	private final String object4 = "Кредит";
	private final String object5 = "Другое";
	private final int atributeCategory = 1; // Категория
	private final String objectSalaryName = "Зарплата";
	private final int incomeObjectType = 2; // Доход
	private final int atributeIncomeDate = 4; // Дата дохода
	private final int attributeIncome = 5; // Сумма дохода
	private final int attributeRegularIncome = 6; // Ежемесячный доход
	private final String attributeRegularIncomeCheck = "true";

	/*
	 * // general email properties private final String userLog =
	 * "sop.testing123@gmail.com"; private final String userAuth =
	 * "sop.testing123"; private final String passwordLog = "SiteOfPriorities";
	 */

	@PersistenceContext(unitName = "myP")
	private EntityManager em;

	public EntityUser checkAndGetUser(String login, String password) {
		EntityUser user = ejb.getUserByLoginAndPassword(login, createSalt(password));
		return user;
	}

	public EntityUser getUserByLogin(String login) {
		EntityUser user = (EntityUser) ejb.getObject(EntityUser.class, login);
		return user;
	}

	public EntityUser getUserById(long userId) {
		EntityUser user = (EntityUser) ejb.getObject(EntityUser.class, userId);
		Hibernate.initialize(user.getUserObjects());
		for (EntityObject obj : user.getUserObjects())
			Hibernate.initialize(obj.getObjectParams());
		return user;
	}

	public void updateUser(EntityUser user) {
		ejb.updateObject(user);
	}

	public boolean createUser(String login, String password, String name, String email) {
		EntityObject object;
		EntityUser user;
		System.out.println(ejb.getString());
		// check user
		user = (EntityUser) ejb.getObject(EntityUser.class, login);
		if (user == null) {
			user = new EntityUser();
			user.setAccountType("user");
			user.setHashSum(password.hashCode());
			user.setSalt(createSalt(password));
			user.setLogin(login);
			user.setName(name);
			user.setEmail(email);
			ejb.addObject(user);

			int i = 0;
			ArrayList<String> generalObjects = new ArrayList<String>();
			generalObjects.add(object1);
			generalObjects.add(object2);
			generalObjects.add(object3);
			generalObjects.add(object4);
			generalObjects.add(object5);
			while (i < 5) {
				object = new EntityObject();
				user = (EntityUser) ejb.getObject(EntityUser.class, user.getLogin());
				object.setUser(user);
				object.setObjectType((EntityObjectType) ejb.getObject(EntityObjectType.class, atributeCategory));
				// add standard objects
				object.setObjectName(generalObjects.get(i));
				ejb.addObject(object);
				i++;
			}
			return true;
		}
		return false;
	}

	// md5 hash
	public int createSalt(String salt) {
		byte[] bytePassword;
		try {
			bytePassword = salt.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] res = md.digest(bytePassword);
			BigInteger bi = new BigInteger(1, res);
			return bi.intValue();
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<EntityObject> getUserActiveCases(long userId) {
		List<EntityObject> list = ejb.getUserActiveObjects(userId);
		return list;
	}

	public List<EntityObject> getUserGeneralCases(long userId) {
		List<EntityObject> list = ejb.getGeneralObjects(userId);
		return list;
	}

	public SalaryModel getLastCheckSalary(long userId) {
		return ejb.getLastCheckSalary(userId);
	}

	public IncomeConsumptionModel procentForBar(IncomeConsumptionModel inCon, long userId) {
		return ejb.procentForBar(inCon, userId);
	}

	public void addUserSalary(String salary, long userId) {

		EntityUser user = getUserById(userId);
		EntityObject object = new EntityObject();

		EntityObjectType objType = (EntityObjectType) ejb.getObject(EntityObjectType.class, incomeObjectType);
		object.setObjectType(objType);
		object.setObjectName(objectSalaryName);
		object.setUser(user);
		ejb.addObject(object);
		ejb.updateReferencesToObjects();

		EntityParam param = new EntityParam();
		param.setAttribute((EntityAttribute) ejb.getObject(EntityAttribute.class, attributeIncome));
		param.setObject(object);
		param.setValue(salary);
		ejb.addObject(param);

		param = new EntityParam();
		param.setAttribute((EntityAttribute) ejb.getObject(EntityAttribute.class, atributeIncomeDate));
		param.setObject(object);
		objContr.setParamCurrentDate(param);
		ejb.addObject(param);

		param = new EntityParam();
		param.setAttribute((EntityAttribute) ejb.getObject(EntityAttribute.class, attributeRegularIncome));
		param.setObject(object);
		param.setValue(attributeRegularIncomeCheck);
		ejb.addObject(param);

	}

	public void addMissUserSalary(SalaryModel salary, String[] mounthsControl, String newSalary, EntityUser user) {

		boolean checkSameSalary = true;
		String previous = mounthsControl[0];
		for (String i : mounthsControl) {
			if (!previous.equals(i))
				checkSameSalary = false;
			else
				continue;
		}
		EntityObject object;
		Date date = salary.getLastCheckDate();
		Calendar cal = Calendar.getInstance();

		// if user set not same salaries
		if (checkSameSalary == false) {
			for (String valueSalary : mounthsControl) {
				object = new EntityObject();
				object.setObjectType((EntityObjectType) ejb.getObject(EntityObjectType.class, incomeObjectType));
				object.setObjectName(objectSalaryName);
				object.setUser(user);
				ejb.addObject(object);
				ejb.updateReferencesToObjects();

				cal.setTimeInMillis(date.getTime());
				cal.add(Calendar.MONTH, 1);
				date.setTime(cal.getTimeInMillis());
				addRegularySalaryParams(valueSalary, object, date);

			}
		} else {
			for (int i = 0; i < salary.getDateCount(); i++) {
				object = new EntityObject();
				object.setObjectType((EntityObjectType) ejb.getObject(EntityObjectType.class, incomeObjectType));
				object.setObjectName(objectSalaryName);
				object.setUser(user);
				ejb.addObject(object);
				ejb.updateReferencesToObjects();

				cal.setTimeInMillis(date.getTime());
				cal.add(Calendar.MONTH, 1);
				date.setTime(cal.getTimeInMillis());
				addRegularySalaryParams(newSalary, object, date);
			}
		}
		ejb.updateReferencesToObjects();
	}

	// add standard params for salary object
	private void addRegularySalaryParams(String valueSalary, EntityObject object, Date date) {
		EntityParam param = new EntityParam();
		param.setAttribute((EntityAttribute) ejb.getObject(EntityAttribute.class, attributeIncome));
		param.setObject(object);
		param.setValue(valueSalary);
		ejb.addObject(param);

		param = new EntityParam();
		param.setAttribute((EntityAttribute) ejb.getObject(EntityAttribute.class, atributeIncomeDate));
		param.setObject(object);
		param.setValueDate((Date) date.clone());
		ejb.addObject(param);

		param = new EntityParam();
		param.setAttribute((EntityAttribute) ejb.getObject(EntityAttribute.class, attributeRegularIncome));
		param.setObject(object);
		param.setValue(attributeRegularIncomeCheck);
		ejb.addObject(param);
	}
}

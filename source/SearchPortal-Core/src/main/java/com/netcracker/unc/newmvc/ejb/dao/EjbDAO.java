package com.netcracker.unc.newmvc.ejb.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.netcracker.unc.newmvc.ejb.entities.EntityObject;
import com.netcracker.unc.newmvc.ejb.entities.EntityUser;
import com.netcracker.unc.newmvc.ejb.models.IncomeConsumptionModel;
import com.netcracker.unc.newmvc.ejb.models.SalaryModel;

@Stateless
@LocalBean
public class EjbDAO {
	private final long objectTypeIncome = 2; // Доход
	private final String valueSalary = "true"; // Ежемесячный доход true/false
	private final long attributeCostIncome = 5; // Сумма дохода
	private final long attributeConsumption = 12; // Стоимость
	private final long objectTypeCase = 4; // Задача
	private final long objectTypeCategory = 1; // Категория

	@PersistenceContext(unitName = "myP")
	private EntityManager em;

	public void addObject(Object object) {
		em.persist(object);
	}

	public String getString() {
		return "ура, EJB заработал!";
	}

	public Object getObject(Class<?> clazz, long id) {
		Object object = em.getReference(clazz, id);
		return object;
	}

	public Object getObject(Class<?> clazz, String key) {
		Object object = null;
		List<?> list = null;
		if (clazz == EntityUser.class)
			list = em.createNamedQuery("Users.getUserByLogin", clazz).setParameter("login", key).getResultList();

		if (list.size() > 0)
			object = list.get(0);
		return object;
	}

	public EntityUser getUserByLoginAndPassword(String login, int salt) {
		List<?> list = null;
		list = em.createNamedQuery("Users.getUserByLoginAndPassword", EntityUser.class).setParameter("login", login)
				.setParameter("salt", salt).getResultList();
		if (list.size() > 0) {
			EntityUser user = (EntityUser) list.get(0);
			return user;
		}
		return null;
	}

	public void updateReferencesToObjects() {
		em.flush();
	}

	public List<EntityObject> getUserActiveObjects(long userId) {
		List<EntityObject> list = em.createNamedQuery("Objects.getActiveObjectsByUserId", EntityObject.class)
				.setParameter("userId", userId).setParameter("objectType", objectTypeCase).getResultList();
		return list;
	}

	public List<EntityObject> getGeneralObjects(long userId) {
		List<EntityObject> list = em.createNamedQuery("Objects.getGeneralObjects", EntityObject.class)
				.setParameter("userId", userId).setParameter("finObjectTypeId", objectTypeCategory).getResultList();
		return list;
	}

	public SalaryModel getLastCheckSalary(long userId) {

		SalaryModel salaryModel = new SalaryModel();

		String query = "select * from (select a1.FIN_OBJECT_ID, a1.OBJECT_NAME, a1.USER_ID, "
				+ "a2.VALUE_DATE, a3.FIN_OBJECT_TYPE_ID, a3.FIN_OBJECT_TYPE_NAME, "
				+ "a2.ATTRIBUTE_ID, TRUNC(MONTHS_BETWEEN(sysdate, a2.value_date), 0) as "
				+ "mounth_count, a5.VALUE from SP_FIN_OBJECTS a1 right join sp_params a2 "
				+ "on a1.FIN_OBJECT_ID = a2.FIN_OBJECT_ID inner join SP_FIN_OBJECT_TYPES a3 "
				+ "on a1.FIN_OBJECT_TYPE_ID = a3.FIN_OBJECT_TYPE_ID inner join sp_params a4 "
				+ "on a1.FIN_OBJECT_ID = a4.FIN_OBJECT_ID inner join sp_params a5 on "
				+ "a1.FIN_OBJECT_ID = a5.FIN_OBJECT_ID where a3.FIN_OBJECT_TYPE_ID = ? and "
				+ "a1.USER_ID = ? and a4.VALUE = ? and a2.VALUE_DATE IS NOT NULL and "
				+ "a5.ATTRIBUTE_ID = ? order by a2.VALUE_DATE desc) where rownum = 1";

		List<?> result = em.createNativeQuery(query).setParameter(1, objectTypeIncome).setParameter(2, userId)
				.setParameter(3, valueSalary).setParameter(4, attributeCostIncome).getResultList();

		if (result.size() > 0) {
			Iterator<?> i = result.iterator();
			if (i.hasNext()) {
				Object[] res = (Object[]) i.next();
				salaryModel.setObjectId((long) res[0]);
				salaryModel.setObjectName((String) res[1]);
				salaryModel.setUserId((long) res[2]);
				salaryModel.setLastCheckDate((Date) res[3]);
				salaryModel.setDateCount((long) res[4]);
				salaryModel.setValue((String) res[5]);
			}
		}
		return salaryModel;
	}

	public IncomeConsumptionModel procentForBar(IncomeConsumptionModel inCon, long userId) {

		// System.out.println(user.get_user_id());
		// column names: 1) maxCost 2) minCost 3) maxName 4) minName 5) avgCost
		// 6) sumCosts
		String query = "select max(to_number(q1.VALUE)) over() as maxcost, min(to_number(q1.VALUE)) over() as mincost, "
				+ "first_value(q3.object_name) over(order by to_number(q1.VALUE) desc) as maxname, first_value(q3.object_name) "
				+ "over(order by to_number(q1.VALUE)) as minname, sum(to_number(q1.VALUE)) over() as sumcost, avg(to_number(q1.VALUE))over() "
				+ "as avgcost from sp_params q1 right join (select n1.FIN_OBJECT_ID from (select s1.FIN_OBJECT_ID from sp_fin_objects s1 "
				+ "where s1.USER_ID = ? and s1.FIN_OBJECT_TYPE_ID = ?) n1 right join sp_params n2 on n1.FIN_OBJECT_ID = n2.FIN_OBJECT_ID "
				+ "where TRUNC(MONTHS_BETWEEN(sysdate, n2.value_date), 0) = 0) q2 on q1.FIN_OBJECT_ID = q2.FIN_OBJECT_ID right join "
				+ "SP_FIN_OBJECTS q3 on q2.FIN_OBJECT_ID = q3.FIN_OBJECT_ID where q1.ATTRIBUTE_ID = ?";

		// for income
		List<?> result = em.createNativeQuery(query).setParameter(1, userId).setParameter(2, objectTypeIncome)
				.setParameter(3, attributeCostIncome).getResultList();

		if (result.size() > 0) {
			Iterator<?> i = result.iterator();
			if (i.hasNext()) {
				Object[] res = (Object[]) i.next();
				inCon.setMaxIncome(((BigDecimal) res[0]).longValue());
				inCon.setMinIncome(((BigDecimal) res[1]).longValue());
				inCon.setMaxIncomeName((String) res[2]);
				inCon.setMinIncomeName((String) res[3]);
				inCon.setFullIncome(((BigDecimal) res[4]).longValue());
				inCon.setAvgIncome(((BigDecimal) res[5]).longValue());
			}
		}

		// for consumption
		query = "select max(to_number(q1.VALUE)) over() as maxcost, min(to_number(q1.VALUE)) "
				+ "over() as mincost, first_value(q2.object_name) over(order by "
				+ "to_number(q1.VALUE) desc) as maxname, first_value(q2.object_name) "
				+ "over(order by to_number(q1.VALUE)) as minname, "
				+ "sum(to_number(q1.VALUE)) over() as sumcost, avg(to_number(q1.VALUE)) "
				+ "over() as avgcost from SP_PARAMS q1 right join (select "
				+ "z1.VALUE_DATE, z1.fin_object_id, z2.object_name from sp_params z1 "
				+ "inner join SP_FIN_OBJECTS z2 on z1.fin_object_id = z2.fin_object_id "
				+ "where z1.value_date is not null and z2.FIN_OBJECT_TYPE_ID = ? and "
				+ "z1.value_date > sysdate and z2.USER_ID = ?) q2 on "
				+ "q1.FIN_OBJECT_ID = q2.fin_object_id where q1.ATTRIBUTE_ID = ?";

		result = em.createNativeQuery(query).setParameter(1, objectTypeCase).setParameter(2, userId)
				.setParameter(3, attributeConsumption).getResultList();

		if (result.size() > 0) {
			Iterator<?> i = result.iterator();
			if (i.hasNext()) {
				Object[] res = (Object[]) i.next();
				inCon.setMaxConsumption(((BigDecimal) res[0]).longValue());
				inCon.setMinConsumption(((BigDecimal) res[1]).longValue());
				inCon.setMaxConsumptionName((String) res[2]);
				inCon.setMinConsumptionName((String) res[3]);
				inCon.setAvgConsumption(((BigDecimal) res[4]).longValue());
				inCon.setFullConsumption(((BigDecimal) res[5]).longValue());
			}
		}
		return inCon;
	}
}

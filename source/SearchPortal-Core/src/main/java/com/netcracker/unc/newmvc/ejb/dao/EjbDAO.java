package com.netcracker.unc.newmvc.ejb.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.netcracker.unc.newmvc.ejb.controllers.ControllerObjects;
import com.netcracker.unc.newmvc.ejb.entities.EntityObject;
import com.netcracker.unc.newmvc.ejb.entities.EntityUser;
import com.netcracker.unc.newmvc.ejb.models.ActiveCasesModel;
import com.netcracker.unc.newmvc.ejb.models.CategoryModel;
import com.netcracker.unc.newmvc.ejb.models.IncomeConsumptionModel;
import com.netcracker.unc.newmvc.ejb.models.InvoiceModel;
import com.netcracker.unc.newmvc.ejb.models.SalaryModel;
import com.netcracker.unc.newmvc.ejb.models.StatisticModel;
import com.netcracker.unc.newmvc.ejb.models.TransactionModel;
import com.netcracker.unc.newmvc.ejb.queries.InvoiceQueries;

@Stateless
@LocalBean
public class EjbDAO {
	private final String valueSalary = "true"; // Ежемесячный доход true/false

	private final long attributeCostIncome = 5; // Сумма дохода
	private final long attributeConsumption = 12; // Стоимость
	private final long attributeEndDate = 11; // Дата завершения
	private final int attributeCreateDate = 10; // Дата создания
	private final int attributePriority = 13; // Приоритет

	private final long objectTypeIncome = 2; // Доход
	private final long objectTypeCase = 4; // Задача
	private final long objectTypeCategory = 1; // Категория

	//// database name object types
	private String category1 = "Транспорт";
	private String category2 = "ЖКХ";
	private String category3 = "Кредит";
	private String category4 = "Продукты";
	private String category5 = "Другое";

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

	public Object findObject(Class<?> clazz, long id) {
		Object object = em.find(clazz, id);
		return object;
	}

	public void updateObject(Object object) {
		em.merge(object);
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

	public void deleteObject(Object object) {
		em.remove(object);
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

	// columns result : fin_object_id, object_name, user_id, value_date,
	// fin_object_type_id, fin_object_type_name, attribute_id, mounth_count,
	// value
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
				salaryModel.setObjectId(((BigDecimal) res[0]).longValue());
				salaryModel.setObjectName((String) res[1]);
				salaryModel.setUserId(((BigDecimal) res[2]).longValue());
				salaryModel.setLastCheckDate(new Date(((Timestamp) res[3]).getTime()));
				salaryModel.setDateCount(((BigDecimal) res[7]).longValue());
				salaryModel.setValue((String) res[8]);
			}
		}
		return salaryModel;
	}

	public List<CategoryModel> getCategories(long userId) {
		List<CategoryModel> categoryList = new ArrayList<>();

		String query = " SELECT " + " FIN_OBJECT_ID as OBJECT_ID, OBJECT_NAME, ( SELECT coef_par.VALUE"
				+ " FROM SP_PARAMS coef_par WHERE coef_par.ATTRIBUTE_ID = 1"
				+ "  AND coef_par.FIN_OBJECT_ID = main_fo.FIN_OBJECT_ID ) as coefficient, ("
				+ " SELECT coef_par.VALUE FROM SP_PARAMS coef_par WHERE coef_par.ATTRIBUTE_ID = 2 "
				+ "  AND coef_par.FIN_OBJECT_ID = main_fo.FIN_OBJECT_ID ) as min_percent, ("
				+ " SELECT coef_par.VALUE FROM SP_PARAMS coef_par WHERE coef_par.ATTRIBUTE_ID = 3 "
				+ "  AND coef_par.FIN_OBJECT_ID = main_fo.FIN_OBJECT_ID ) as max_percent "
				+ "FROM SP_FIN_OBJECTS main_fo WHERE FIN_OBJECT_TYPE_ID =1 AND USER_ID = ? ";

		Query emQuery = em.createNativeQuery(query);
		emQuery.setParameter(1, userId);
		List<?> resultList = emQuery.getResultList();
		if (resultList.size() > 0) {
			Iterator<?> i = resultList.iterator();
			while (i.hasNext()) {
				CategoryModel categoryModel = new CategoryModel();
				Object[] res = (Object[]) i.next();

				categoryModel.setObjectId(((BigDecimal) res[0]).longValue());
				categoryModel.setObjectName((String) res[1]);
				categoryModel.setCoeficient(((BigDecimal) res[0]).doubleValue());
				categoryModel.setMinPercent(((BigDecimal) res[0]).doubleValue());
				categoryModel.setMaxPercent(((BigDecimal) res[0]).doubleValue());

				categoryList.add(categoryModel);
			}
		}

		if (categoryList.isEmpty())
			categoryList = Collections.emptyList();

		return categoryList;
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

	public List<ActiveCasesModel> getActiveUserObjects(long userId, ControllerObjects controller) {

		List<ActiveCasesModel> list = null;

		// column names: hierarchy, level, objectid, casename, casecost,
		// startdate,
		// enddate, parentname, objid, priority
		String query = "select * from (select lpad(object_name, length(object_name)+(level-2)*5, ' ') "
				+ "as hierarchy, level, sp_fin_objects.FIN_OBJECT_ID as objectid from "
				+ "sp_fin_objects where level > 1 start with LOWER(object_name) = "
				+ "? or LOWER(object_name) = ? or LOWER(object_name) = "
				+ "? or LOWER(object_name) = ? or LOWER(object_name) = "
				+ "? connect by prior FIN_OBJECT_ID = PARENT_ID) e1 inner join "
				+ "(select t1.casename, t1.casecost, t1.startdate, t1.enddate, "
				+ "t1.parentname, t1.objid, t2.VALUE as priority from (select "
				+ "q1.object_name as casename, q1.cost as casecost, q2.VALUE_DATE as "
				+ "startdate, q1.enddate as enddate, q4.OBJECT_NAME as parentname, "
				+ "q1.Fin_object_id as objid from (select p1.OBJECT_NAME, p1.VALUE as cost, "
				+ "p1.FIN_OBJECT_ID, p2.VALUE_DATE as enddate, p1.parent_id from "
				+ "(select a1.OBJECT_NAME, a3.VALUE, a1.FIN_OBJECT_ID, a1.PARENT_ID from "
				+ "SP_FIN_OBJECTS a1 inner join sp_fin_object_types a2 on "
				+ "a1.FIN_OBJECT_TYPE_ID = a2.FIN_OBJECT_TYPE_ID inner join sp_params a3 on "
				+ "a1.FIN_OBJECT_ID = a3.FIN_OBJECT_ID inner join SP_ATTRIBUTES a4 on "
				+ "a3.ATTRIBUTE_ID = a4.ATTRIBUTE_ID where a2.FIN_OBJECT_TYPE_ID = ? and "
				+ "a4.attribute_id = ? and a1.USER_ID = ?) p1 inner join SP_PARAMS p2 on "
				+ "p1.FIN_OBJECT_ID = p2.FIN_OBJECT_ID inner join SP_ATTRIBUTES p3 on "
				+ "p2.ATTRIBUTE_ID = p3.ATTRIBUTE_ID where p3.ATTRIBUTE_ID = ?) q1 inner "
				+ "join sp_params q2 on q1.FIN_OBJECT_ID = q2.FIN_OBJECT_ID inner join "
				+ "SP_ATTRIBUTES q3 on q2.ATTRIBUTE_ID = q3.ATTRIBUTE_ID inner join "
				+ "SP_FIN_OBJECTS q4 on q1.parent_id = q4.FIN_OBJECT_ID where "
				+ "q3.ATTRIBUTE_ID = ?) t1 inner join sp_params t2 on "
				+ "t1.objid = t2.FIN_OBJECT_ID inner join SP_ATTRIBUTES t3 on "
				+ "t2.ATTRIBUTE_ID = t3.ATTRIBUTE_ID where t3.ATTRIBUTE_ID = ?) e2 on e1.objectid = e2.objid";

		Query emQuery = em.createNativeQuery(query);
		emQuery.setParameter(1, category1.toLowerCase());
		emQuery.setParameter(2, category2.toLowerCase());
		emQuery.setParameter(3, category3.toLowerCase());
		emQuery.setParameter(4, category4.toLowerCase());
		emQuery.setParameter(5, category5.toLowerCase());
		emQuery.setParameter(6, objectTypeCase);
		emQuery.setParameter(7, attributeConsumption);
		emQuery.setParameter(8, userId);
		emQuery.setParameter(9, attributeEndDate);
		emQuery.setParameter(10, attributeCreateDate);
		emQuery.setParameter(11, attributePriority);

		list = new ArrayList<ActiveCasesModel>();
		List<?> result = emQuery.getResultList();
		if (result.size() > 0) {
			Iterator<?> i = result.iterator();
			while (i.hasNext()) {
				ActiveCasesModel activeCases = new ActiveCasesModel();
				Object[] res = (Object[]) i.next();
				activeCases.setHierarchy((String) res[0]);
				activeCases.setLevel(((BigDecimal) res[1]).intValue());
				activeCases = controller.setSpaceHierarchy(activeCases, ((BigDecimal) res[1]).intValue());
				activeCases.setObjectId(((BigDecimal) res[2]).longValue());
				activeCases.setCaseName((String) res[3]);
				activeCases.setCaseCost((String) res[4]);
				activeCases.setStartDate(((Timestamp) res[5]).toString());
				activeCases.setEndDate(((Timestamp) res[6]).toString());
				activeCases.setParentName((String) res[7]);
				activeCases.setPriority(((BigDecimal) res[8]).doubleValue());
				activeCases = controller.setPriority(activeCases, Double.valueOf((String) res[9]));
				list.add(activeCases);
			}
		}
		return list;
	}


	public int getSumBalance(EntityUser user) {
		Query query = em.createNativeQuery(InvoiceQueries.getSumAllBalancesByUserId);
		query.setParameter(1, user.getUserId());
		List<?> result = query.getResultList();
		if (result.size() > 0) {
			Iterator<?> i = result.iterator();
			if (i.hasNext()) {
				Object obj = i.next();
				if (obj != null) {
					System.out.println(obj);
					return ((BigDecimal)obj).intValue();
					
				}
			}
		}
		return 0;
	}

	public InvoiceModel getInvoice(int invoiceId, EntityUser user) {
		InvoiceModel invoice = new InvoiceModel();
		invoice.setInvoiceId(invoiceId);
		Query query = em.createNativeQuery(InvoiceQueries.getInvoiceName);
		query.setParameter(1, user.getUserId());
		query.setParameter(2, invoiceId);
		List<?> result = query.getResultList();
		if (result.size() > 0) {
			Iterator<?> i = result.iterator();
			if (i.hasNext()) {
				invoice.setInvoiceName((String) i.next());
			}
		}
		query = em.createNativeQuery(InvoiceQueries.getBalanceCreditAndPercent);
		query.setParameter(1, user.getUserId());
		query.setParameter(2, invoiceId);
		result = query.getResultList();
		if (result.size() > 0) {
			Iterator<?> i = result.iterator();
			if (i.hasNext()) {
				Object[] res = (Object[]) i.next();
				invoice.setBalance(Integer.valueOf((String) res[0]));
				invoice.setCredit(Boolean.parseBoolean((String) res[1]));
				invoice.setPercent(Integer.valueOf((String) res[2]));
			}
		}
		return invoice;
	}

	public void updateBalance(InvoiceModel invoiceJsp) {
		InvoiceModel invoice = invoiceJsp;
		Query query = em.createNativeQuery(InvoiceQueries.updateBalance);
		query.setParameter(1, invoice.getBalance());
		query.setParameter(2, invoice.getInvoiceId());
		query.executeUpdate();
	}

	public ArrayList<InvoiceModel> getAllInvoice(EntityUser user) {
		InvoiceModel invoice;
		ArrayList<InvoiceModel> listGetAllInvoice = new ArrayList<InvoiceModel>();
		Query query = em.createNativeQuery(InvoiceQueries.getAllInvoicesByUserId);
		query.setParameter(1, user.getUserId());
		List<?> result = query.getResultList();
		if (result.size() > 0) {
			Iterator<?> i = result.iterator();
			while (i.hasNext()) {
				Object[] res = (Object[]) i.next();

				invoice = new InvoiceModel();
				invoice.setInvoiceId(((BigDecimal) res[0]).intValue());
				invoice.setInvoiceName((String) res[1]);
				invoice.setBalance(Integer.valueOf((String) res[2]));
				invoice.setCredit(Boolean.parseBoolean((String) res[3]));
				invoice.setPercent(Integer.valueOf((String) res[4]));
				listGetAllInvoice.add(invoice);
			}
		}
		return listGetAllInvoice;
	}
	
	public StatisticModel procentForPie(StatisticModel inStat, int userId) {

		String query = "SELECT SUM(par.VALUE) AS res"
				+ "FROM SP_FIN_OBJECTS fo INNER JOIN SP_ATTRIBUTES atr ON fo.FIN_OBJECT_TYPE_ID = atr.FIN_OBJECT_TYPE_ID"
				+ "INNER JOIN SP_PARAMS par ON atr.ATTRIBUTE_ID = par.ATTRIBUTE_ID"
				+ "WHERE fo.FIN_OBJECT_TYPE_ID = 3 AND fo.USER_ID = ? AND par.VALUE_DATE = SYSDATE";

		List<?> result = em.createNativeQuery(query).setParameter(1, userId).getResultList();

		if (result.size() > 0) {
			Iterator<?> i = result.iterator();
			if (i.hasNext()) {
				Object[] res = (Object[]) i.next();
				inStat.setReservedMoney(((BigDecimal) res[0]).longValue());
			}
		}

		query = "select SUM(p.VALUE)" + "from SP_FIN_OBJECTS o, SP_PARAMS p"
				+ "where p.FIN_OBJECT_ID=o.FIN_OBJECT_ID and USER_ID=? and p.ATTRIBUTE_ID=14 and o.FIN_OBJECT_TYPE_ID=5";

		result = em.createNativeQuery(query).setParameter(1, userId).getResultList();

		if (result.size() > 0) {
			Iterator<?> i = result.iterator();
			if (i.hasNext()) {
				Object[] res = (Object[]) i.next();
				inStat.setSum(((BigDecimal) res[0]).longValue());
			}
		}
		// Add calculating of free money
		/*
		 * 
		 */
		return inStat;
	}
//
	public List<TransactionModel> transactionTable(int userId) {
		
		List<TransactionModel> resTrans = null;
		String query = "SELECT t.transaction_date, t.cost, fo.object_name"
				+ "FROM SP_TRANSACTIONS t JOIN SP_FIN_OBJECTS fo ON t.fin_object_id=fo.fin_object_id"
				+ "WHERE fo.user_id=?";
        resTrans = new ArrayList<TransactionModel>();
		List<?> result = em.createNativeQuery(query).setParameter(1, userId).getResultList();

		if (result.size() > 0) {
			Iterator<?> i = result.iterator();
			while (i.hasNext()) {
				TransactionModel inTrans = new TransactionModel();
				Object[] res = (Object[]) i.next();
				inTrans.setDate((String) res[0]);
				inTrans.setValue(((BigDecimal) res[1]).intValue());
				inTrans.setName((String) res[2]);
				resTrans.add(inTrans);
			}
		}
		return resTrans;
	}
}

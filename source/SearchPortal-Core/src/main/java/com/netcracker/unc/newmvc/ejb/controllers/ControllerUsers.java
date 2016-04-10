package com.netcracker.unc.newmvc.ejb.controllers;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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

	// priorities for general objects
	// Транспорт
	private final String priority1 = "0.3"; // Коэффициент приоритета
	private final String minProcent1 = "10"; // Минимальный % от расхода
	private final String maxProcent1 = "40"; // Минимальный % от расхода

	// ЖКХ
	private final String priority2 = "0.65"; // Коэффициент приоритета
	private final String minProcent2 = "15"; // Минимальный % от расхода
	private final String maxProcent2 = "80"; // Минимальный % от расхода

	// Продукты
	private final String priority3 = "0.8"; // Коэффициент приоритета
	private final String minProcent3 = "20"; // Минимальный % от расхода
	private final String maxProcent3 = "80"; // Минимальный % от расхода

	// Кредит
	private final String priority4 = "0.4"; // Коэффициент приоритета
	private final String minProcent4 = "15"; // Минимальный % от расхода
	private final String maxProcent4 = "60"; // Минимальный % от расхода

	// Другое
	private final String priority5 = "0.2"; // Коэффициент приоритета
	private final String minProcent5 = "10"; // Минимальный % от расхода
	private final String maxProcent5 = "70"; // Минимальный % от расхода

	// standard objects
	private final String object1 = "Транспорт";
	private final String object2 = "ЖКХ";
	private final String object3 = "Продукты";
	private final String object4 = "Кредит";
	private final String object5 = "Другое";

	private final long attribute1 = 1; // Коэффициент приоритета
	private final long attribute2 = 2; // Минимальный процент от расхода
	private final long attribute3 = 3; // Максимальный процент от расхода

	private final int typeCategory = 1; // Категория
	private final String objectSalaryName = "Зарплата";
	private final int incomeObjectType = 2; // Доход
	private final int atributeIncomeDate = 4; // Дата дохода
	private final int attributeIncome = 5; // Сумма дохода
	private final int attributeRegularIncome = 6; // Ежемесячный доход
	private final String attributeRegularIncomeCheck = "true";

	// general email properties
	private final String userLog = "sop.testing123@gmail.com";
	private final String userAuth = "sop.testing123";
	private final String passwordLog = "SiteOfPriorities";

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
		return user;
	}

	public void updateUser(EntityUser user) {
		ejb.updateObject(user);
	}

	public boolean createUser(String login, String password, String name, String email) {
		EntityObject object;
		EntityParam param;
		// check user
		EntityUser user = (EntityUser) ejb.getObject(EntityUser.class, login);
		if (user == null) {
			user = new EntityUser();
			user.setAccountType("user");
			user.setHashSum(password.hashCode());
			user.setSalt(createSalt(password));
			user.setLogin(login);
			user.setName(name);
			user.setEmail(email);
			ejb.addObject(user);
			// ejb.updateReferencesToObjects();
			EntityObjectType objectType = (EntityObjectType) ejb.getObject(EntityObjectType.class, typeCategory);

			String[] generalObjects = new String[] { object1, object2, object3, object4, object5 };
			String[][] categoryAr = new String[][] { { priority1, maxProcent1, minProcent1 },
					{ priority2, maxProcent2, minProcent2 }, { priority3, maxProcent3, minProcent3 },
					{ priority4, maxProcent4, minProcent4 }, { priority5, maxProcent5, minProcent5 } };

			for (int i = 0; i < generalObjects.length; i++) {
				object = new EntityObject();
				object.setUser(user);
				object.setObjectType(objectType);
				// add standard objects
				object.setObjectName(generalObjects[i]);
				ejb.addObject(object);
				ejb.updateReferencesToObjects();

				param = new EntityParam();
				ejb.updateReferencesToObjects();
				param.setObject(object);
				param.setAttribute((EntityAttribute) ejb.getObject(EntityAttribute.class, attribute1));
				param.setValue(categoryAr[i][0]);
				ejb.addObject(param);

				param = new EntityParam();
				ejb.updateReferencesToObjects();
				param.setObject(object);
				param.setAttribute((EntityAttribute) ejb.getObject(EntityAttribute.class, attribute2));
				param.setValue(categoryAr[i][1]);
				ejb.addObject(param);

				param = new EntityParam();
				ejb.updateReferencesToObjects();
				param.setObject(object);
				param.setAttribute((EntityAttribute) ejb.getObject(EntityAttribute.class, attribute3));
				param.setValue(categoryAr[i][2]);
				ejb.addObject(param);
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

	public void sendPasswordToUserEmail(String email, String password) {

		String text = "Добрый день! Вы забыли пароль и начали процедуру восстановления пароля "
				+ "на сайте приоритетов. Пожалуйста, используйте следующий пароль для входа: " + password;

		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.user", userAuth);
		properties.put("mail.smtp.password", passwordLog);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.debug", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.EnableSSL.enable", "true");
		properties.put("mail.smtp.port", "587");
		properties.setProperty("mail.smtp.socketFactory.port", "587");
		properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.smtp.socketFactory.fallback", "false");

		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userAuth, passwordLog);
			}
		};
		Session session = Session.getInstance(properties, auth);
		MimeMessage mes = new MimeMessage(session);
		try {
			mes.setFrom(new InternetAddress(userLog));
			mes.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			mes.setSubject("Восстановление пароля - no reply");
			mes.setText(text);
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", userAuth, passwordLog);
			transport.sendMessage(mes, mes.getAllRecipients());

		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public String generateUserPassword(int length) {
		Random random = new Random();
		char charData = ' ';
		String data = "";
		for (int i = 0; i < length; i++) {
			int numb = random.nextInt(25);
			if ((numb & 1) == 0)
				charData = (char) (numb + 97);
			else
				charData = (char) (numb + 65);
			data += String.valueOf(charData);
		}
		return data;
	}
}
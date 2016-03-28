package com.netcracker.unc.newmvc.ejb.controllers;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.netcracker.unc.newmvc.ejb.dao.EjbDAO;
import com.netcracker.unc.newmvc.ejb.entities.EntityObject;
import com.netcracker.unc.newmvc.ejb.entities.EntityObjectType;
import com.netcracker.unc.newmvc.ejb.entities.EntityUser;

@Stateless
public class ControllerUsers {
	@EJB
	private EjbDAO ejb;

	// standard objects
	private final String object1 = "Транспорт";
	private final String object2 = "ЖКХ";
	private final String object3 = "Продукты";
	private final String object4 = "Кредит";
	private final String object5 = "Другое";
	private final int atributeCategory = 1; // Категория

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
		// for change fetch to EAGLE
		user.getUserObjects().size();
		return user;
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
		System.out.println("попал");
		List<EntityObject> list = ejb.getUserActiveCases(userId);
		return list;
	}

	public String test() {
		return "EJB работает";
	}

}

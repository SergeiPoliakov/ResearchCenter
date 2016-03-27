package com.netcracker.unc.newmvc.ejb.dao;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.netcracker.unc.newmvc.ejb.entities.EntityObject;
import com.netcracker.unc.newmvc.ejb.entities.EntityUser;

@Stateless
@LocalBean
public class EjbDAO {

	@PersistenceContext(unitName = "myP")
	private EntityManager em;

	private final int typeCase = 4; // Задача

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

	public List<EntityObject> getUserActiveCases(long userId) {
		System.out.println(em);
		List<EntityObject> list = em.createNamedQuery("Objects.getActiveCasesByUserId", EntityObject.class)
				.setParameter("userId", userId).setParameter("objectType", typeCase).getResultList();
		System.out.println(list);
		return list;
	}
}

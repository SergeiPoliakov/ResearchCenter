package com.netcracker.unc.newmvc.ejb.models;

import javax.ejb.Stateful;
import com.netcracker.unc.newmvc.ejb.entities.EntityUser;

@Stateful
public class UserModel {

	private EntityUser user = new EntityUser();

	public EntityUser getUser() {
		return user;
	}

	public void setUser(EntityUser user) {
		this.user = user;
	}

}

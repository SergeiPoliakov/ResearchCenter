package com.netcracker.unc.newmvc.ejb.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.hibernate.mapping.Collection;


import com.netcracker.unc.newmvc.ejb.dao.EjbDAO;
import com.netcracker.unc.newmvc.ejb.entities.EntityUser;
import com.netcracker.unc.newmvc.ejb.models.CreditModel;

@Stateless
@LocalBean
public class ControllerCredit {
	@EJB
	private EjbDAO dao;
			
	public List<CreditModel> getAllCreditsByUser(EntityUser user){
		List<CreditModel> resultList = new ArrayList<CreditModel>(dao.getAllCredits(user));
		if(resultList.isEmpty())
			resultList = Collections.emptyList();
		
		return resultList;
	}
	
	
	
}

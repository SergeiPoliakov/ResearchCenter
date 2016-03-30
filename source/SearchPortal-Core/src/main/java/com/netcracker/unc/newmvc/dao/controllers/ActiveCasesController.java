package com.netcracker.unc.newmvc.dao.controllers;

import com.netcracker.unc.newmvc.ejb.models.ActiveCasesModel;

public class ActiveCasesController {

	// standard priorities from database
	private final double high = 0.75;
	private final double average = 0.5;
	private final double low = 0.35;

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

	// set hierarchy spaces for jsp view
	public ActiveCasesModel setSpaceHierarchy(ActiveCasesModel activeCases, long i) {
		int count = 0;
		while (count < (i - 2) * 6) {
			activeCases.setSpaceHierarchy(activeCases.getSpaceHierarchy() + "&nbsp;");
			count++;
		}
		return activeCases;
	}

}

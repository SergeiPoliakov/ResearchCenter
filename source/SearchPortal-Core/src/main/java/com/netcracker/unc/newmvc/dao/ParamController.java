package com.netcracker.unc.newmvc.dao;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ParamController {

	private final SimpleDateFormat parse = new SimpleDateFormat("yyyy-MM-dd");
	// database attributes
	private final int atrIncome = 5; // Сумма дохода
	private final int atrDate = 4; // Дата дохода
	private final int atrCheck = 9; // Ежемесячный расход
	private final String valueCheck = "true";

	public ParamModel setParamDate(String date, ParamModel param) {
		java.util.Date oldDate;
		try {
			oldDate = parse.parse(date);
			param.setValueDate(new Date(oldDate.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return param;
	}

	public ParamModel setParamCurrentDate(ParamModel param) {
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

	public void addRegularSalaryParams(String salary, int objectId, Date date) {
		ParamModel param = new ParamModel();
		ParamDAO paramDAO = new ParamDAO();

		param.setAttributeId(atrIncome);
		param.setFinObjectId(objectId);
		param.setValue(salary);
		paramDAO.addParam(param);

		param = new ParamModel();
		param.setAttributeId(atrDate);
		param.setFinObjectId(objectId);
		param.setValueDate(date);
		paramDAO.addParam(param);

		param = new ParamModel();
		param.setAttributeId(atrCheck);
		param.setFinObjectId(objectId);
		param.setValue(valueCheck);
		paramDAO.addParam(param);

	}

}

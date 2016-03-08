package com.netcracker.unc.newmvc.dao;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ParamController {

	private final SimpleDateFormat parse = new SimpleDateFormat("yyyy-MM-dd");

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
}

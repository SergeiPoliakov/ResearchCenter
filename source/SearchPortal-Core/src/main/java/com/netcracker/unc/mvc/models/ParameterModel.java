package com.netcracker.unc.mvc.models;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ParameterModel {

	private String value = null;
	private SimpleDateFormat parse = new SimpleDateFormat("yyyy-MM-dd");
	private Date value_date = null;
	private int fin_object_id;
	private int attribute_id;
	private java.util.Date oldDate = null;

	public String get_value() {
		return value;
	}

	public void set_value(String value) {
		this.value = value;
	}

	public Date get_value_date() {
		return value_date;
	}

	public void set_value_date(String value_date) {
		try {
			oldDate = parse.parse(value_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.value_date = new Date(oldDate.getTime());
	}

	public int get_fin_object_id() {
		return fin_object_id;
	}

	public void set_fin_object_id(int fin_object_id) {
		this.fin_object_id = fin_object_id;
	}

	public int get_attribute_id() {
		return attribute_id;
	}

	public void set_attribute_id(int attribute_id) {
		this.attribute_id = attribute_id;
	}

}

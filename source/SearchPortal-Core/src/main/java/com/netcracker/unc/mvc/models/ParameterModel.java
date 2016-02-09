package main.java.com.netcracker.unc.mvc.models;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ParameterModel {

	private String value1 = null;
	private SimpleDateFormat parse = new SimpleDateFormat("yyyy-MM-dd");
	private Date value_date = null;
	private int fin_object_id;
	private int attribute_id;
	private java.util.Date oldDate = null;

	public String get_value1() {
		return value1;
	}

	public void set_value1(String value1) {
		this.value1 = value1;
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

package com.unc2016.mvc.models;

public class ParameterModel {

	private String _value1 = null;
	private String _value_date = null;
	private int _fin_object_id;
	private int _attribute_id;

	public String get_value1() {
		return _value1;
	}

	public void set_value1(String _value1) {
		this._value1 = _value1;
	}

	public String get_value_date() {
		return _value_date;
	}

	public void set_value_date(String _value_date) {
		this._value_date = _value_date;
	}

	public int get_fin_object_id() {
		return _fin_object_id;
	}

	public void set_fin_object_id(int _fin_object_id) {
		this._fin_object_id = _fin_object_id;
	}

	public int get_attribute_id() {
		return _attribute_id;
	}

	public void set_attribute_id(int _attribute_id) {
		this._attribute_id = _attribute_id;
	}

}

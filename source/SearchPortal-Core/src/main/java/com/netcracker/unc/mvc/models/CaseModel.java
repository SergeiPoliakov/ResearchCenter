package com.netcracker.unc.mvc.models;

public class CaseModel {

	private int _fin_object_id;
	private Integer _parent_id = null;
	private String _object_name = null;
	private int _fin_object_type_id;
	private int _user_id;

	public int get_fin_object_id() {
		return _fin_object_id;
	}

	public void set_fin_object_id(int _fin_object_id) {
		this._fin_object_id = _fin_object_id;
	}

	public Integer get_parent_id() {
		return _parent_id;
	}

	public void set_parent_id(int _parent_id) {
		this._parent_id = _parent_id;
	}

	public String get_object_name() {
		return _object_name;
	}

	public void set_object_name(String _object_name) {
		this._object_name = _object_name.toLowerCase();
	}

	public int get_fin_object_type_id() {
		return _fin_object_type_id;
	}

	public void set_fin_object_type_id(int _fin_object_type_id) {
		this._fin_object_type_id = _fin_object_type_id;
	}

	public int get_user_id() {
		return _user_id;
	}

	public void set_user_id(int _user_id) {
		this._user_id = _user_id;
	}

}

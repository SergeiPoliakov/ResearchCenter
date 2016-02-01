package com.unc2016.kolesnikov.mvc;

import com.unc2016.kolesnikov.mvc.dao.SiteOfPrioritiesObjects;

/**
 * 
 * case model from fin_object_types table
 * 
 * @author Kolesnikov
 *
 */
public class CasesTypesPOJO implements SiteOfPrioritiesObjects {

	private int _fin_object_type_id;
	private String _fin_object_type_name = null;

	public int get_fin_object_type_id() {
		return _fin_object_type_id;
	}

	public void set_fin_object_type_id(int _fin_object_type_id) {
		this._fin_object_type_id = _fin_object_type_id;
	}

	public String get_fin_object_type_name() {
		return _fin_object_type_name;
	}

	public void set_fin_object_type_name(String _fin_object_type_name) {
		this._fin_object_type_name = _fin_object_type_name;
	}

}

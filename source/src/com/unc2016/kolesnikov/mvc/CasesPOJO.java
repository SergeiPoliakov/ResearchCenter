package com.unc2016.kolesnikov.mvc;

/**
 * 
 * case model from fin_objects table
 * 
 * @author Kolesnikov
 *
 */
public class CasesPOJO {

	private int fin_object_id;
	private int parent_id;
	private String object_name = null;
	private int fin_object_type_id;
	private int user_id;
	private String fin_object_type_name = null;
	
	
	public int get_Fin_object_id() {
		return fin_object_id;
	}
	public void set_Fin_object_id(int fin_object_id) {
		this.fin_object_id = fin_object_id;
	}
	public int get_Parent_id() {
		return parent_id;
	}
	public void set_Parent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public String get_Object_name() {
		return object_name;
	}
	public void set_Object_name(String object_name) {
		this.object_name = object_name;
	}
	public int get_Fin_object_type_id() {
		return fin_object_type_id;
	}
	public void set_Fin_object_type_id(int fin_object_type_id) {
		this.fin_object_type_id = fin_object_type_id;
	}
	public int get_User_id() {
		return user_id;
	}
	public void set_User_id(int user_id) {
		this.user_id = user_id;
	}
	public String get_Fin_object_type_name() {
		return fin_object_type_name;
	}
	public void set_Fin_object_type_name(String fin_object_type_name) {
		this.fin_object_type_name = fin_object_type_name;
	}
	
	
	
}

package main.java.com.netcracker.unc.mvc.models;

public class AttributeModel {

	private String _attribute_name = null;
	private int _fin_object_type_id;
	private int _attribute_id;

	public String get_attribute_name() {
		return _attribute_name;
	}

	public void set_attribute_name(String _attribute_name) {
		this._attribute_name = _attribute_name;
	}

	public int get_fin_object_type_id() {
		return _fin_object_type_id;
	}

	public void set_fin_object_type_id(int _fin_object_type_id) {
		this._fin_object_type_id = _fin_object_type_id;
	}

	public int get_attribute_id() {
		return _attribute_id;
	}

	public void set_attribute_id(int _attribute_id) {
		this._attribute_id = _attribute_id;
	}

}

package main.java.com.netcracker.unc.mvc.models;

public class TransactionModel {

	private int _transaction_id;
	private String _transaction_date = null;
	private int _fin_object_id;
	private int _cost;
	private int _user_id;

	public int get_transaction_id() {
		return _transaction_id;
	}

	public void set_transaction_id(int _transaction_id) {
		this._transaction_id = _transaction_id;
	}

	public String get_transaction_date() {
		return _transaction_date;
	}

	public void set_transaction_date(String _transaction_date) {
		this._transaction_date = _transaction_date;
	}

	public int get_fin_object_id() {
		return _fin_object_id;
	}

	public void set_fin_object_id(int _fin_object_id) {
		this._fin_object_id = _fin_object_id;
	}

	public int get_cost() {
		return _cost;
	}

	public void set_cost(int _cost) {
		this._cost = _cost;
	}

	public int get_user_id() {
		return _user_id;
	}

	public void set_user_id(int _user_id) {
		this._user_id = _user_id;
	}

}

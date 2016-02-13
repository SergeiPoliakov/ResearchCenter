package com.netcracker.unc.mvc.models;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserModel {

	private int _user_id;
	private String _login = null;
	private int _hash_sum;
	private String _name = null;
	private String _account_type = null;
	private int _salt;

	public int get_user_id() {
		return _user_id;
	}

	public void set_user_id(int _user_id) {
		this._user_id = _user_id;
	}

	public String get_login() {
		return _login.toLowerCase();
	}

	public void set_login(String _login) {
		this._login = _login;
	}

	public int get_hash_sum() {
		return _hash_sum;
	}

	public void set_hash_sum(int _hash_sum) {
		this._hash_sum = _hash_sum;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public String get_account_type() {
		return _account_type;
	}

	public void set_account_type(String _account_type) {
		this._account_type = _account_type;
	}

	public int get_salt() {
		return _salt;
	}

	public void set_salt(int _salt) {
		this._salt = _salt;
	}

	public void createHashAndSalt(String password) {
		set_hash_sum(password.hashCode());
		set_salt(createSalt(password));
	}

	// md5 hash
	private Integer createSalt(String salt) {
		byte[] bytePassword;
		try {
			bytePassword = salt.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] res = md.digest(bytePassword);
			BigInteger bi = new BigInteger(1, res);
			return bi.intValue();
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}

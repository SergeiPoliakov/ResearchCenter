package com.netcracker.unc.mvc.models;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserModel {

	private int user_id;
	private String login = null;
	private int hash_sum;
	private String name = null;
	private String account_type = null;
	private int salt;

	public int get_user_id() {
		return user_id;
	}

	public void set_user_id(int user_id) {
		this.user_id = user_id;
	}

	public String get_login() {
		return login.toLowerCase();
	}

	public void set_login(String login) {
		this.login = login;
	}

	public int get_hash_sum() {
		return hash_sum;
	}

	public void set_hash_sum(int hash_sum) {
		this.hash_sum = hash_sum;
	}

	public String get_name() {
		return name;
	}

	public void set_name(String name) {
		this.name = name;
	}

	public String get_account_type() {
		return account_type;
	}

	public void set_account_type(String _account_type) {
		this.account_type = _account_type;
	}

	public int get_salt() {
		return salt;
	}

	public void set_salt(int salt) {
		this.salt = salt;
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

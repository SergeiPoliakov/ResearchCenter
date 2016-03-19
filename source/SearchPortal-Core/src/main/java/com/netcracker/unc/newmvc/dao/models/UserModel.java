package com.netcracker.unc.newmvc.dao.models;

import java.util.List;

public class UserModel {

	private int userId;
	private String login;
	private int hashSum;
	private String name;
	private String accountType;
	private int salt;
	private List<ObjectModel> allObjects;
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getHashSum() {
		return hashSum;
	}

	public void setHashSum(int hashSum) {
		this.hashSum = hashSum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public int getSalt() {
		return salt;
	}

	public void setSalt(int salt) {
		this.salt = salt;
	}

	public List<ObjectModel> getAllObjects() {
		return allObjects;
	}

	public void setAllObjects(List<ObjectModel> allObjects) {
		this.allObjects = allObjects;
	}

}

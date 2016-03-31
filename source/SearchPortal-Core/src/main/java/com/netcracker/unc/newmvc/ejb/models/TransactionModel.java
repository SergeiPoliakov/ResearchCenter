package com.netcracker.unc.newmvc.ejb.models;

import javax.ejb.Stateful;

@Stateful

public class TransactionModel {
	private String date, nameOper;
	private int value;
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public void setName (String name) {
		this.nameOper = name;
	}
	
	public void setValue (int val){
		this.value = val;
	}
	
	public String getDate () {
		return this.date;
	}
	
	public String getNameOper () {
		return this.nameOper;
	}
	
	public int value () {
		return this.value;
	}
}


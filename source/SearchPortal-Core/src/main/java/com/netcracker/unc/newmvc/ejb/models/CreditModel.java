package com.netcracker.unc.newmvc.ejb.models;

import java.sql.Date;

import javax.ejb.Stateful;

@Stateful
public class CreditModel {
	
	private int creditValue;//Сумма кредита
	
	private double creditPercent;//процентная ставка
	
	private String receivingDate;//дата получения кредита
	
	private int payPeriod;//срок платежа в днях
	
	
	
	//то что можно наследовать от базовой модели
	private long parentID;
	private String creditName;
	private int objectTypeID;
	private long creditID;
	
	public CreditModel(){
		
	}
	
	public long getParentID()
	{
		return this.parentID;
	}
	
	public void setParentID(long parentId){
		this.parentID = parentId;
	}
	
	public String getCreditName(){
		return this.creditName;
	}

	public int getCreditValue() {
		return this.creditValue;
	}

	public void setCreditValue(int creditValue) {
		this.creditValue = creditValue;
	}

	public double getCreditPercent() {
		return this.creditPercent;
	}

	public void setCreditPercent(double creditPercent) {
		this.creditPercent = creditPercent;
	}

	public String getReceivingDate() {
		return this.receivingDate;
	}

	public void setReceivingDate(String receivingDate) {
		this.receivingDate = receivingDate;
	}

	public int getPayPeriod() {
		return this.payPeriod;
	}

	public void setPayPeriod(int payPeriod) {
		this.payPeriod = payPeriod;
	}

	public int getObjectTypeID() {
		return this.objectTypeID;
	}

	public void setObjectTypeID(int objectTypeID) {
		this.objectTypeID = objectTypeID;
	}

	public long getCreditID() {
		return this.creditID;
	}

	public void setCreditID(long creditID) {
		this.creditID = creditID;
	}

	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}
}

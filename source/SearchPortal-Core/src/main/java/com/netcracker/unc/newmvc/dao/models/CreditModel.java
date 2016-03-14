package com.netcracker.unc.newmvc.dao.models;

public class CreditModel {
	
	private int creditValue;//Сумма кредита
	private int creditBalance;//Остаток выплат
	private double creditPercent;//процентная ставка
	private String receivingDate;//дата получения
	private int payPeriod;
	private int monthPay;
	
	private String creditName;
	private int objectTypeID;
	private int creditID;
	
	public CreditModel(){
		creditName = null;
		objectTypeID = 0;
		creditID = 0;
		
		payPeriod = 0;
		monthPay = 0;
		creditBalance = 0;
		creditValue = 0;
		creditPercent = 0;
		receivingDate = null;
	}
	
	public String getCreditName(){
		return creditName;
	}

	public int getCreditValue() {
		return creditValue;
	}

	public void setCreditValue(int creditValue) {
		this.creditValue = creditValue;
	}

	public int getCreditBalance() {
		return creditBalance;
	}

	public void setCreditBalance(int creditBalance) {
		this.creditBalance = creditBalance;
	}

	public double getCreditPercent() {
		return creditPercent;
	}

	public void setCreditPercent(double creditPercent) {
		this.creditPercent = creditPercent;
	}

	public String getReceivingDate() {
		return receivingDate;
	}

	public void setReceivingDate(String receivingDate) {
		this.receivingDate = receivingDate;
	}

	public int getPayPeriod() {
		return payPeriod;
	}

	public void setPayPeriod(int payPeriod) {
		this.payPeriod = payPeriod;
	}

	public int getMonthPay() {
		return monthPay;
	}

	public void setMonthPay(int monthPay) {
		this.monthPay = monthPay;
	}

	public int getObjectTypeID() {
		return objectTypeID;
	}

	public void setObjectTypeID(int objectTypeID) {
		this.objectTypeID = objectTypeID;
	}

	public int getCreditID() {
		return creditID;
	}

	public void setCreditID(int creditID) {
		this.creditID = creditID;
	}

	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}
	
	

}

package com.netcracker.unc.newmvc.dao.models;

import java.sql.Date;

public class IncomeModel {
	
	public IncomeModel(){
		
	}
	
	private int incomeId;
	private int inoiceId;
	private String incomeName;
	private int incomeSum;
	private boolean month;
	private String invoiceName;
	private Date dateIncome;
	
	
	public int getInoiceId() {
		return inoiceId;
	}
	public void setInoiceId(int inoiceId) {
		this.inoiceId = inoiceId;
	}
	public String getInvoiceName() {
		return invoiceName;
	}
	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}
	public int getIncomeId() {
		return incomeId;
	}
	public void setIncomeId(int incomeId) {
		this.incomeId = incomeId;
	}
	public String getIncomeName() {
		return incomeName;
	}
	public void setIncomeName(String incomName) {
		this.incomeName = incomName;
	}
	public int getIncomeSum() {
		return incomeSum;
	}
	public void setIncomeSum(int incomeSum) {
		this.incomeSum = incomeSum;
	}
	public boolean isMonth() {
		return month;
	}
	public void setMonth(boolean month) {
		this.month = month;
	}
	public Date getDateIncome() {
		return dateIncome;
	}
	public void setDateIncome(Date dateIncome) {
		this.dateIncome = dateIncome;
	}
	
}

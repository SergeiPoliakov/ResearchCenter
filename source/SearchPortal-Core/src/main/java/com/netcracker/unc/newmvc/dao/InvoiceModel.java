package com.netcracker.unc.newmvc.dao;


public class InvoiceModel {
	
	private String invoiceName;
	private int balance;
	private boolean credit;
	private double percent;
	private int invoiceId;
	private int objectTypeId;
	
	public void InvoiceModel(){
		invoiceName = "none";
		balance = 0;
		credit = false;
		percent = 0;
		invoiceId = 1;
		objectTypeId = 5;
		
	}

	public String getInvoiceName() {
		return invoiceName;
	}

	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public boolean isCredit() {
		return credit;
	}

	public void setCredit(boolean credit) {
		this.credit = credit;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public int getObjectTypeId() {
		return objectTypeId;
	}

	public void setObjectTypeId(int objectTypeId) {
		this.objectTypeId = objectTypeId;
	}
	
	
	
}

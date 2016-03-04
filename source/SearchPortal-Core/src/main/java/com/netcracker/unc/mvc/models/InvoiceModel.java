package com.netcracker.unc.mvc.models;


public class InvoiceModel {
	
	private String invoiceName = "none";
	private int balance;
	private boolean credit;
	private double percent;
	private int invoice_id;
	private int user_id;
	private int object_type_id;
	
	public void InvoiceModel(){
		balance = 0;
		credit = false;
		percent = 0;
		invoice_id = 1;
		user_id = 1;
		object_type_id = 5;
		
	}
	
	public int getObject_type_id() {
		return object_type_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(int invoice_id) {
		this.invoice_id = invoice_id;
	}

	public InvoiceModel(){
		
	}

	public String getInvoiceName(){
		return invoiceName;
	}
	
	public void setInvoiceName(String invoiceName){
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
	
}

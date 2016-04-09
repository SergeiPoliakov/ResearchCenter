package com.netcracker.unc.newmvc.dao.models;

import java.sql.Date;
import com.netcracker.unc.newmvc.dao.InvoiceDAO;
import com.netcracker.unc.newmvc.ejb.models.InvoiceModel;

public class IncomeModel {
	
	public IncomeModel(){
		
	}
	
	private int incomeId;
	private int userId;
	private int invoiceId;
	private String incomeName;
	//private String invoiceName;
	private int incomeSum;
	private boolean month;
	private Date dateIncome;
	private InvoiceModel IncomesIvoice;

	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(int inoiceId) {
		this.invoiceId = inoiceId;
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
	
	public InvoiceModel getIncomesInvoice(){
		InvoiceDAO invoiceDao = new InvoiceDAO();
		return invoiceDao.getInvoice(this.invoiceId, this.userId);	
	}
	
}

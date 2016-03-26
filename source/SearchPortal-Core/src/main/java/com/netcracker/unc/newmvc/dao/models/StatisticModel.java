package com.netcracker.unc.newmvc.dao.models;

public class StatisticModel {

	private long sum, freeMoney, reservedMoney;


	public long getSum() {
		return sum;
	}

	public void setSum(long sum) {
		this.sum = sum;
	}

	public long getReservedMoney() {
		return reservedMoney;
	}

	public void setReservedMoney(long reservedMoney) {
		this.reservedMoney = reservedMoney;
	}
    
	public long getfreeMoney() {
		return freeMoney;
	}

	public void setfreeMoney() {
		this.freeMoney = this.sum-this.reservedMoney;
	}
	
/*public class transactionModel {
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
	
	public String returnRow () {
		return "<tr><td>" + this.date + "</td><td>" + this.name + "</td><td>" + value + "</td></tr>";
	}
}*/

}

package com.netcracker.unc.newmvc.ejb.models;

import javax.ejb.Stateful;

@Stateful

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

}

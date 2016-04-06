package com.netcracker.unc.newmvc.ejb.entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sp_transactions", indexes = { @Index(name = "transactionId", columnList = "transaction_id"),
		@Index(name = "transactionDate", columnList = "transaction_date"),
		@Index(name = "finObjectId", columnList = "fin_object_id"), @Index(name = "cost", columnList = "cost") })
public class EntityTransaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(sequenceName = "SP_TRANSACTION_ID_SEQ ", name = "transactionSEQ")
	@GeneratedValue(generator = "transactionSEQ")
	@NotNull
	@Column(name = "transaction_id", unique = true)
	private long transactionId;
	@Column(name = "transaction_date", nullable = false)
	private Date transactionDate;
	@JoinColumn(name = "fin_object_id", referencedColumnName = "fin_object_id")
	@ManyToOne(fetch = FetchType.EAGER)
	private EntityObject object;
	@Column(name = "cost", nullable = false)
	private long cost;

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public EntityObject getObject() {
		return object;
	}

	public void setObject(EntityObject object) {
		this.object = object;
	}

	public long getCost() {
		return cost;
	}

	public void setCost(long cost) {
		this.cost = cost;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

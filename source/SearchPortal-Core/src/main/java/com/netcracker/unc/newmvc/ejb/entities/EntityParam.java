package com.netcracker.unc.newmvc.ejb.entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sp_params", indexes = { @Index(name = "value", columnList = "value"),
		@Index(name = "valueDate", columnList = "value_date"),
		@Index(name = "finObjectId", columnList = "fin_object_id"),
		@Index(name = "attributeId", columnList = "attribute_id") })
public class EntityParam implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "value", nullable = true)
	private String value;
	@Column(name = "value_date", nullable = true)
	private Date valueDate;
	@Id
	@JoinColumn(name = "fin_object_id", referencedColumnName = "fin_object_id")
	@OneToOne(fetch = FetchType.EAGER)
	private EntityObject object;
	@Id
	@JoinColumn(name = "attribute_id", referencedColumnName = "attribute_id")
	@ManyToOne(fetch = FetchType.EAGER)
	private EntityAttribute attribute;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getValueDate() {
		return valueDate;
	}

	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}

	public EntityObject getObject() {
		return object;
	}

	public void setObject(EntityObject object) {
		this.object = object;
	}

	public EntityAttribute getAttribute() {
		return attribute;
	}

	public void setAttribute(EntityAttribute attribute) {
		this.attribute = attribute;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

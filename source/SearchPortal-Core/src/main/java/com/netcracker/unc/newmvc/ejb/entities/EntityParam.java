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

	@Id
	private long id;
	@Column(name = "value", nullable = true)
	private String value;
	@Column(name = "value_date", nullable = true)
	private Date valueDate;
	@JoinColumn(name = "fin_object_id", referencedColumnName = "fin_object_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private EntityObject object;
	@JoinColumn(name = "attribute_id", referencedColumnName = "attribute_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private EntityAttribute attribute;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

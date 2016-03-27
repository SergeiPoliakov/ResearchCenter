package com.netcracker.unc.newmvc.ejb.entities;

import java.io.Serializable;
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
@Table(name = "sp_attributes", indexes = { @Index(name = "atributeName", columnList = "attribute_name"),
		@Index(name = "finObjectTypeId", columnList = "fin_object_type_id"),
		@Index(name = "attributeId", columnList = "attribute_id") })
public class EntityAttribute implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "attribute_name", nullable = false, length = 50)
	private String attributeName;
	@JoinColumn(name = "fin_object_type_id", referencedColumnName = "fin_object_type_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private EntityObjectType objectType;
	@Id
	@SequenceGenerator(sequenceName = "SP_ATTRIBUTE_ID_SEQ ", name = "attributeSEQ")
	@GeneratedValue(generator = "attributeSEQ")
	@NotNull
	@Column(name = "attribute_id", unique = true)
	private long attributeId;

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public EntityObjectType getObjectType() {
		return objectType;
	}

	public void setObjectType(EntityObjectType objectType) {
		this.objectType = objectType;
	}

	public long getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(long attributeId) {
		this.attributeId = attributeId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

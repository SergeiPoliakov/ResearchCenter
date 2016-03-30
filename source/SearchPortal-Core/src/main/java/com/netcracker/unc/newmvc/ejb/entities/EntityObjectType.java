package com.netcracker.unc.newmvc.ejb.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sp_fin_object_types", indexes = { @Index(name = "finObjectTypeId", columnList = "fin_object_type_id"),
		@Index(name = "finObjectTypeName", columnList = "fin_object_type_name") })
public class EntityObjectType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(sequenceName = "SP_FIN_OBJECT_TYPE_ID_SEQ ", name = "objectTypeSEQ")
	@GeneratedValue(generator = "objectTypeSEQ")
	@NotNull
	@Column(name = "fin_object_type_id", unique = true)
	private long finObjectTypeId;
	@Column(name = "fin_object_type_name", nullable = false, length = 50)
	private String finObjectTypeName;
	@OneToMany(mappedBy = "objectType", fetch = FetchType.LAZY, targetEntity = EntityObject.class)
	private Set<EntityObject> objects;
	@OneToMany(mappedBy = "objectType", fetch = FetchType.LAZY, targetEntity = EntityAttribute.class)
	private Set<EntityAttribute> attributes;

	public long getFinObjectTypeId() {
		return finObjectTypeId;
	}

	public void setFinObjectTypeId(long finObjectTypeId) {
		this.finObjectTypeId = finObjectTypeId;
	}

	public String getFinObjectTypeName() {
		return finObjectTypeName;
	}

	public void setFinObjectTypeName(String finObjectTypeName) {
		this.finObjectTypeName = finObjectTypeName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<EntityObject> getObjects() {
		return objects;
	}

	public void setObjects(Set<EntityObject> objects) {
		this.objects = objects;
	}

	public Set<EntityAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<EntityAttribute> attributes) {
		this.attributes = attributes;
	}

}

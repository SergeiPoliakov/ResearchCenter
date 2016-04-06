package com.netcracker.unc.newmvc.ejb.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sp_fin_objects", indexes = { @Index(name = "objectId", columnList = "fin_object_id"),
		@Index(name = "parentId", columnList = "parent_id"), @Index(name = "objectName", columnList = "object_name"),
		@Index(name = "finObjectTypeId", columnList = "fin_object_type_id"),
		@Index(name = "userId", columnList = "user_id") })
@NamedQueries({
		@NamedQuery(name = "Objects.getActiveObjectsByUserId", query = "SELECT a1 FROM EntityObject a1 JOIN a1.objectParams a2 JOIN a1.user a3 JOIN a1.objectType a4 WHERE a3.userId = :userId AND a2.valueDate IS NOT NULL AND a2.valueDate > CURRENT_DATE AND a4.finObjectTypeId = :objectType"),
		@NamedQuery(name = "Objects.getGeneralObjects", query = "SELECT a1 FROM EntityObject a1 JOIN a1.objectType a2 JOIN a1.user a3 WHERE a2.finObjectTypeId = :finObjectTypeId AND a3.userId = :userId") })
public class EntityObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(sequenceName = "SP_FIN_OBJECT_ID_SEQ ", name = "objectSEQ")
	@GeneratedValue(generator = "objectSEQ")
	@NotNull
	@Column(name = "fin_object_id", unique = true)
	private long finObjectId;
	@JoinColumn(name = "parent_id", referencedColumnName = "fin_object_id", nullable = true)
	@ManyToOne(fetch = FetchType.EAGER)
	private EntityObject parentObject;
	@OneToMany(mappedBy = "parentObject", fetch = FetchType.EAGER)
	private Set<EntityObject> childObjects;
	@Column(name = "object_name", nullable = false, length = 100)
	private String objectName;
	@JoinColumn(name = "fin_object_type_id", referencedColumnName = "fin_object_type_id")
	@ManyToOne(fetch = FetchType.EAGER)
	private EntityObjectType objectType;
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	@ManyToOne(fetch = FetchType.EAGER)
	private EntityUser user;
	@OneToMany(mappedBy = "object", fetch = FetchType.EAGER, targetEntity = EntityTransaction.class)
	private Set<EntityTransaction> objectTransactions;
	@OneToMany(mappedBy = "object", fetch = FetchType.EAGER, targetEntity = EntityParam.class )
	private Set<EntityParam> objectParams;

	public Set<EntityParam> getObjectParams() {
		return objectParams;
	}

	public void setObjectParams(Set<EntityParam> objectParams) {
		this.objectParams = objectParams;
	}

	public EntityObject getParentObject() {
		return parentObject;
	}

	public void setParentObject(EntityObject parentObject) {
		this.parentObject = parentObject;
	}

	public Set<EntityObject> getChildObjects() {
		return childObjects;
	}

	public void setChildObjects(Set<EntityObject> childObjects) {
		this.childObjects = childObjects;
	}

	public Set<EntityTransaction> getObjectTransactions() {
		return objectTransactions;
	}

	public void setObjectTransactions(Set<EntityTransaction> objectTransactions) {
		this.objectTransactions = objectTransactions;
	}

	public long getFinObjectId() {
		return finObjectId;
	}

	public void setFinObjectId(long finObjectId) {
		this.finObjectId = finObjectId;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public EntityObjectType getObjectType() {
		return objectType;
	}

	public void setObjectType(EntityObjectType objectType) {
		this.objectType = objectType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public EntityUser getUser() {
		return user;
	}

	public void setUser(EntityUser user) {
		this.user = user;
	}
}

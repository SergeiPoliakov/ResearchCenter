package com.netcracker.unc.newmvc.ejb.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sp_users", indexes = { @Index(name = "login", columnList = "login"),
		@Index(name = "name", columnList = "name"), @Index(name = "hashSum", columnList = "hash_sum"),
		@Index(name = "accountType", columnList = "account_type"), @Index(name = "salt", columnList = "salt"),
		@Index(name = "email", columnList = "email"), @Index(name = "userId", columnList = "user_id") })
@NamedQueries({ @NamedQuery(name = "Users.getUserByLogin", query = "SELECT c FROM EntityUser c WHERE c.login = :login"),
		@NamedQuery(name = "Users.getUserByLoginAndPassword", query = "SELECT c FROM EntityUser c WHERE c.login = :login AND c.salt = :salt") })
public class EntityUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(sequenceName = "SP_USER_ID_SEQ", name = "userSEQ")
	@GeneratedValue(generator = "userSEQ", strategy = GenerationType.SEQUENCE)
	@NotNull
	@Column(name = "user_id")
	private long userId;
	@Column(name = "login", nullable = false, length = 20)
	private String login;
	@Column(name = "hash_sum", nullable = false)
	private int hashSum;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "account_type", nullable = true)
	private String accountType;
	@Column(name = "salt", nullable = false)
	private int salt;
	@Column(name = "email", nullable = false, length = 50)
	private String email;
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<EntityObject> userObjects;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getLogin() {
		return login;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getHashSum() {
		return hashSum;
	}

	public void setHashSum(int hashSum) {
		this.hashSum = hashSum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public int getSalt() {
		return salt;
	}

	public void setSalt(int salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<EntityObject> getUserObjects() {
		return userObjects;
	}

	public void setUserObjects(Set<EntityObject> userObjects) {
		this.userObjects = userObjects;
	}
}

package kolesnikov.source;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// класс объектов юзер
public class Users {

	private Integer userId = null;
	private String login = null;
	private Integer hashSum = null;
	private String name = null;
	private String accountType = null;
	private Integer salt = null;
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Integer getHashSum() {
		return hashSum;
	}
	public void setHashSum(String hashSum) {
		setSalt(hashSum);
		this.hashSum  = hashSum.hashCode();
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
	public Integer getSalt() {
		return salt;
	}
	private void setSalt(String salt) {
		byte[] bytePassword;
		try {
			bytePassword = salt.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");  // хэшируем сообщение по типу md5
			byte[] res = md.digest(bytePassword);
			BigInteger bi = new BigInteger(1, res);
			this.salt = bi.intValue();
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
}

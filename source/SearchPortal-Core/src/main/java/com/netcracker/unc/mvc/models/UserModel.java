package com.netcracker.unc.mvc.models;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserModel {

    private int userId;
    private String login = null;
    private int hashSum;
    private String name = null;
    private String accountType = null;
    private int salt;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login.toLowerCase();
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

    public void createHashAndSalt(String password) {
        setHashSum(password.hashCode());
        setSalt(createSalt(password));
    }

    // md5 hash
    private Integer createSalt(String salt) {
        byte[] bytePassword;
        try {
            bytePassword = salt.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] res = md.digest(bytePassword);
            BigInteger bi = new BigInteger(1, res);
            return bi.intValue();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.netcracker.unc.beans.user;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {

    private int user_id;
    private String login = null;
    private int hash_sum;
    private String name = null;
    private String account_type = null;
    private int salt;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getHash_sum() {
        return hash_sum;
    }

    public void setHash_sum(int hash_sum) {
        this.hash_sum = hash_sum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public int getSalt() {
        return salt;
    }

    public void setSalt(int salt) {
        this.salt = salt;
    }

    public void createHashAndSalt(String password) {
        setHash_sum(password.hashCode());
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

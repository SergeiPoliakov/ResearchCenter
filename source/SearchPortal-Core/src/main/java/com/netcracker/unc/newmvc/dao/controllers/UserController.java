package com.netcracker.unc.newmvc.dao.controllers;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.netcracker.unc.newmvc.dao.ObjectDAO;
import com.netcracker.unc.newmvc.dao.UserDAO;
import com.netcracker.unc.newmvc.dao.models.ObjectModel;
import com.netcracker.unc.newmvc.dao.models.UserModel;

public class UserController {

	// standard objects
	private final String object1 = "Транспорт";
	private final String object2 = "ЖКХ";
	private final String object3 = "Продукты";
	private final String object4 = "Кредит";
	private final String object5 = "Другое";
	private final int atributeCategory = 1; // Категория

	// general email properties
	private final String userLog = "sop.testing123@gmail.com";
	private final String userAuth = "sop.testing123";
	private final String passwordLog = "SiteOfPriorities";

	// md5 hash
	public int createSalt(String salt) {
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
		return 0;
	}

	public UserModel checkAndGetUser(String login, String password) {
		UserDAO userDAO = new UserDAO();
		UserModel user = userDAO.getUserWithCheckPassword(login, password.hashCode(), createSalt(password));
		return user;
	}

	public boolean createUser(String login, String password, String name, String email) {
		UserDAO userDAO = new UserDAO();
		ObjectDAO objectDAO = new ObjectDAO();
		ObjectModel object;
		UserModel user;

		// check user
		user = userDAO.getUserByLogin(login);
		if (user.getUserId() == 0) {
			user = new UserModel();
			user.setAccountType("user");
			user.setHashSum(password.hashCode());
			user.setSalt(createSalt(password));
			user.setLogin(login);
			user.setName(name);
			user.setEmail(email);
			userDAO.addUser(user);

			object = new ObjectModel();
			object.setUserId(userDAO.getUserByLogin(user.getLogin()).getUserId());
			object.setFinObjectTypeId(atributeCategory);

			// add standard objects
			object.setObjectName(object1);
			objectDAO.addObject(object);
			object.setObjectName(object2);
			objectDAO.addObject(object);
			object.setObjectName(object3);
			objectDAO.addObject(object);
			object.setObjectName(object4);
			objectDAO.addObject(object);
			object.setObjectName(object5);
			objectDAO.addObject(object);

			return true;
		} else
			return false;
	}

	public void sendPasswordToUserEmail(String email, String password) {

		String text = "Добрый день! Вы забыли пароль и начали процедуру восстановления пароля "
				+ "на сайте приоритетов. Пожалуйста, используйте следующий пароль для входа: " + password;

		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.user", userAuth);
		properties.put("mail.smtp.password", passwordLog);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.debug", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.EnableSSL.enable", "true");
		properties.put("mail.smtp.port", "587");
		properties.setProperty("mail.smtp.socketFactory.port", "587");
		properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.smtp.socketFactory.fallback", "false");

		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userAuth, passwordLog);
			}
		};
		Session session = Session.getInstance(properties, auth);
		MimeMessage mes = new MimeMessage(session);
		try {
			mes.setFrom(new InternetAddress(userLog));
			mes.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			mes.setSubject("Восстановление пароля - no reply");
			mes.setText(text);
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", userAuth, passwordLog);
			transport.sendMessage(mes, mes.getAllRecipients());

		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public String generateUserPassword(int length) {
		Random random = new Random();
		char charData = ' ';
		String data = "";
		for (int i = 0; i < length; i++) {
			int numb = random.nextInt(25);
			if ((numb & 1) == 0)
				charData = (char) (numb + 97);
			else
				charData = (char) (numb + 65);
			data += String.valueOf(charData);
		}
		return data;
	}
}

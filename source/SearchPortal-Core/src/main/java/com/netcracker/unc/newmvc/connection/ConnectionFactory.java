package com.netcracker.unc.newmvc.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Properties;

/**
 * singleton of connection to database
 * 
 * @author Kolesnikov
 *
 */
public class ConnectionFactory {

	// use application.properties for connect to database
	private Properties dbPr = new Properties();

	private String jdbcDriverPath;
	private String connectPath;
	private String user;
	private String password;

	private static ConnectionFactory object = new ConnectionFactory();

	private ConnectionFactory() {

		// load connection properties
		InputStream loadPr = this.getClass().getResourceAsStream("connection.properties");
		try {
			if (loadPr != null) {
				dbPr.load(loadPr);
				jdbcDriverPath = dbPr.getProperty("db.driver");
				connectPath = dbPr.getProperty("db.type") + dbPr.getProperty("db.url") + ":"
						+ dbPr.getProperty("db.sid");
				user = dbPr.getProperty("db.user");
				password = dbPr.getProperty("db.password");
			} else {
				alternativeConectionData();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// get singleton of connection
	private Connection createConnection() {
		Connection connect = null;
		try {
			Locale.setDefault(Locale.ENGLISH);
			Class.forName(jdbcDriverPath);
			connect = DriverManager.getConnection(connectPath, user, password);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connect;
	}

	public static Connection getConnection() {
		return object.createConnection();
	}

	private void alternativeConectionData() {
		jdbcDriverPath = "oracle.jdbc.driver.OracleDriver";
		connectPath = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		user = "sys as sysdba";
		password = "1234";
	}

}

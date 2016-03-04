package com.netcracker.unc.mvc.connection;

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

	private static ConnectionFactory object = new ConnectionFactory();

	private ConnectionFactory() {
		try {
			// load connection properties
			InputStream loadPr = this.getClass().getResourceAsStream("connection.properties");
			dbPr.load(loadPr);
			Class.forName(dbPr.getProperty("db.driver"));
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	// get singleton of connection
	private Connection createConnection() {
		Connection connect = null;
		try {
			Locale.setDefault(Locale.ENGLISH);
			String connectPath = dbPr.getProperty("db.type") + dbPr.getProperty("db.url") + ":"
					+ dbPr.getProperty("db.sid");
			connect = DriverManager.getConnection(connectPath, dbPr.getProperty("db.user"),
					dbPr.getProperty("db.password"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connect;
	}

	public static Connection getConnection() {
		return object.createConnection();
	}

	/*
	 * private static ConnectionFactory object = new ConnectionFactory();
	 * 
	 * private ConnectionFactory() { try { Locale.setDefault(Locale.ENGLISH);
	 * Class.forName("oracle.jdbc.driver.OracleDriver"); } catch
	 * (ClassNotFoundException e) { e.printStackTrace(); } }
	 * 
	 * // get singleton of connection private Connection createConnection() {
	 * Connection connect = null; try { Locale.setDefault(Locale.ENGLISH);
	 * //String connectPath = "jdbc:oracle:thin:@127.0.0.1:1521:XE"; connect =
	 * DriverManager.getConnection( "jdbc:oracle:thin:@127.0.0.1:1521:XE",
	 * "sys as sysdba", "1234"); } catch (SQLException e) { e.printStackTrace();
	 * } return connect; }
	 * 
	 * public static Connection getConnection() { return
	 * object.createConnection(); }
	 */

}

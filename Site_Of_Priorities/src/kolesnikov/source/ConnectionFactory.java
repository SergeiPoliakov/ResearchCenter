package kolesnikov.source;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// синглтон подключения к БД
public class ConnectionFactory {

	///////////////////////////////////////////////////
	private String url = "localhost:1521";  // адрес БД
	private String sid = "SiteOfPriori"; // SID базы данных
	private String user = "sys as sysdba"; // имя пользователя
	private String password = "ARaQLcAQ07"; // пароль пользователя
	///////////////////////////////////////////////////
	private static ConnectionFactory object = new ConnectionFactory();	
			
	private ConnectionFactory() {	
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	private Connection createConnection() {
		Connection connect = null;
		try {
			connect = DriverManager.getConnection("jdbc:oracle:thin:@" + url + ":" + sid, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connect;
	}
	
	public static Connection getConnection() {
		return object.createConnection();
	}
	
	
}

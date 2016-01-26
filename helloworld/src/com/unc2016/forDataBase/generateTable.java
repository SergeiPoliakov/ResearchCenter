package com.unc2016.forDataBase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.jdbc.htmlHelper;

/**
 * Servlet implementation class generateTable
 */
@WebServlet("/generateTable")
public class generateTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public generateTable() {
        super();
    }
    
    /**
	 * @see HttpServlet#init(HttpServletRequest request, HttpServletResponse response)
	 */
    public void init(HttpServletRequest request, HttpServletResponse response)
    	throws ServletException, IOException {
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String select = (String) request.getParameter("select");
        String table = null;
		try {
			table = convertSelectToTable(select);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("table", table);
		RequestDispatcher disp = request.getRequestDispatcher("hello.jsp");
		disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	
	
	private String convertSelectToTable(String select) throws SQLException{
		String table=null;
		Connection connection = null;
		try{
			Locale.setDefault(Locale.ENGLISH);
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@127.0.0.1:1521:XE",
					"sys as sysdba", "1234");
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(select);
			table = htmlHelper.createTable(resultSet);
		}
		catch(Exception e){ System.out.println(e.getMessage()); }
		finally{ connection.close(); }
		return table;
	}

}

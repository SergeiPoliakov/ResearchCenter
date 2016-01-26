package com.test.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        try{
        	Class.forName("oracle.jdbc.driver.OracleDriver");
        }
        catch(Exception e){}
        // TODO Auto-generated constructor stub
    }

    
    private void doRequestAndGenerateTable(HttpServletRequest request, HttpServletResponse response) 
    									throws SQLException{
		
    	//request.setAttribute("test1", "test11");
    	
    	
    	
    	
    	RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/hello.jsp");
    	
    	try {
			requestDispatcher.forward(request, response);
		} catch (ServletException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	
    	Connection connection = null;
		try{
			connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE",
														"sys as sysdba", "1234");
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM PJ_USERS");
			resultSet.next();
			resultSet.getString(1);
			PrintWriter printWriter = response.getWriter();
			printWriter.println("test");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
    }
    
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			doRequestAndGenerateTable(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		try {
			doRequestAndGenerateTable(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

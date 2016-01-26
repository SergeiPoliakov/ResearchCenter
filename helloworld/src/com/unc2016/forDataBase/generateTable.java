package com.unc2016.forDataBase;

import java.io.IOException;
import java.io.PrintWriter;
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
		response.setContentType("text/html;charset=Windows-1251");//указание для PrintWriter
        
		
		
		
		
		
        String val = null;
		try {
			val = convertSelectToTable(select);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		request.setAttribute("name", val);
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
		StringBuffer table = new StringBuffer();
		table.append("<table>");
		table.append("<tr><th>34</th><th>");
		
		
		Connection connection = null;
		try{
			Locale.setDefault(Locale.ENGLISH);
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@127.0.0.1:1521:XE",
					"sys as sysdba", "1234");
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(select);
			
			table.append("HELLOWORLD!!!!!");
			
		}
		catch(Exception e){ System.out.println(e.getMessage()); }
		finally{ connection.close(); }
		
		
		
		table.append("</th></tr><tr><th>65</th><th>ывапр</th></tr>");
		
		table.append("</table>");
		return table.toString();
	}

}

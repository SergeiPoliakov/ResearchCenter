package com.unc2016.forDataBase;

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
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html;charset=Windows-1251");//указание кодировки
		PrintWriter pw = response.getWriter();
        pw.println("<B>hello world</B>");
        
        
        Integer val = new Integer(1);
		request.setAttribute("name", val);
		RequestDispatcher disp = request.getRequestDispatcher("hello.jsp");
		disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*// TODO Auto-generated method stub
		//doGet(request, response);
		try {
			doRequestAndGenerateTable(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		Integer val = new Integer(1);
		request.setAttribute("name", val);
		RequestDispatcher disp = request.getRequestDispatcher("hello.jsp");
		disp.forward(request, response);
	}

}

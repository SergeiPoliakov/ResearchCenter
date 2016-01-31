package com.unc2016.kolesnikov.authorization.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unc2016.kolesnikov.userinterface.Cases;


@WebServlet("/CreateCaseServlet")
public class CreateCaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Cases caze = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		caze.setCaseName(new String(request.getParameter("type_case").getBytes("ISO-8859-1"),"UTF-8"));
		
		
		
		
		
	}


}

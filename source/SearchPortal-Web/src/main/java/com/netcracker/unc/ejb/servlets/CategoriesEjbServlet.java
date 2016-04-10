package com.netcracker.unc.ejb.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netcracker.unc.newmvc.ejb.controllers.ControllerCategories;
import com.netcracker.unc.newmvc.ejb.entities.EntityUser;

/**
 * Servlet implementation class CategoriesEjbServlet
 */
@WebServlet("/CategoriesEjbServlet")
public class CategoriesEjbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoriesEjbServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		EntityUser user = (EntityUser) request.getSession().getAttribute("user");// тут
																					// пусто
		//ControllerCategories controllerCategories = new ControllerCategories();

		response.getWriter().append("Привет сервлет");
	}
}

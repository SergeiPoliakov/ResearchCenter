package com.netcracker.unc.ejb.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netcracker.unc.newmvc.ejb.controllers.ControllerCategories;
import com.netcracker.unc.newmvc.ejb.entities.EntityUser;
import com.netcracker.unc.newmvc.ejb.models.CategoryModel;
import com.netcracker.unc.newmvc.ejb.models.UserModel;

/**
 * Servlet implementation class CategoriesEjbServlet
 */
@WebServlet("/CategoriesEjbServlet")
public class CategoriesEjbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	ControllerCategories controller;

	@EJB
	UserModel user;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		user.setUser((EntityUser) request.getSession().getAttribute("user"));
		List categoryList = new ArrayList<CategoryModel>();
		categoryList = controller.getCategories(user.getUser());

		request.getSession().setAttribute("categoryList", categoryList);
		// response.sendRedirect("ejb/module-categories/module.jsp");
	}
}

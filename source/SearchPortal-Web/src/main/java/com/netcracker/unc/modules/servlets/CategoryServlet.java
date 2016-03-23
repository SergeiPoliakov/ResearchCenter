package com.netcracker.unc.modules.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netcracker.unc.newmvc.dao.controllers.CategoryController;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/CategoryServlet")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		String action = null;
		int objectId = 0;
		String categoryName = null;
		double coefficient = 0;
		double minPercent = 0;
		double maxPercent = 0;
		int userId = 0;

		action = request.getParameter("action");
		objectId = Integer.parseInt(request.getParameter("objectid"));
		categoryName = request.getParameter("categoryname");
		coefficient = Double.parseDouble(request.getParameter("coefficient"));
		minPercent = Double.parseDouble(request.getParameter("minpercent"));
		maxPercent = Double.parseDouble(request.getParameter("maxpercent"));
		userId = Integer.parseInt(request.getParameter("userid"));

		if (action.equals("delete") && objectId != 0) {
			try {
				CategoryController categoryController = new CategoryController();
				categoryController.deleteCategoty(objectId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (action.equals("add") && coefficient != 0 && minPercent != 0 && maxPercent != 0
				&& !categoryName.equals("null") && userId != 0) {
			try {
				CategoryController categoryController = new CategoryController();
				categoryController.addCategory(categoryName, coefficient, minPercent, maxPercent, userId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		RequestDispatcher disp = request.getRequestDispatcher("/sadpage.jsp");
		disp.forward(request, response);
	}

}

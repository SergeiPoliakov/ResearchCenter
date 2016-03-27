package com.netcracker.unc.modules.servlets;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		request.setCharacterEncoding("UTF-8");

		String action = null;
		int objectId = 0;
		String categoryName = null;
		double coefficient = 0;
		double minPercent = 0;
		double maxPercent = 0;
		int userId = 0;

		action = request.getParameter("action");

		objectId = Integer.parseInt(request.getParameter("objectid"));
		if (checkCategoryName(request.getParameter("categoryname"))) {
			categoryName = request.getParameter("categoryname");
		}
		if (checkCoefficent(request.getParameter("coefficient"))) {
			coefficient = Double.parseDouble(request.getParameter("coefficient"));
		}
		if (checkPercent(request.getParameter("minpercent"))) {
			minPercent = Double.parseDouble(request.getParameter("minpercent"));
		}
		if (checkPercent(request.getParameter("maxpercent"))) {
			maxPercent = Double.parseDouble(request.getParameter("maxpercent"));
		}
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

		RequestDispatcher disp = request.getRequestDispatcher("/welcome.jsp");
		disp.forward(request, response);
	}

	private static boolean checkCoefficent(String inputString) {
		Pattern pattern = Pattern.compile("0[.]{1}[0-9]{1,3}|1|0");
		Matcher matcher = pattern.matcher(inputString);

		return matcher.matches();
	}

	private static boolean checkPercent(String inputString) {
		Pattern pattern = Pattern.compile("[0-9]{1,2}[.]{1}[0-9]{1,3}|100|[0-9]{1,2}");
		Matcher matcher = pattern.matcher(inputString);
		return matcher.matches();
	}

	private static boolean checkCategoryName(String inputString) {
		Pattern pattern = Pattern.compile("[A-Za-zА-Яа-я.0-9_!,() %@#+$&*^:/?=;'<|>-]{3,50}");
		Matcher matcher = pattern.matcher(inputString);

		return matcher.matches();
	}

}

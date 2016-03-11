package com.netcracker.unc.modules.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netcracker.unc.mvc.dao.CategoryDao;
import com.netcracker.unc.priorityModule.CalculationPriority;
import com.netcracker.unc.priorityModule.FillHTMLTable;

/**
 * Servlet implementation class controllerPriorities
 */
@WebServlet("/controllerPriorities")
public class controllerPriorities extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public controllerPriorities() {
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
		int userId = Integer.parseInt(request.getParameter("userId"));
		/*List<ModelForTable> resultList = new ArrayList<ModelForTable>();
		try {
			CategoryDao categoryDao = new CategoryDao();
			List<CategoryModel> categoryList = new ArrayList<CategoryModel>(categoryDao.getAllCategoriesUser(userId));
			double sumInvoice = 5000;// заглушка
			double sumIncome = 5000;// заглушка
			CalculationPriority calculationPriority = new CalculationPriority();
			resultList = new ArrayList<ModelForTable>(
					calculationPriority.convertToTableView(categoryList, sumInvoice, sumIncome));
			//в jsp resultHTMLString = FillHTMLTable.toHTMLString(resultList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			resultList = Collections.emptyList();
		}
		request.setAttribute("dataForPriorityList", resultList);
		RequestDispatcher disp = request.getRequestDispatcher("/sadpage.jsp");
		disp.forward(request, response);*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

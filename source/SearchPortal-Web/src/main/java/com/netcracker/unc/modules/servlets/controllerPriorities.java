package com.netcracker.unc.modules.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netcracker.unc.mvc.models.CategoryModel;
import com.netcracker.unc.priorityModule.CalculationPriority;
import com.netcracker.unc.priorityModule.FillHTMLTable;
import com.netcracker.unc.priorityModule.ModelForTable;

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

		// String select = (String) request.getParameter("select"); получение id
		// от jsp страницы
		/*
		 * 1.Получаем id 2.Передаем его в categoryDao, получаем categoryList
		 * 3.Передаем id, получаем сумму доходов 4.Передаем id, получаем сумму
		 * счетов 5.Закидываем 2,3,4 параметрами в метод класса
		 * calculationpriority, получаем resultList 6.resultList в
		 * FillhtmlTable, получаем htmlResultString 7.Выводим ее на jsp
		 */
		String resultHTMLString;
		try{
		List<CategoryModel> categoryList = new ArrayList<CategoryModel>();
		double sumInvoice = 0;
		double sumIncome = 0;
		List<ModelForTable> resultList = new ArrayList<ModelForTable>(
				CalculationPriority.convertToTableView(categoryList, sumInvoice, sumIncome));
		resultHTMLString = FillHTMLTable.toHTMLString(resultList);
		}
		catch(Exception e){
			resultHTMLString = "error in servlet";
		}
		request.setAttribute("resultHTMLString", resultHTMLString);
		RequestDispatcher disp = request.getRequestDispatcher("/sadpage.jsp");
		disp.forward(request, response);
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

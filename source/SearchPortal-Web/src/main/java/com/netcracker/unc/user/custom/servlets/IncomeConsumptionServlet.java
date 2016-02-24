package com.netcracker.unc.user.custom.servlets;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.netcracker.unc.beans.custom.models.IncomeConsumptionModel;

/**
 * Servlet implementation class IncomeConsumptionServlet
 */
@WebServlet("/attitudes/IncomeConsumption")
public class IncomeConsumptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	IncomeConsumptionModel inCons;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		inCons.procentForBar();

		int progress = 0;
		if (inCons.getFullConsumption() != 0)
			progress = (inCons.getFullIncome() * 100) / (inCons.getFullIncome() + inCons.getFullConsumption());

		request.setAttribute("progress", progress);
		request.setAttribute("maxIncomeCost", inCons.getMaxIncomeName());
		request.setAttribute("maxConsumptionCost", inCons.getMaxConsumptionName());
		request.setAttribute("maxIncomeCostInt", inCons.getMaxIncome());
		request.setAttribute("maxConsumptionCostInt", inCons.getMaxConsumption());
		request.setAttribute("avgIncome", inCons.getAvgIncome());
		request.setAttribute("avgConsumption", inCons.getAvgConsumption());
		request.setAttribute("percentString", progress + "/" + (100 - progress));

		RequestDispatcher dispatcher = request.getRequestDispatcher("/attitudes/income_consumption.jsp");
		dispatcher.include(request, response);

	}

}

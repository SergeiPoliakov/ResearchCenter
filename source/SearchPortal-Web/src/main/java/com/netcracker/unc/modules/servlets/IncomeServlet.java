package com.netcracker.unc.modules.servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netcracker.unc.newmvc.dao.IncomeDAO;
import com.netcracker.unc.newmvc.dao.models.IncomeModel;
import com.netcracker.unc.newmvc.dao.models.UserModel;


@WebServlet("/IncomeServlet")
public class IncomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public IncomeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserModel user = null;
		IncomeModel income = new IncomeModel();
		user = (UserModel) request.getSession().getAttribute("user");
		IncomeDAO incomedao = new IncomeDAO(user);

		if ((request.getParameter("income-name") != null)
				&& (!request.getParameter("income-name").trim().isEmpty())) {
			income.setIncomeName(request.getParameter("income-name"));

			if ((request.getParameter("income-date") != null)
					&& (!request.getParameter("income-date").trim().isEmpty())) {
				income.setDateIncome(Date.valueOf(request.getParameter("income-date")));
			}
			if ((request.getParameter("income-sum") != null)
					&& (!request.getParameter("income-sum").trim().isEmpty())) {
				income.setIncomeSum(Integer.valueOf(request.getParameter("income-sum")));
			}
			if ((request.getParameter("income-month") != null)
					&& (!request.getParameter("income-month").trim().isEmpty())) {
				income.setMonth(Boolean.valueOf(request.getParameter("income-month")));
			}
			if ((request.getParameter("invoice-number") != null)
					&& (!request.getParameter("invoice-number").trim().isEmpty())) {
				income.setInvoiceId(Integer.valueOf(request.getParameter("invoice-number")));
			}
			
			incomedao.addIncome(income, user);

		} else if ((request.getParameter("income-delete-number") != null)
				&& (!request.getParameter("income-delete-number").trim().isEmpty())) {
			int incomeNumber = Integer.valueOf(request.getParameter("income-delete-number"));
			income.setIncomeId(Integer.valueOf(incomeNumber));
			incomedao.deleteIncome(incomeNumber);
		}
		response.sendRedirect("welcome.jsp");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

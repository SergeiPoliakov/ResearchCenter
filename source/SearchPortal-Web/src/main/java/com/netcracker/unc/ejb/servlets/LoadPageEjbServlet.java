package com.netcracker.unc.ejb.servlets;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.netcracker.unc.newmvc.ejb.controllers.ControllerActions;
import com.netcracker.unc.newmvc.ejb.controllers.ControllerUsers;
import com.netcracker.unc.newmvc.ejb.entities.EntityObject;
import com.netcracker.unc.newmvc.ejb.entities.EntityParam;
import com.netcracker.unc.newmvc.ejb.entities.EntityUser;
import com.netcracker.unc.newmvc.ejb.models.IncomeConsumptionModel;
import com.netcracker.unc.newmvc.ejb.models.SalaryModel;

/**
 * Servlet implementation class PageCheckLoadEjbServlet
 */
@WebServlet("/load")
public class LoadPageEjbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// columns names od fatabase
	// private final int incomeType = 2; // Доход
	private final String objectName = "Зарплата";
	private final String valueCheck = "true";
	/////////////

	@EJB
	ControllerUsers usContr;
	@EJB
	ControllerActions actContr;

	private void checkCookie(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityUser user = null;
		request.setAttribute("page", "page");
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equals("userID")) {
					user = (EntityUser) usContr.getUserById(Long.valueOf(cookie.getValue()));
					request.getSession().setAttribute("user", user);
					request.setAttribute("page", "page-ok");
				}
			}
		} else if (request.getSession().getAttribute("user") != null) {
			request.setAttribute("page", "page-ok");
		}

	}

	private void checkSalary(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EntityUser user = (EntityUser) request.getSession().getAttribute("user");
		request.setAttribute("checkSalary", "error");
		for (EntityObject object : user.getUserObjects()) {
			if (object.getObjectName().toLowerCase().equals(objectName.toLowerCase())) {
				for (EntityParam param : object.getObjectParams()) {
					if (param.getValue() != null && param.getValue().toLowerCase().equals(valueCheck.toLowerCase()))
						request.setAttribute("checkSalary", "ok");
					else
						continue;
				}
			}
		}
		if (request.getAttribute("checkSalary").equals("ok"))
			controlSalary(request, response, user);
	}

	private void controlSalary(HttpServletRequest request, HttpServletResponse response, EntityUser user)
			throws ServletException, IOException {

		SalaryModel salary = usContr.getLastCheckSalary(user.getUserId());
		if (salary.getDateCount() > 0 && request.getAttribute("checkSalaryBro") == null)
			request.setAttribute("controlSalary", salary);
		else
			request.setAttribute("controlSalary", null);
	}

	private void viewIncomeConsumptionOverlay(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		IncomeConsumptionModel inCon = new IncomeConsumptionModel();
		long progress = 0;
		EntityUser user = (EntityUser) request.getSession().getAttribute("user");
		inCon = usContr.procentForBar(inCon, user.getUserId());

		if (inCon.getFullConsumption() != 0 || inCon.getFullIncome() != 0) {
			progress = (inCon.getFullIncome() * 100) / (inCon.getFullIncome() + inCon.getFullConsumption());
			request.setAttribute("percentString", progress + "/" + (100 - progress));
		} else
			request.setAttribute("percentString", "0/0");

		request.setAttribute("progress", progress);
		request.setAttribute("maxIncomeCost", inCon.getMaxIncomeName());
		request.setAttribute("maxConsumptionCost", inCon.getMaxConsumptionName());
		request.setAttribute("maxIncomeCostInt", inCon.getMaxIncome());
		request.setAttribute("maxConsumptionCostInt", inCon.getMaxConsumption());
		request.setAttribute("avgIncome", inCon.getAvgIncome());
		request.setAttribute("avgConsumption", inCon.getAvgConsumption());

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		checkCookie(request, response);

		if (request.getAttribute("jsp") != null) {
			checkSalary(request, response);
			viewIncomeConsumptionOverlay(request, response);
		}
	}
}

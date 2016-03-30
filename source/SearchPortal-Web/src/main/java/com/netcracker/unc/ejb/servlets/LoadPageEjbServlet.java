package com.netcracker.unc.ejb.servlets;

import java.io.IOException;
import java.util.List;

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
import com.netcracker.unc.newmvc.ejb.models.UserModel;

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
	@EJB
	SalaryModel salaryModel;
	@EJB
	IncomeConsumptionModel inConModel;
	@EJB
	UserModel user;

	private void checkCookie(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("page", "page");
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equals("userID")) {
					EntityUser user = (EntityUser) usContr.getUserById(Long.valueOf(cookie.getValue()));
					this.user.setUser(user);
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

		request.setAttribute("checkSalary", "error");
		//EntityUser user = (EntityUser) request.getSession().getAttribute("user");
		for (EntityObject object : user.getUser().getUserObjects()) {
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
			controlSalary(request, response, user.getUser());
	}

	private void controlSalary(HttpServletRequest request, HttpServletResponse response, EntityUser user)
			throws ServletException, IOException {

		salaryModel = usContr.getLastCheckSalary(user.getUserId());
		if (salaryModel.getDateCount() > 0 && request.getAttribute("checkSalaryBro") == null)
			request.setAttribute("controlSalary", salaryModel);
		else
			request.setAttribute("controlSalary", null);
	}

	private void viewIncomeConsumptionOverlay(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		inConModel = new IncomeConsumptionModel();
		long progress = 0;
		//EntityUser user = (EntityUser) request.getSession().getAttribute("user");
		inConModel = usContr.procentForBar(inConModel, user.getUser().getUserId());
		if (inConModel.getFullConsumption() != 0 || inConModel.getFullIncome() != 0) {
			progress = (inConModel.getFullIncome() * 100)
					/ (inConModel.getFullIncome() + inConModel.getFullConsumption());
			request.setAttribute("percentString", progress + "/" + (100 - progress));
		} else
			request.setAttribute("percentString", "0/0");

		request.setAttribute("progress", progress);
		request.setAttribute("maxIncomeCost", inConModel.getMaxIncomeName());
		request.setAttribute("maxConsumptionCost", inConModel.getMaxConsumptionName());
		request.setAttribute("maxIncomeCostInt", inConModel.getMaxIncome());
		request.setAttribute("maxConsumptionCostInt", inConModel.getMaxConsumption());
		request.setAttribute("avgIncome", inConModel.getAvgIncome());
		request.setAttribute("avgConsumption", inConModel.getAvgConsumption());

	}

	private void doCreateCaseSelect(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//EntityUser user = (EntityUser) request.getSession().getAttribute("user");
		List<EntityObject> userActiveCases = usContr.getUserActiveCases(user.getUser().getUserId());
		List<EntityObject> userGeneralCases = usContr.getUserGeneralCases(user.getUser().getUserId());
		request.setAttribute("listCases", userActiveCases);
		request.setAttribute("generalCases", userGeneralCases);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		checkCookie(request, response);

		if (request.getAttribute("jsp") != null && request.getAttribute("page").equals("page-ok")) {
			checkSalary(request, response);
			viewIncomeConsumptionOverlay(request, response);
			if (request.getAttribute("jsp").equals("create-case"))
				doCreateCaseSelect(request, response);
		}
	}
}

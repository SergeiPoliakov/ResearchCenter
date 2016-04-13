package com.netcracker.unc.ejb.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netcracker.unc.newmvc.ejb.controllers.ControllerObjects;
import com.netcracker.unc.newmvc.ejb.controllers.ControllerUsers;
import com.netcracker.unc.newmvc.ejb.entities.EntityUser;
import com.netcracker.unc.newmvc.ejb.models.SalaryModel;
import com.netcracker.unc.newmvc.ejb.models.UserModel;

/**
 * Servlet implementation class CustomEjbServlet
 */
@WebServlet("/cust")
public class CustomEjbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String mainUrl = "ejb/welcome.jsp";
	@EJB
	ControllerUsers usContr;
	@EJB
	ControllerObjects objContr;
	@EJB
	UserModel user;

	private void addSalary(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String salary = request.getParameter("salary");
		usContr.addUserSalary(salary, user.getUser().getUserId());

		response.sendRedirect(mainUrl);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void recordMissSalary(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SalaryModel salary = (SalaryModel) request.getSession().getAttribute("controlSalary");
		String[] mounthsControl = request.getParameterValues("controlSalaryInputTable");
		String newSalary = request.getParameter("controlSalaryInput");

		usContr.addMissUserSalary(salary, mounthsControl, newSalary, user.getUser());

		response.sendRedirect(mainUrl);
	}

	// option: 0 - reset balance; 1 = change balance
	private void resetUserBalance(HttpServletRequest request, HttpServletResponse response, int option)
			throws ServletException, IOException {

		if (option == 0)
			objContr.resetUserBalance(user.getUser());
		else if (option == 1) {
			long objId = Long.valueOf(request.getParameter("balObjId"));
			String cost = request.getParameter("balTextCost");
			objContr.changeUserBalance(objId, cost);
		}

		response.sendRedirect(mainUrl);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		user.setUser((EntityUser) request.getSession().getAttribute("user"));

		String custom = request.getParameter("custom");

		if (custom.equals("addSalary"))
			addSalary(request, response);
		else if (custom.equals("controlSalary"))
			recordMissSalary(request, response);
		else if (custom.equals("resetBalance"))
			resetUserBalance(request, response, 0);
		else if (custom.equals("changeBalance"))
			resetUserBalance(request, response, 1);

	}

}

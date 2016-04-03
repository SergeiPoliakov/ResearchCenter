package com.netcracker.unc.ejb.servlets;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EntityUser user = (EntityUser) request.getSession().getAttribute("user");
		this.user.setUser(user);

		String custom = request.getParameter("custom");

		if (custom.equals("addSalary"))
			addSalary(request, response);
		else if (custom.equals("controlSalary"))
			recordMissSalary(request, response);

	}

}

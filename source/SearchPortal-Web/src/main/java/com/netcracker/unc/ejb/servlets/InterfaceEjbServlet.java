package com.netcracker.unc.ejb.servlets;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.netcracker.unc.newmvc.ejb.controllers.ControllerObjects;
import com.netcracker.unc.newmvc.ejb.entities.EntityUser;
import com.netcracker.unc.newmvc.ejb.models.UserModel;

@WebServlet("/int")
public class InterfaceEjbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	ControllerObjects objContr;
	@EJB
	UserModel user;
	private final String mainUrl = "ejb/welcome.jsp";

	private void createCase(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String caseNameStr = request.getParameter("name_case");
		long caseTypeLong = Long.valueOf(request.getParameter("type_case"));
		long caseParentLong = 0;
		if (request.getParameter("parentBlock") != null)
			caseParentLong = Long.valueOf(request.getParameter("parentBlock"));
		String casePriorityStr = request.getParameter("priority");
		String caseDateStr = request.getParameter("date_case");
		String caseCostStr = request.getParameter("cost_case");

		objContr.createCase(caseNameStr, caseTypeLong, caseParentLong, casePriorityStr, caseDateStr, caseCostStr, user.getUser());
		response.sendRedirect(mainUrl);

	}

	private void updateCase(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nameCase = request.getParameter("nameCase");
		String costCase = request.getParameter("costCase");
		String dateCase = request.getParameter("dateCase");
		String priorityCase = request.getParameter("priorityCase");
		long objectId = Integer.valueOf(request.getParameter("caseId"));

		objContr.updateUserCase(nameCase, costCase, dateCase, priorityCase, objectId, user.getUser());
		response.sendRedirect(mainUrl);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EntityUser user = (EntityUser) request.getSession().getAttribute("user");
		this.user.setUser(user);
		String interfaces = request.getParameter("interfaces");

		if (interfaces.equals("createCase"))
			createCase(request, response);
		else if (interfaces.equals("updateCase"))
			updateCase(request, response);
		/*
		 * else if (interfaces.equals("updateUser")) { updateUser(request,
		 * response); }
		 */
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}

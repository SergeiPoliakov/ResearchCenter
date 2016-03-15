package com.netcracker.unc.user.custom.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netcracker.unc.newmvc.dao.controllers.ObjectController;
import com.netcracker.unc.newmvc.dao.controllers.ParamController;
import com.netcracker.unc.newmvc.dao.models.ObjectModel;
import com.netcracker.unc.newmvc.dao.models.UserModel;

@WebServlet("/control")
public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private void caseControl(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// constants for test
		final String dateStr = "2016-03-10";
		final int objectId = 112;
		final String salary = "250";
		////////

		UserModel user = (UserModel) request.getSession().getAttribute("user");
		ParamController paramController = new ParamController();
		Date date = paramController.convertToDate(dateStr);
		Date curDate = new Date(Calendar.getInstance().getTimeInMillis());

		if (objectId != 0) {
			ObjectController objectController = new ObjectController();
			ObjectModel object = objectController.getObjectById(user, objectId);

			if (date.before(curDate)) {
				request.setAttribute("caseInterviewAttention", "warning");
				request.setAttribute("caseInterviewObject", object);
				request.setAttribute("caseInterviewDate", date.toString());
				request.setAttribute("caseInterviewSalary", salary);
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/attitudes/case_interview.jsp");
		dispatcher.include(request, response);

	}

	private void caseInterview(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pay = request.getParameter("caseIntText");

		request.setAttribute("caseInterviewPay", pay);
		response.sendRedirect("modules.jsp");

		System.out.println("Пользователь оплатил " + pay);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String control = request.getParameter("control");

		if (control == null)
			caseControl(request, response);
		else if (control.equals("interviewCase"))
			caseInterview(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

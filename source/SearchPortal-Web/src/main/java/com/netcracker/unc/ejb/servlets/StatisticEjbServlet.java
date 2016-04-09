package com.netcracker.unc.ejb.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netcracker.unc.newmvc.ejb.dao.EjbDAO;
import com.netcracker.unc.newmvc.ejb.entities.EntityUser;
import com.netcracker.unc.newmvc.ejb.models.StatisticModel;
import com.netcracker.unc.newmvc.ejb.models.UserModel;

/**
 * Servlet implementation class StatisticEjbServlet
 */
@WebServlet("/stat")
public class StatisticEjbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	EjbDAO ejb;
	@EJB
	UserModel user;

	protected void getStatistic(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		StatisticModel statMod = ejb.procentForPie(user.getUser().getUserId());
		request.getSession().setAttribute("statisticModel", statMod);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		user.setUser((EntityUser) request.getSession().getAttribute("user"));
		getStatistic(request, response);
	}

}

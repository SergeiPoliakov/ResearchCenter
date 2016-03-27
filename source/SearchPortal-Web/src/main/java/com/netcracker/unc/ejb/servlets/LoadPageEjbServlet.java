package com.netcracker.unc.ejb.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netcracker.unc.newmvc.ejb.controllers.ControllerUsers;
import com.netcracker.unc.newmvc.ejb.entities.EntityObject;
import com.netcracker.unc.newmvc.ejb.entities.EntityUser;

/**
 * Servlet implementation class PageCheckLoadEjbServlet
 */
@WebServlet("/load")
public class LoadPageEjbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	ControllerUsers usContr;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityUser user = (EntityUser) request.getSession().getAttribute("user");
		List<EntityObject> userActiveObjects = usContr.getUserActiveCases(user.getUserId());
		request.getSession().setAttribute("activeCases", userActiveObjects);
	}

}

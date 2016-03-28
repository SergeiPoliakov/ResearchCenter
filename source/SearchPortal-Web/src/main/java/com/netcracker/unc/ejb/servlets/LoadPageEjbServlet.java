package com.netcracker.unc.ejb.servlets;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.netcracker.unc.newmvc.ejb.controllers.ControllerUsers;
import com.netcracker.unc.newmvc.ejb.entities.EntityUser;

/**
 * Servlet implementation class PageCheckLoadEjbServlet
 */
@WebServlet("/load")
public class LoadPageEjbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	ControllerUsers usContr;

	private void checkCookie(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityUser user = null;
		request.setAttribute("page", "page");
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				System.out.println(cookie.getName());
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getAttribute("page").equals("loading"))
			checkCookie(request, response);

		/*
		 * EntityUser user = (EntityUser)
		 * request.getSession().getAttribute("user"); List<EntityObject>
		 * userActiveObjects = usContr.getUserActiveCases(user.getUserId());
		 * request.getSession().setAttribute("activeCases", userActiveObjects);
		 */
	}

}

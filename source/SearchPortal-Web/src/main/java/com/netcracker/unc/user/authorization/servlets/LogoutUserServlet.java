package com.netcracker.unc.user.authorization.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutUser")
public class LogoutUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Cookie[] cookie = null;
	private HttpSession session = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		cookie = request.getCookies();
		if (cookie != null) { // delete all user cookies
			for (Cookie c : cookie) {
				c.setValue("");
				c.setPath("");
				c.setMaxAge(0);
				response.addCookie(c);
			}
		}
		session = request.getSession(false); // for delete current user session
		if (session != null) {
			request.getSession().invalidate();
		}
		response.sendRedirect("index.jsp");
	}
}

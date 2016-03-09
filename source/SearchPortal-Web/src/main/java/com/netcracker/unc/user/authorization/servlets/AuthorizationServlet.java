package com.netcracker.unc.user.authorization.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.netcracker.unc.newmvc.dao.UserController;
import com.netcracker.unc.newmvc.dao.UserDAO;
import com.netcracker.unc.newmvc.dao.UserModel;

@WebServlet("/authorization")
public class AuthorizationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private void authorizationCheck(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		UserController userController = new UserController();

		String login = request.getParameter("login");
		String password = request.getParameter("password");

		UserModel user = userController.checkAndGetUser(login, password);
		// if user is correct
		if (user.getUserId() != 0) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setMaxInactiveInterval(30 * 60);
			Cookie cookie = new Cookie("userID", String.valueOf(user.getUserId()));
			cookie.setMaxAge(24 * 60 * 60);
			response.addCookie(cookie);
			response.sendRedirect("modules.jsp");
		} else {
			System.out.println("error");
			request.setAttribute("errorMessage", "Неправильно введен логин или пароль!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.include(request, response);
		}
	}

	private void registrationUser(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String name = request.getParameter("name");

		UserController userController = new UserController();
		if (userController.createUser(login, password, name)) {
			UserDAO userDAO = new UserDAO();
			UserModel user = userDAO.getUserByLogin(login);

			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setMaxInactiveInterval(30 * 60);
			Cookie cookie = new Cookie("userID", String.valueOf(user.getUserId()));
			cookie.setMaxAge(24 * 60 * 60);
			response.addCookie(cookie);

			response.sendRedirect("modules.jsp");
		} else {
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equals("page")) {
					cookie.setValue("first");
				} else {
					cookie = new Cookie("page", "first");
				}
				response.addCookie(cookie);
			}
			request.setAttribute("multiName", "Извините, такой логин уже используется!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.include(request, response);
		}
	}

	private void logOutUser(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		Cookie[] cookie = request.getCookies();
		if (cookie != null) { // delete all user cookies
			for (Cookie c : cookie) {
				c.setValue("");
				c.setPath("");
				c.setMaxAge(0);
				response.addCookie(c);
			}
		}
		HttpSession session = request.getSession(false); // for delete current
															// user session
		if (session != null) {
			request.getSession().invalidate();
		}
		response.sendRedirect("index.jsp");
	}

	private void cookieLogInUser(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		int userId = 0;
		UserDAO userDAO = new UserDAO();
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals("userID")) {
				userId = Integer.valueOf(cookie.getValue());
			}
		}
		UserModel user = userDAO.getUser(userId);
		request.getSession().setAttribute("user", user);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		if (request.getParameter("authorization") != null) {
			String authorization = request.getParameter("authorization");
			if (authorization.equals("userLogin"))
				authorizationCheck(request, response);
			else if (authorization.equals("userRegistration"))
				registrationUser(request, response);
			else if (authorization.equals("logOut"))
				logOutUser(request, response);
		} else if (request.getAttribute("checkCookie") != null)
			cookieLogInUser(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

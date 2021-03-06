package com.netcracker.unc.ejb.servlets;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.netcracker.unc.newmvc.ejb.controllers.ControllerUsers;
import com.netcracker.unc.newmvc.ejb.dao.EjbDAO;
import com.netcracker.unc.newmvc.ejb.entities.EntityUser;
import com.netcracker.unc.newmvc.ejb.models.UserModel;

@WebServlet("/auth")
public class AuthorizationEjbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String mainUrl = "ejb/welcome.jsp";
	private final String indexUrl = "ejb/index.jsp";
	@EJB
	ControllerUsers usContr;
	@EJB
	UserModel user;
	@EJB
	EjbDAO ejb;

	private void authorizationCheck(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String login = request.getParameter("login");
		String password = request.getParameter("password");
		EntityUser user = usContr.checkAndGetUser(login, password);
		// if user is correct
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setMaxInactiveInterval(30 * 60);
			Cookie cookie = new Cookie("userID", String.valueOf(user.getUserId()));
			cookie.setMaxAge(48 * 60 * 60);
			response.addCookie(cookie);
			response.sendRedirect(mainUrl);
		} else {
			request.setAttribute("errorMessage", "Неправильно введен логин или пароль!");
			RequestDispatcher dispatcher = request.getRequestDispatcher(indexUrl);
			dispatcher.include(request, response);
		}

	}

	private void registrationUser(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");

		if (usContr.createUser(login, password, name, email)) {
			EntityUser user = usContr.getUserByLogin(login);
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setMaxInactiveInterval(30 * 60);
			Cookie cookie = new Cookie("userID", String.valueOf(user.getUserId()));
			cookie.setMaxAge(24 * 60 * 60);
			response.addCookie(cookie);
			response.sendRedirect(mainUrl);
		}
		request.setAttribute("multiName", "Извините, такой логин уже используется!");
		RequestDispatcher dispatcher = request.getRequestDispatcher(indexUrl);
		dispatcher.include(request, response);
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
		response.sendRedirect("");
	}

	private void restoreUserPassword(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String login = request.getParameter("login");
		String email = request.getParameter("email");
		user.setUser(usContr.getUserByLogin(login));

		if (user.getUser() != null && user.getUser().getEmail().toLowerCase().equals(email.toLowerCase())) {

			String newPassword = usContr.generateUserPassword(5);
			user.getUser().setHashSum(newPassword.hashCode());
			user.getUser().setSalt(usContr.createSalt(newPassword));
			ejb.updateObject(user.getUser());

			usContr.sendPasswordToUserEmail(user.getUser().getEmail(), newPassword);
		}

		request.setAttribute("restorePassword",
				"Если логин и почта совпали, письмо с паролем было выслано Вам на почту!");

		RequestDispatcher dispatcher = request.getRequestDispatcher(indexUrl);
		dispatcher.forward(request, response);

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
			else if (authorization.equals("restorePassword"))
				restoreUserPassword(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
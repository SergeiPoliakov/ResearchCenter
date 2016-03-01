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

import com.netcracker.unc.mvc.ObjectController;
import com.netcracker.unc.mvc.models.UserModel;

@WebServlet("/LoginUser")
public class LoginUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserModel user = null;
	private ObjectController controller = new ObjectController();
	private Cookie cookie = null; // for save session by user
	private HttpSession session = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		user = new UserModel();

		response.setContentType("text/html;charset=UTF-8");
		String login = request.getParameter("login");
		String password = request.getParameter("password");

		user.set_login(login);
		user.createHashAndSalt(password); // hash md5 user password and set
											// it
											// to attributes

		user = controller.checkUserLoginAndPassword(user); // for check
															// login
															// and password

		if (user != null) { // if login is correct

			session = request.getSession();
			session.setAttribute("user", user);
			session.setMaxInactiveInterval(30 * 60);
			cookie = new Cookie("userID", String.valueOf(user.get_user_id()));
			cookie.setMaxAge(24 * 60 * 60);
			response.addCookie(cookie);
			response.sendRedirect("modules.jsp");

		} else {
			request.setAttribute("errorMessage", "Неправильно введен логин или пароль!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.include(request, response);
		}

	}

}

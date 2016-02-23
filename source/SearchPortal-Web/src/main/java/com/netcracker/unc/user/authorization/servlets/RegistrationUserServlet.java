package com.netcracker.unc.user.authorization.servlets;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.netcracker.unc.mvc.dao.UserDAO;
import com.netcracker.unc.mvc.models.UserModel;

@WebServlet(value = "/RegistrationUser")
public class RegistrationUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserModel user = null;
	private UserDAO userDAO = null;
	// regular check input data
	private Pattern loginPat = Pattern.compile("^[A-Za-z0-9]{1,15}$");
	private Pattern passwordPat = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).[^ ]{5,10}$");
	private Pattern namePat = Pattern.compile("^[A-Za-zА-Яа-я']{1,10}$");
	private Matcher loginMat = null;
	private Matcher passwordMat = null;
	private Matcher nameMat = null;
	private Cookie cookie = null; // for save session by user
	private HttpSession session = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String name = request.getParameter("name");

		// check input data
		System.out.println(login);
		System.out.println(password);
		System.out.println(name);

		user = new UserModel();
		userDAO = new UserDAO();

		user.set_login(login);
		loginMat = loginPat.matcher(login);
		// check correct login
		if (!loginMat.matches()) {
			request.setAttribute("incorrectLogin",
					"Логин должен состоять только из букв и цифер (не более 15 символов)");
		} else
			user.set_login(login);

		// check correct password
		passwordMat = passwordPat.matcher(password);
		if (!passwordMat.matches()) {
			request.setAttribute("incorrectPassword",
					"Пароль должен состоять из цифер, латинских букв нижнего и верхнего регистров (не менее 5 и не более 10 символов)");
		} else {
			user.createHashAndSalt(password);
			user.set_account_type("user");
		}

		// check correct name if have to input
		if (!name.isEmpty()) {
			nameMat = namePat.matcher(name);
			if (!nameMat.matches()) {
				request.setAttribute("incorrectName", "Имя должно состоять только из букв (не более 10 символов)");
			} else
				user.set_name(name);
		}

		if (loginMat.matches() && passwordMat.matches() && name.length() < 1
				|| loginMat.matches() && passwordMat.matches() && nameMat.matches()) {

			// check login and password
			boolean check = false;
			List<Object> list = userDAO.getAllObjectsDB();
			UserModel userList = null;

			for (int i = 0; i < list.size(); i++) {
				userList = (UserModel) list.get(i);
				if (user.get_login().toLowerCase().equals(userList.get_login().toLowerCase())) {
					check = true;
					System.out.println(userList.get_login());
				}
			}
			// if user is real new
			if (!check) {
				userDAO.addObject(user);
				user = (UserModel) userDAO.getObject(user);

				session = request.getSession();
				session.setAttribute("user", user);
				session.setMaxInactiveInterval(30 * 60);
				cookie = new Cookie("userID", String.valueOf(user.get_user_id()));
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
		} else {
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equals("page")) {
					cookie.setValue("first");
				} else {
					cookie = new Cookie("page", "first");
				}
				response.addCookie(cookie);
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.include(request, response);
		}
	}
}

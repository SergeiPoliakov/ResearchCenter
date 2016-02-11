package com.netcracker.unc.user.authorization.servlets;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.netcracker.unc.mvc.dao.UserDAO;
import com.netcracker.unc.mvc.models.UserModel;

@WebServlet(value = "/authorization/RegistrationUser")
public class RegistrationUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserModel user = new UserModel();
	private UserDAO userDAO = new UserDAO();
	// regular check input data
	private Pattern loginPat = Pattern.compile("^[A-Za-z0-9]{1,15}$");
	private Pattern passwordPat = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).[^ ]{5,10}$");
	private Pattern namePat = Pattern.compile("^[A-Za-z�-��-�']{1,10}$");
	private Matcher loginMat = null;
	private Matcher passwordMat = null;
	private Matcher nameMat = null;

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

		user.set_login(login);
		loginMat = loginPat.matcher(login);
		// check correct login
		if (!loginMat.matches()) {
			request.setAttribute("incorrectLogin",
					"Incorrect login, must consist of english letters and no more 15 symbols");
		} else
			user.set_login(login);

		// check correct password
		passwordMat = passwordPat.matcher(password);
		if (!passwordMat.matches()) {
			request.setAttribute("incorrectPassword",
					"Incorrect password, must consist of english upper-lower case letters, numbers, no less 5 and no more 10 symbols");
		} else {
			user.createHashAndSalt(password);
			user.set_account_type("user");
		}

		// check correct name if have to input
		if (!name.isEmpty()) {
			nameMat = namePat.matcher(name);
			if (!nameMat.matches()) {
				request.setAttribute("incorrectName",
						"Incorrect name, must consist of only letters and no more 10 symbols");
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
				if (user.get_login().equals(userList.get_login())) {
					check = true;
					System.out.println(userList.get_login());
				}
			}
			// if user is real new
			if (!check) {
				userDAO.addObject(user);
				user = (UserModel) userDAO.getObject(user);
				request.setAttribute("loginComplete", user.get_login());
				request.setAttribute("welcomeNewUser", "Thank you and Welcome!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/authorization/welcome_user.jsp");
				dispatcher.include(request, response);
				userDAO.connectionClose();
			} else {
				request.setAttribute("multiName", "Sorry, this login already use");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/authorization/registration_user.jsp");
				dispatcher.include(request, response);
			}
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/authorization/registration_user.jsp");
			dispatcher.include(request, response);
		}
	}
}

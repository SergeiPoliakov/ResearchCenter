package com.unc2016.kolesnikov.authorization.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unc2016.kolesnikov.dao.SiteOfPrioritiesObjects;
import com.unc2016.kolesnikov.dao.UsersDAO;
import com.unc2016.kolesnikov.mvc.UsersPOJO;

@WebServlet("/authorization/LoginUserServlet")
public class LoginUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsersDAO userDAO = new UsersDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		String login = request.getParameter("login");
		String password = request.getParameter("password");

		System.out.println(login);
		System.out.println(password);

		UsersPOJO user = new UsersPOJO();

		// get all users from database
		List<SiteOfPrioritiesObjects> list = userDAO.getAllObjectsDB();
		UsersPOJO userList = null;

		user.setLogin(login);
		user.createHashAndSalt(password);

		// check login and password
		boolean check = false;

		for (int i = 0; i < list.size(); i++) {
			userList = (UsersPOJO) list.get(i);
			if (user.getLogin().equals(userList.getLogin()) && user.getHashSum() == userList.getHashSum()
					&& user.getSalt() == userList.getSalt()) {
				check = true;
			}
		}

		if (check) {
			user = (UsersPOJO) userDAO.getObject(user);
			request.setAttribute("loginComplete", user.getLogin());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/authorization/welcome_user.jsp");
			dispatcher.include(request, response);
		} else {
			request.setAttribute("errorMessage", "Incorrect login or password!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/authorization/authorization_user.jsp");
			dispatcher.include(request, response);
		}

	}

}

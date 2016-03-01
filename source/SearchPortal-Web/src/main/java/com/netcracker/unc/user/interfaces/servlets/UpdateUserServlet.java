package com.netcracker.unc.user.interfaces.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.netcracker.unc.mvc.dao.UserDAO;
import com.netcracker.unc.mvc.models.UserModel;

@WebServlet("/interface/UpdateUser")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserModel user = null;
	private UserDAO userDAO = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		user = new UserModel();
		userDAO = new UserDAO();

		String name = request.getParameter("name");
		String oldPas = request.getParameter("oldPas");
		String newPas = request.getParameter("newPas");
		System.out.println(name + " " + oldPas + " " + newPas);

		// for check current password
		UserModel userPas = new UserModel();
		userPas.createHashAndSalt(oldPas);

		user.set_user_id(1);
		user = (UserModel) userDAO.getObject(user);

		// if old password is correct
		if (userPas.get_hash_sum() == user.get_hash_sum() && userPas.get_salt() == user.get_salt()) {
			// user = (UserModel) request.getSession().getAttribute("user");
			user.set_name(name);
			user.createHashAndSalt(newPas);
			userDAO.updateObject(user);
			request.setAttribute("errorPassword", "Данные изменены!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/interface/update_user.jsp");
			dispatcher.include(request, response);
		} else {
			request.setAttribute("errorPassword", "Неправильно введен старый пароль!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/interface/update_user.jsp");
			dispatcher.include(request, response);
		}
	}

}

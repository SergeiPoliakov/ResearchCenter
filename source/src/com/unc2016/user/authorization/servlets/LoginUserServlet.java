package com.unc2016.user.authorization.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.unc2016.mvc.ObjectController;
import com.unc2016.mvc.models.UserModel;

@WebServlet("/LoginUser")
public class LoginUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserModel user = null;
	private ObjectController controller = new ObjectController();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		user = new UserModel();
		
		response.setContentType("text/html;charset=UTF-8");
		String login = request.getParameter("login");
		String password = request.getParameter("password");

		System.out.println(login);
		System.out.println(password);

		user.set_login(login);
		user.createHashAndSalt(password); // hash md5 user password and set it
											// to attributes

		user = controller.checkUserLoginAndPassword(user); // for check login
															// and password

		if (user != null) {
			request.setAttribute("loginComplete", user.get_login());
			RequestDispatcher dispatcher = request.getRequestDispatcher("authorization/welcome_user.jsp");
			dispatcher.include(request, response);
		} else {
			request.setAttribute("errorMessage", "Incorrect login or password!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/kolmain.jsp");
			dispatcher.include(request, response);
		}

	}

}

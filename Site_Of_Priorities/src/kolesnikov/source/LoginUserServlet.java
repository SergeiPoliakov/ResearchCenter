package kolesnikov.source;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LoginUserServlet")
public class LoginUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		System.out.println(login);
		System.out.println(password);
		
		Users loginUser = new Users();
		loginUser.setLogin(login);
		loginUser.setHashSum(password);
		
		
		
		//если пользователь ввел корректно
		if(login.length()>1 && password.length()>1) {
			ObjectsController controller = new ObjectsController();
			boolean check = controller.checkPassword(loginUser);
			if(check) {
				Users user = (Users)controller.getObjectFromeDatabase(loginUser);
				request.setAttribute("loginComplete", user.getLogin());
				RequestDispatcher dispatcher = request.getRequestDispatcher("/welcome_user.jsp");
				dispatcher.include(request, response);
				
			}
			else {
				request.setAttribute("errorMessage", "Неправильно введен логин или пароль");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/authorization_user.jsp");
				dispatcher.include(request, response);
			}
		}
		else {
			request.setAttribute("errorMessage", "Неправильно введен логин или пароль");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/authorization_user.jsp");
			dispatcher.include(request, response);
		}	
	}

}

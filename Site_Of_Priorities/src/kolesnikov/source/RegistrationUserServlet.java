package kolesnikov.source;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/RegistrationUserServlet")
public class RegistrationUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; 
    private Users user = new Users();
    private ObjectsController controller = new ObjectsController();
    private Pattern loginPat = Pattern.compile("^[A-Za-z0-9]{1,15}$");   // проверка логина
    private Pattern passwordPat = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).[^ ]{5,10}$");   // проверка пароля
    private Pattern namePat = Pattern.compile("^[A-Za-zА-Яа-я']{1,10}$");   // проверка имени
    private Matcher loginMat = null;
    private Matcher passwordMat = null;
    private Matcher nameMat = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String name = new String(request.getParameter("button").getBytes("ISO-8859-1"),"UTF-8");
		
		//если нажата кнопка назад
		if(name.equals("Назад")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/authorization_user.jsp");
			dispatcher.include(request, response);
		}
		else {
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			name = new String(request.getParameter("name").getBytes("ISO-8859-1"),"UTF-8");
		
		
			//для проверки полученных данных
			System.out.println(login);
			System.out.println(password);
			System.out.println(name);
		
			if(login.length()>=1 && password.length()>=1) {
				user.setLogin(login);
				loginMat = loginPat.matcher(login);
				// если логин не удовлетворяет ограничению
				if(!loginMat.matches()) {
					request.setAttribute("incorrectLogin", "логин может содержать только латинские буквы и цифры (не более 15 символов)");
				}
		    
				user.setHashSum(password);
				passwordMat = passwordPat.matcher(password);
				// если пароль не удовлетворяет ограничению
				if(!passwordMat.matches()) {
					request.setAttribute("incorrectPassword", "пароль должен состоять из цифр, верхнего и нижнего регистров (не менее 5 и не более 10 символов)");	
				}
		    
				user.setAccountType("user");
				if(name.length()>=1) {
					user.setName(name);
					nameMat = namePat.matcher(name);
					// если пароль не удовлетворяет ограничению
					if(!nameMat.matches()) {
						request.setAttribute("incorrectName", "имя должно быть только из букв (не более 10 символов)");	
					}
				}
				if(loginMat.matches() && passwordMat.matches() && name.length() < 1 || loginMat.matches() && passwordMat.matches() && nameMat.matches()) {
					boolean check = controller.addObjectToDatabase(user);  // попытались добавить пользователя к БД
					// если успешно добавился
					if(check) {
						System.out.println("Пользователь добавился к БД");
						request.setAttribute("loginComplete", user.getLogin());
						request.setAttribute("welcomeNewUser", "Благодарим за регистрацию!");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/welcome_user.jsp");
						dispatcher.include(request, response);
					}
					else {
						System.out.println("Пользователь не добавился к БД");
						request.setAttribute("multiName", "извините, пользователь с таким логином уже существует");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/registration_user.jsp");
						dispatcher.include(request, response);
					}
				}
				else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/registration_user.jsp");
					dispatcher.include(request, response);
				}
			}
			//если колонки не заполнены
			else {
				System.out.println("Засек пустые колонки");
				if(login.length()<1)
					request.setAttribute("incorrectLogin", "это поле не может быть пустым");
				if(password.length()<1)
					request.setAttribute("incorrectPassword", "это поле не может быть пустым");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/registration_user.jsp");
				dispatcher.include(request, response);
			}
		}
	}
}

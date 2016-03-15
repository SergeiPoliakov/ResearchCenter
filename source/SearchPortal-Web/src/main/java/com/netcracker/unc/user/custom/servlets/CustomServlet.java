package com.netcracker.unc.user.custom.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.netcracker.unc.newmvc.dao.IncomeConsumptionDAO;
import com.netcracker.unc.newmvc.dao.ObjectDAO;
import com.netcracker.unc.newmvc.dao.SalaryDAO;
import com.netcracker.unc.newmvc.dao.UserDAO;
import com.netcracker.unc.newmvc.dao.controllers.ObjectController;
import com.netcracker.unc.newmvc.dao.controllers.ParamController;
import com.netcracker.unc.newmvc.dao.models.IncomeConsumptionModel;
import com.netcracker.unc.newmvc.dao.models.ObjectModel;
import com.netcracker.unc.newmvc.dao.models.ParamModel;
import com.netcracker.unc.newmvc.dao.models.SalaryModel;
import com.netcracker.unc.newmvc.dao.models.UserModel;

@WebServlet("/custom")
public class CustomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// columns names od fatabase
	private final int incomeType = 2; // Доход
	private final String objectName = "Зарплата";
	private final String valueCheck = "true";
	/////////////

	private void viewIncomeConsumptionOverlay(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		IncomeConsumptionModel inCon = new IncomeConsumptionModel();
		IncomeConsumptionDAO inConDAO = new IncomeConsumptionDAO();
		long progress = 0;
		UserModel user = (UserModel) request.getSession().getAttribute("user");
		inCon = inConDAO.procentForBar(inCon, user.getUserId());

		if (inCon.getFullConsumption() != 0 || inCon.getFullIncome() != 0) {
			progress = (inCon.getFullIncome() * 100) / (inCon.getFullIncome() + inCon.getFullConsumption());
			request.setAttribute("percentString", progress + "/" + (100 - progress));
		} else
			request.setAttribute("percentString", "0/0");

		request.setAttribute("progress", progress);
		request.setAttribute("maxIncomeCost", inCon.getMaxIncomeName());
		request.setAttribute("maxConsumptionCost", inCon.getMaxConsumptionName());
		request.setAttribute("maxIncomeCostInt", inCon.getMaxIncome());
		request.setAttribute("maxConsumptionCostInt", inCon.getMaxConsumption());
		request.setAttribute("avgIncome", inCon.getAvgIncome());
		request.setAttribute("avgConsumption", inCon.getAvgConsumption());

	}

	private void addSalary(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String salary = request.getParameter("salary");

		ObjectModel object = new ObjectModel();
		ObjectDAO objectDAO = new ObjectDAO();
		UserDAO userDAO = new UserDAO();
		UserModel user = (UserModel) request.getSession().getAttribute("user");
		ArrayList<Integer> userObjectsId = new ArrayList<Integer>();

		for (ObjectModel obj : user.getAllObjects()) {
			if (obj.getObjectName().toLowerCase().equals(objectName.toLowerCase())) {
				userObjectsId.add(obj.getFinObjectId());
			}
		}

		object.setFinObjectTypeId(incomeType);
		object.setObjectName(objectName);
		object.setUserId(user.getUserId());
		objectDAO.addObject(object);
		user = userDAO.getUser(user.getUserId());

		for (ObjectModel obj : user.getAllObjects()) {
			if (obj.getObjectName().toLowerCase().equals(objectName.toLowerCase())) {
				if (userObjectsId.size() > 0) {
					for (Integer objectId : userObjectsId) {
						if (objectId == obj.getFinObjectId()) {
							System.out.println("id " + obj.getFinObjectId());
							continue;
						} else
							object.setFinObjectId(obj.getFinObjectId());
					}
				} else
					object.setFinObjectId(obj.getFinObjectId());
			}
		}

		ParamController paramController = new ParamController();
		ParamModel param = new ParamModel();
		paramController.setParamCurrentDate(param);
		paramController.addRegularSalaryParams(salary, object.getFinObjectId(), param.getValueDate());

		Cookie cookie = new Cookie("caseAdd", "1");
		response.addCookie(cookie);

		request.getSession().setAttribute("user", userDAO.getUser(user.getUserId()));
		response.sendRedirect("modules.jsp");

	}

	private void checkSalary(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserModel user = (UserModel) request.getSession().getAttribute("user");
		request.setAttribute("checkSalary", "error");
		for (ObjectModel object : user.getAllObjects()) {
			if (object.getObjectName().toLowerCase().equals(objectName.toLowerCase())) {
				for (ParamModel param : object.getAllParams()) {
					if (param.getValue() != null && param.getValue().toLowerCase().equals(valueCheck.toLowerCase()))
						request.setAttribute("checkSalary", "ok");
					else
						continue;
				}
			}
		}
		if (request.getAttribute("checkSalary").equals("ok"))
			controlSalary(request, response, user);
	}

	private void controlSalary(HttpServletRequest request, HttpServletResponse response, UserModel user)
			throws ServletException, IOException {

		SalaryDAO salaryDAO = new SalaryDAO();
		SalaryModel salary = salaryDAO.getLastCheckSalary(user.getUserId());
		if (salary.getDateCount() > 0 && request.getAttribute("checkSalaryBro") == null)
			request.setAttribute("controlSalary", salary);
		else
			request.setAttribute("controlSalary", null);
	}

	private void recordMissSalary(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserModel user = (UserModel) request.getSession().getAttribute("user");
		UserDAO userDAO = new UserDAO();
		SalaryModel salary = (SalaryModel) request.getAttribute("controlSalary");
		String[] mounthsControl = request.getParameterValues("controlSalaryInputTable");
		String newSalary = request.getParameter("controlSalaryInput");
		boolean checkSameSalary = true;
		String previous = mounthsControl[0];
		for (String i : mounthsControl) {
			if (!previous.equals(i))
				checkSameSalary = false;
			else
				continue;
		}
		ObjectModel object;
		ObjectDAO objectDAO = new ObjectDAO();
		ObjectController objectController = new ObjectController();
		ParamController paramController = new ParamController();
		Date date = salary.getLastCheckDate();
		Calendar cal = Calendar.getInstance();

		// if user set not same salaries
		if (checkSameSalary == false) {
			for (String valueSalary : mounthsControl) {
				object = new ObjectModel();
				object.setFinObjectTypeId(incomeType);
				object.setObjectName(objectName);
				object.setUserId(user.getUserId());
				objectDAO.addObject(object);
				object = objectController.getLastCreatingObject(user.getAllObjects(), user.getUserId());
				user = userDAO.getUser(user.getUserId());

				cal.setTimeInMillis(date.getTime());
				cal.add(Calendar.MONTH, 1);
				date.setTime(cal.getTimeInMillis());
				paramController.addRegularSalaryParams(valueSalary, object.getFinObjectId(), date);

			}
		} else {
			for (int i = 0; i < salary.getDateCount(); i++) {
				object = new ObjectModel();
				object.setFinObjectTypeId(incomeType);
				object.setObjectName(objectName);
				object.setUserId(user.getUserId());
				objectDAO.addObject(object);
				object = objectController.getLastCreatingObject(user.getAllObjects(), user.getUserId());
				user = userDAO.getUser(user.getUserId());

				cal.setTimeInMillis(date.getTime());
				cal.add(Calendar.MONTH, 1);
				date.setTime(cal.getTimeInMillis());
				paramController.addRegularSalaryParams(newSalary, object.getFinObjectId(), date);
			}
		}
		Cookie cookie = new Cookie("caseAdd", "1");
		response.addCookie(cookie);

		request.getSession().setAttribute("user", userDAO.getUser(user.getUserId()));
		response.sendRedirect("modules.jsp");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String custom = request.getParameter("custom");
		checkSalary(request, response);
		viewIncomeConsumptionOverlay(request, response);

		if (custom != null) {
			if (custom.equals("addSalary"))
				addSalary(request, response);
			else if (custom.equals("controlSalary"))
				recordMissSalary(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

package com.netcracker.unc.user.custom.servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.netcracker.unc.newmvc.dao.IncomeConsumptionDAO;
import com.netcracker.unc.newmvc.dao.IncomeConsumptionModel;
import com.netcracker.unc.newmvc.dao.ObjectDAO;
import com.netcracker.unc.newmvc.dao.ObjectModel;
import com.netcracker.unc.newmvc.dao.ParamController;
import com.netcracker.unc.newmvc.dao.ParamDAO;
import com.netcracker.unc.newmvc.dao.ParamModel;
import com.netcracker.unc.newmvc.dao.UserDAO;
import com.netcracker.unc.newmvc.dao.UserModel;

@WebServlet("/custom")
public class CustomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// columns names od fatabase
	private final int incomeType = 2;
	private final String objectName = "Зарплата";
	private final int atrIncome = 5;
	private final int atrDate = 4;
	private final int atrCheck = 9;
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
		ParamDAO paramDAO = new ParamDAO();

		param.setAttributeId(atrIncome);
		param.setFinObjectId(object.getFinObjectId());
		param.setValue(salary);
		paramDAO.addParam(param);

		param = new ParamModel();
		param.setAttributeId(atrDate);
		param.setFinObjectId(object.getFinObjectId());
		paramController.setParamCurrentDate(param);
		paramDAO.addParam(param);

		param = new ParamModel();
		param.setAttributeId(atrCheck);
		param.setFinObjectId(object.getFinObjectId());
		param.setValue(valueCheck);
		paramDAO.addParam(param);

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
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

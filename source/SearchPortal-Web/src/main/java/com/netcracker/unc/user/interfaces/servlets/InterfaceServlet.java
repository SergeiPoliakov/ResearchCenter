package com.netcracker.unc.user.interfaces.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.netcracker.unc.newmvc.dao.ObjectDAO;
import com.netcracker.unc.newmvc.dao.ObjectModel;
import com.netcracker.unc.newmvc.dao.ParamController;
import com.netcracker.unc.newmvc.dao.ParamDAO;
import com.netcracker.unc.newmvc.dao.ParamModel;
import com.netcracker.unc.newmvc.dao.UserDAO;
import com.netcracker.unc.newmvc.dao.UserModel;

@WebServlet("/interface")
public class InterfaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// database attributes
	private final int salaryAtr = 12;
	private final int endDateAtr = 11;
	private final int priorityAtr = 13;
	private final int caseType = 4; // Задача
	private final int createDate = 10; // Дата создания
	private final int endDate = 11; // Дата завершения
	private final int cost = 12;// Стоимость
	private final int priority = 13;// Приоритет

	private void updateCases(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nameCase = request.getParameter("nameCase");
		String costCase = request.getParameter("costCase");
		String dateCase = request.getParameter("dateCase");
		String priorityCase = request.getParameter("priorityCase");
		int objectID = Integer.valueOf(request.getParameter("caseId"));

		if (priorityCase.equals("высокий"))
			priorityCase = "0.75";
		if (priorityCase.equals("средний"))
			priorityCase = "0.5";
		if (priorityCase.equals("низкий"))
			priorityCase = "0.35";

		UserModel user = (UserModel) request.getSession().getAttribute("user");
		ObjectModel object = null;
		ObjectDAO objectDAO = new ObjectDAO();
		ParamDAO paramDAO = new ParamDAO();
		ParamController paramController = new ParamController();
		for (ObjectModel obj : user.getAllObjects()) {
			if (obj.getFinObjectId() == objectID) {
				object = obj;
			}
		}

		for (ParamModel param : object.getAllParams()) {
			if (param.getAttributeId() == salaryAtr) {
				param.setValue(costCase);
				paramDAO.updateParam(param);
			}
			if (param.getAttributeId() == endDateAtr) {
				param = paramController.setParamDate(dateCase, param);
				paramDAO.updateParam(param);
			}
			if (param.getAttributeId() == priorityAtr) {
				param.setValue(priorityCase);
				paramDAO.updateParam(param);
			} else
				continue;
		}

		// change case
		object.setObjectName(nameCase.trim());
		objectDAO.updateObject(object);

		Cookie cookie = new Cookie("caseUpdate", "1");
		response.addCookie(cookie);
		response.sendRedirect("modules.jsp");

	}

	private void createCase(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserModel user = (UserModel) request.getSession().getAttribute("user");

		String caseNameStr = request.getParameter("name_case");
		String caseTypeStr = request.getParameter("type_case").toLowerCase();
		int caseParentInt = Integer.valueOf(request.getParameter("parentBlock"));
		String casePriorityStr = request.getParameter("priority");
		String caseDateStr = request.getParameter("date_case");
		String caseCostStr = request.getParameter("cost_case");

		ObjectModel objectCategory = null;
		ArrayList<Integer> objectsId = new ArrayList<Integer>();
		for (ObjectModel obj : user.getAllObjects()) {
			if (obj.getObjectName().toLowerCase().equals(caseTypeStr))
				objectCategory = obj;
			objectsId.add(obj.getFinObjectId());
		}

		ObjectModel object = new ObjectModel();
		ObjectDAO objectDAO = new ObjectDAO();
		UserDAO userDAO = new UserDAO();
		// create model case
		if (caseParentInt == 0) {
			object.setParentId(objectCategory.getFinObjectId());
		} else
			object.setParentId(caseParentInt);

		object.setObjectName(caseNameStr);
		object.setFinObjectTypeId(caseType);
		object.setUserId(user.getUserId());
		objectDAO.addObject(object);

		user = userDAO.getUser(user.getUserId());
		for (ObjectModel obj : user.getAllObjects()) {
			for (Integer id : objectsId) {
				if (obj.getFinObjectId() != id)
					object = obj;
			}
		}

		ParamModel param = new ParamModel();
		ParamController paramController = new ParamController();
		ParamDAO paramDAO = new ParamDAO();
		param.setAttributeId(createDate);
		param = paramController.setParamCurrentDate(param);
		param.setFinObjectId(object.getFinObjectId());
		paramDAO.addParam(param);

		param = new ParamModel();
		param.setAttributeId(endDate);
		param.setFinObjectId(object.getFinObjectId());
		param = paramController.setParamDate(caseDateStr, param);
		paramDAO.addParam(param);

		param = new ParamModel();
		param.setAttributeId(cost);
		param.setFinObjectId(object.getFinObjectId());
		param.setValue(caseCostStr);
		paramDAO.addParam(param);

		param = new ParamModel();
		param.setAttributeId(priority);
		param.setFinObjectId(object.getFinObjectId());
		param.setValue(casePriorityStr);
		paramDAO.addParam(param);

		Cookie cookie = new Cookie("caseAdd", "1");
		response.addCookie(cookie);
		response.sendRedirect("modules.jsp");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String interfaces = request.getParameter("interfaces");

		if (interfaces.equals("updateCase"))
			updateCases(request, response);
		else if (interfaces.equals("createCase"))
			createCase(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
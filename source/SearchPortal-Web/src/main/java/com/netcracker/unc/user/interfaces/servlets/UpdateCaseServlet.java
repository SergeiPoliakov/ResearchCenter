package com.netcracker.unc.user.interfaces.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netcracker.unc.mvc.dao.AttributeDAO;
import com.netcracker.unc.mvc.dao.CaseDAO;
import com.netcracker.unc.mvc.dao.ParameterDAO;
import com.netcracker.unc.mvc.models.AttributeModel;
import com.netcracker.unc.mvc.models.CaseModel;
import com.netcracker.unc.mvc.models.ParameterModel;

@WebServlet("/UpdateCase")
public class UpdateCaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CaseModel casee = null;
	private ParameterModel param = null;
	private CaseDAO caseDAO = new CaseDAO();
	private ParameterDAO paramDAO = new ParameterDAO();
	private AttributeDAO attributeDAO = new AttributeDAO();
	private AttributeModel atr = null;
	private Cookie cookie = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

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

		casee = new CaseModel();
		casee.set_fin_object_id(objectID);
		casee = (CaseModel) caseDAO.getObject(casee);

		param = new ParameterModel();
		param.set_fin_object_id(casee.get_fin_object_id());
		List<Object> list = (List<Object>) paramDAO.getObject(param);
		List<Object> listAtr = attributeDAO.getAllObjectsDB();
		for (Object object : list) {
			param = (ParameterModel) object;
			for (Object objectAtr : listAtr) {
				atr = (AttributeModel) objectAtr;
				if (atr.getAttributeID() == param.get_attribute_id()) {
					if (atr.getAttributeName().equals("стоимость")) {
						param.set_value(costCase);
						paramDAO.updateObject(param);
					}
					if (atr.getAttributeName().equals("дата завершения")) {
						param.set_value_date(dateCase);
						paramDAO.updateObject(param);
					}
					if (atr.getAttributeName().equals("приоритет")) {
						param.set_value(priorityCase);
						paramDAO.updateObject(param);
					} else
						continue;
				}
			}
		}

		// change case
		casee.set_object_name(nameCase.trim());
		caseDAO.updateObject(casee);
		
		cookie = new Cookie("caseUpdate", "1");
		response.addCookie(cookie);
		response.sendRedirect("modules.jsp");

	}

}

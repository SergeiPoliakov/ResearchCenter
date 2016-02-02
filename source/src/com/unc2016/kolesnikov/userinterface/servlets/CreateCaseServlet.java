package com.unc2016.kolesnikov.userinterface.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unc2016.kolesnikov.mvc.CasesPOJO;
import com.unc2016.kolesnikov.mvc.CasesTypesPOJO;
import com.unc2016.kolesnikov.mvc.dao.CasesDAO;
import com.unc2016.kolesnikov.mvc.dao.CasesTypesDAO;
import com.unc2016.kolesnikov.mvc.dao.SiteOfPrioritiesObjects;

@WebServlet("/interface/CreateCase")
public class CreateCaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CasesDAO caseDAO = new CasesDAO();
	private CasesTypesDAO caseTypeDAO = new CasesTypesDAO();
	private CasesTypesPOJO caseType = new CasesTypesPOJO();
	private CasesPOJO casee = new CasesPOJO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String caseName = request.getParameter("name_case");
		String caseTypeStr = request.getParameter("type_case");
		String caseParent = null;

		// check type or create new
		CasesTypesPOJO type = new CasesTypesPOJO();
		type.set_fin_object_type_name(caseTypeStr);
		if (!caseTypeStr.equals("Other")) {
			type = (CasesTypesPOJO) caseTypeDAO.getObject(type);
		} else {
			caseTypeDAO.addObject(type);
			type = (CasesTypesPOJO) caseTypeDAO.getObject(type);
		}
		casee.set_Fin_object_type_id(type.get_fin_object_type_id());

		// for parent id
		if (!request.getParameter("parentBlock").isEmpty()) {
			caseParent = request.getParameter("parentBlock");
			// for get parent id for this case
			caseType.set_fin_object_type_name(caseParent);
			caseType = (CasesTypesPOJO) caseTypeDAO.getObject(caseType);
			casee.set_Parent_id(caseType.get_fin_object_type_id());
		}

		String casePriority = request.getParameter("priority_case");
		String caseDate = request.getParameter("date_case");
		int caseCost = Integer.valueOf(request.getParameter("cost_case"));

		System.out.println(
				caseName + " " + caseTypeStr + " " + caseParent + " " + casePriority + " " + caseDate + " " + caseCost);

		casee.set_Object_name(caseName);
		casee.set_Fin_object_type_name(caseTypeStr);
		////
		casee.set_User_id(1); // temporary id for work this servlet

		caseDAO.addObject((SiteOfPrioritiesObjects) casee);

		System.out.println(casee.get_Object_name() + " " + casee.get_Parent_id() + " " + casee.get_Fin_object_type_id()
				+ " " + casee.get_User_id());

	}

	public void doFilter(HttpServletRequest request, HttpServletResponse response) {

	}

}

package com.unc2016.user.interfaces.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.unc2016.mvc.dao.CaseDAO;
import com.unc2016.mvc.dao.CaseTypeDAO;
import com.unc2016.mvc.models.CaseModel;
import com.unc2016.mvc.models.CaseTypeModel;

@WebServlet("/interface/CreateCase")
public class CreateCaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CaseDAO caseDAO = new CaseDAO();
	private CaseTypeDAO caseTypeDAO = new CaseTypeDAO();
	private CaseTypeModel caseType = new CaseTypeModel();
	private CaseModel casee = new CaseModel();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String caseName = request.getParameter("name_case");
		String caseTypeStr = request.getParameter("type_case");
		String caseParent = null;

		// check type or create new
		CaseTypeModel type = new CaseTypeModel();
		type.set_fin_object_type_name(caseTypeStr);
		if (!caseTypeStr.equals("Other")) {
			type = (CaseTypeModel) caseTypeDAO.getObject(type);
		} else {
			caseTypeDAO.addObject(type);
			type = (CaseTypeModel) caseTypeDAO.getObject(type);
		}
		casee.set_fin_object_type_id(type.get_fin_object_type_id());

		// for parent id
		if (!request.getParameter("parentBlock").isEmpty()) {
			caseParent = request.getParameter("parentBlock");
			// for get parent id for this case
			caseType.set_fin_object_type_name(caseParent);
			caseType = (CaseTypeModel) caseTypeDAO.getObject(caseType);
			casee.set_parent_id(caseType.get_fin_object_type_id());
		}

		String casePriority = request.getParameter("priority_case");
		String caseDate = request.getParameter("date_case");
		int caseCost = Integer.valueOf(request.getParameter("cost_case"));

		System.out.println(
				caseName + " " + caseTypeStr + " " + caseParent + " " + casePriority + " " + caseDate + " " + caseCost);

		casee.set_object_name(caseName);
		casee.set_fin_object_type_name(caseTypeStr);
		////
		casee.set_user_id(1); // temporary id for work this servlet

		caseDAO.addObject(casee);

		System.out.println(casee.get_object_name() + " " + casee.get_parent_id() + " " + casee.get_fin_object_type_id()
				+ " " + casee.get_user_id());

	}

	public void doFilter(HttpServletRequest request, HttpServletResponse response) {

	}

}

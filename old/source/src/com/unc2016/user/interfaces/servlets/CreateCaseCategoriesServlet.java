package com.unc2016.user.interfaces.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unc2016.mvc.dao.CaseDAO;
import com.unc2016.mvc.models.CaseModel;

@WebServlet("/interface/CreateCaseCategories")
public class CreateCaseCategoriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CaseDAO caseTypeContr = new CaseDAO();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		List<Object> list = caseTypeContr.getAllObjectsDB();
		String categories = "";
		CaseModel caseType = new CaseModel();

		for (int i = 0; i < list.size(); i++) {
			caseType = (CaseModel) list.get(i);
			categories += "<option value = \"" + caseType.get_fin_object_type_name() + "\"" + ">"
					+ caseType.get_fin_object_type_name() + "</option> ";
		}

		out.print(categories);
	}

}

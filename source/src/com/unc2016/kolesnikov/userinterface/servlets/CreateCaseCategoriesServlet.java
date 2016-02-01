package com.unc2016.kolesnikov.userinterface.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unc2016.kolesnikov.mvc.CasesTypesPOJO;
import com.unc2016.kolesnikov.mvc.dao.CasesTypesDAO;
import com.unc2016.kolesnikov.mvc.dao.SiteOfPrioritiesObjects;

/**
 * Servlet implementation class CreateCaseCategoriesServlet
 */
@WebServlet("/interface/CreateCaseCategories")
public class CreateCaseCategoriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CasesTypesDAO caseTypeContr = new CasesTypesDAO();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		List<SiteOfPrioritiesObjects> list = caseTypeContr.getAllObjectsDB();
		String categories = "";
		CasesTypesPOJO caseType = new CasesTypesPOJO();

		for (int i = 0; i < list.size(); i++) {
			caseType = (CasesTypesPOJO) list.get(i);
			categories += "<option value = \"" + caseType.get_fin_object_type_name() + "\"" + ">"
					+ caseType.get_fin_object_type_name() + "</option> ";
			System.out.println(caseType.get_fin_object_type_name());
		}

		out.print(categories);
	}

}

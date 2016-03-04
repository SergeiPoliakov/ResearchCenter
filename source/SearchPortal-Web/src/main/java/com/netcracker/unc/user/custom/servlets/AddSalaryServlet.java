package com.netcracker.unc.user.custom.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.netcracker.unc.mvc.dao.AttributeDAO;
import com.netcracker.unc.mvc.dao.CaseDAO;
import com.netcracker.unc.mvc.dao.CaseTypeDAO;
import com.netcracker.unc.mvc.dao.ParameterDAO;
import com.netcracker.unc.mvc.models.AttributeModel;
import com.netcracker.unc.mvc.models.CaseModel;
import com.netcracker.unc.mvc.models.CaseTypeModel;
import com.netcracker.unc.mvc.models.ParameterModel;
import com.netcracker.unc.mvc.models.UserModel;

@WebServlet("/AddSalary")
public class AddSalaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// columns names od fatabase
	String incomeType = "Доход";
	String objectName = "Зарплата";
	String atrIncome = "Сумма дохода";
	String atrDate = "Дата дохода";
	String atrCheck = "Ежемесячный доход";
	String valueCheck = "true";
	/////////////

	private CaseTypeModel caseType = new CaseTypeModel();
	private CaseTypeDAO caseTypeDAO = new CaseTypeDAO();
	private CaseModel casee = null;
	private CaseDAO caseDAO = new CaseDAO();
	private UserModel user = null;
	private AttributeModel attribute = null;
	private AttributeDAO attributeDAO = new AttributeDAO();
	private ParameterModel parameter = null;
	private ParameterDAO parameterDAO = new ParameterDAO();
	private Cookie cookie = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String salary = request.getParameter("salary");
		caseType.set_fin_object_type_name(incomeType.toLowerCase());
		caseType = (CaseTypeModel) caseTypeDAO.getObject(caseType);

		casee = new CaseModel();
		casee.set_fin_object_type_id(caseType.get_fin_object_type_id());
		casee.set_object_name(objectName.toLowerCase());
		user = (UserModel) request.getSession().getAttribute("user");
		casee.set_user_id(user.get_user_id());

		// create salary object in database
		caseDAO.addObject(casee);
		casee = (CaseModel) caseDAO.getObject(casee);

		// add parameters for new object
		// for date
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String currentDate = format.format(now);
		attribute = new AttributeModel();
		attribute.set_attribute_name(atrDate.toLowerCase());
		attribute = (AttributeModel) attributeDAO.getObject(attribute);
		parameter = new ParameterModel();
		parameter.set_attribute_id(attribute.get_attribute_id());
		parameter.set_value_date(currentDate);
		parameter.set_fin_object_id(casee.get_fin_object_id());
		parameterDAO.addObject(parameter);

		// for salary
		attribute = new AttributeModel();
		attribute.set_attribute_name(atrIncome.toLowerCase());
		attribute = (AttributeModel) attributeDAO.getObject(attribute);
		parameter = new ParameterModel();
		parameter.set_attribute_id(attribute.get_attribute_id());
		parameter.set_value(salary);
		parameter.set_fin_object_id(casee.get_fin_object_id());
		parameterDAO.addObject(parameter);

		// for check salary
		attribute = new AttributeModel();
		attribute.set_attribute_name(atrCheck.toLowerCase());
		attribute = (AttributeModel) attributeDAO.getObject(attribute);
		parameter = new ParameterModel();
		parameter.set_attribute_id(attribute.get_attribute_id());
		parameter.set_value(valueCheck);
		parameter.set_fin_object_id(casee.get_fin_object_id());
		parameterDAO.addObject(parameter);

		cookie = new Cookie("caseAdd", "1");
		response.addCookie(cookie);
		response.sendRedirect("modules.jsp");
	}

}

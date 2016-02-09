package main.java.com.netcracker.unc.user.interfaces.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.netcracker.unc.mvc.ObjectController;
import main.java.com.netcracker.unc.mvc.dao.CaseDAO;
import main.java.com.netcracker.unc.mvc.dao.ParameterDAO;
import main.java.com.netcracker.unc.mvc.models.AttributeModel;
import main.java.com.netcracker.unc.mvc.models.CaseModel;
import main.java.com.netcracker.unc.mvc.models.ParameterModel;

@WebServlet("/interface/CreateCase")
public class CreateCaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CaseDAO caseDAO = new CaseDAO();
	private ParameterDAO parameterDAO = new ParameterDAO();
	private CaseModel casee = new CaseModel();
	private AttributeModel attribute = null;
	private List<Object> attributeList = null;
	private ParameterModel param = null;
	private ObjectController controller = new ObjectController();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String caseName = request.getParameter("name_case");
		String caseType = request.getParameter("type_case");
		int userId = 1;
		String caseParent = request.getParameter("parentBlock");
		String casePriority = request.getParameter("priority");
		String caseDate = request.getParameter("date_case");
		String caseCost = request.getParameter("cost_case");

		System.out.println(caseName + " " + caseType + " " + casePriority + " " + caseDate);

		casee.set_object_name(caseName);
		casee.set_fin_object_type_id(Integer.valueOf(caseType));
		casee.set_user_id(userId);
		caseDAO.addObject(casee);
		casee = (CaseModel) caseDAO.getObject(casee);

		attributeList = controller.getCaseTypeAttributes(casee);
		for (Object atr : attributeList) {
			attribute = (AttributeModel) atr;

			if (attribute.get_attribute_name().equals("Коэффициент приоритета")) {
				param = new ParameterModel();
				param.set_attribute_id(attribute.get_attribute_id());
				param.set_value1(casePriority);
				param.set_fin_object_id(casee.get_fin_object_id());
				parameterDAO.addObject(param);
			}

			if (attribute.get_attribute_name().equals("Дата окончания")) {
				param = new ParameterModel();
				param.set_attribute_id(attribute.get_attribute_id());
				param.set_value_date(caseDate);
				param.set_fin_object_id(casee.get_fin_object_id());
				parameterDAO.addObject(param);
			}

			if (attribute.get_attribute_name().equals("Стоимость задачи")) {
				param = new ParameterModel();
				param.set_attribute_id(attribute.get_attribute_id());
				param.set_value1(caseCost);
				param.set_fin_object_id(casee.get_fin_object_id());
				parameterDAO.addObject(param);
			}
		}
		caseDAO.connectionClose();
	}
}

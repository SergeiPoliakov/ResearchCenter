package com.netcracker.unc.user.interfaces.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netcracker.unc.mvc.ObjectController;
import com.netcracker.unc.mvc.dao.CaseDAO;
import com.netcracker.unc.mvc.dao.CaseTypeDAO;
import com.netcracker.unc.mvc.dao.ParameterDAO;
import com.netcracker.unc.mvc.models.AttributeModel;
import com.netcracker.unc.mvc.models.CaseModel;
import com.netcracker.unc.mvc.models.CaseTypeModel;
import com.netcracker.unc.mvc.models.ParameterModel;
import com.netcracker.unc.mvc.models.UserModel;

@WebServlet("/CreateCase")
public class CreateCaseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CaseDAO caseDAO = null;
    private CaseTypeDAO caseTypeDAO = null;
    private UserModel user = null;
    private ParameterDAO parameterDAO = null;
    private CaseModel casee = null;
    private CaseTypeModel caseType = null;
    private AttributeModel attribute = null;
    private ParameterModel param = null;
    private ObjectController controller = new ObjectController();
    private Cookie cookie = null;

    /**
     * for database tables
     */
    // sp_fin_object_types (fin_object_type_name)
    private String objectTypeName = "Задача";
    // sp_attributes (attribute_name)
    private String createDate = "Дата создания";
    private String endDate = "Дата завершения";
    private String cost = "Стоимость";
    private String priority = "Приоритет";

    ////////

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // user = (UserModel) request.getSession().getAttribute("user");
        user = (UserModel) request.getSession().getAttribute("user");

        String caseNameStr = request.getParameter("name_case");
        String caseTypeStr = request.getParameter("type_case").toLowerCase();
        int caseParentInt = 0;
        if (!request.getParameter("parentBlock").isEmpty()) {
            caseParentInt = Integer.valueOf(request.getParameter("parentBlock"));
        }
        String casePriorityStr = request.getParameter("priority");
        String caseDateStr = request.getParameter("date_case");
        String caseCostStr = request.getParameter("cost_case");

        System.out.println(caseNameStr + " " + caseTypeStr + " " + casePriorityStr + " " + caseDateStr + " "
                                   + user.get_user_id() + " " + caseCostStr + " " + caseParentInt);

        CaseModel caseCategory = controller.getStandartCategory(user, caseTypeStr); // this
        // general
        // case
        // of
        // category
        casee = new CaseModel();
        attribute = new AttributeModel();
        caseType = new CaseTypeModel();
        caseTypeDAO = new CaseTypeDAO();
        caseType.set_fin_object_type_name(objectTypeName.toLowerCase());
        caseType = (CaseTypeModel) caseTypeDAO.getObject(caseType);
        // create model case
        if (caseParentInt == 0) {
            casee.set_parent_id(caseCategory.get_fin_object_id());
        } else {
            casee.set_parent_id(caseParentInt);
        }
        casee.set_object_name(caseNameStr);
        casee.set_fin_object_type_id(caseType.get_fin_object_type_id());
        casee.set_user_id(user.get_user_id());

        caseDAO = new CaseDAO();
        caseDAO.addObject(casee);
        casee = (CaseModel) caseDAO.getObject(casee);
        List<Object> listAtr = controller.getCaseTypeAttributes(casee.get_fin_object_type_id());
        parameterDAO = new ParameterDAO();
        for (Object ob : listAtr) {
            attribute = (AttributeModel) ob;

            if (attribute.get_attribute_name().toLowerCase().equals(createDate.toLowerCase())) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date now = new Date();
                String currentDate = format.format(now);
                param = new ParameterModel();
                param.set_value_date(currentDate);
                param.set_fin_object_id(casee.get_fin_object_id());
                param.set_attribute_id(attribute.get_attribute_id());
                parameterDAO.addObject(param);
            }

            if (attribute.get_attribute_name().toLowerCase().equals(endDate.toLowerCase())) {
                param = new ParameterModel();
                param.set_value_date(caseDateStr);
                param.set_fin_object_id(casee.get_fin_object_id());
                param.set_attribute_id(attribute.get_attribute_id());
                parameterDAO.addObject(param);
            }

            if (attribute.get_attribute_name().toLowerCase().equals(cost.toLowerCase())) {
                param = new ParameterModel();
                param.set_value(caseCostStr);
                param.set_fin_object_id(casee.get_fin_object_id());
                param.set_attribute_id(attribute.get_attribute_id());
                parameterDAO.addObject(param);
            }

            if (attribute.get_attribute_name().toLowerCase().equals(priority.toLowerCase())) {
                System.out.println(attribute.get_attribute_name());
                param = new ParameterModel();
                param.set_value(casePriorityStr);
                param.set_fin_object_id(casee.get_fin_object_id());
                param.set_attribute_id(attribute.get_attribute_id());
                parameterDAO.addObject(param);
            }
        }
        cookie = new Cookie("caseAdd", "1");
        response.addCookie(cookie);
        response.sendRedirect("modules.jsp");
    }
}

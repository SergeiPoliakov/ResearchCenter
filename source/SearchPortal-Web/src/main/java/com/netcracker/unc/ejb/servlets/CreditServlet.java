package com.netcracker.unc.ejb.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netcracker.unc.newmvc.ejb.controllers.ControllerCredit;
import com.netcracker.unc.newmvc.ejb.dao.EjbDAO;
import com.netcracker.unc.newmvc.ejb.entities.EntityUser;
import com.netcracker.unc.newmvc.ejb.models.CreditModel;
import com.netcracker.unc.newmvc.ejb.models.UserModel;


@WebServlet("/credit")
public class CreditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @EJB
    ControllerCredit credController;
    @EJB
    UserModel user;
    @EJB
    EjbDAO dao;
    Logger log;
    
    private final String mainUrl = "ejb/welcome.jsp";
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
    	user.setUser((EntityUser)request.getSession().getAttribute("user"));
    	request.getSession().setAttribute("userCredits", dao.getAllCredits(user.getUser()));
		log = Logger.getLogger(CreditServlet.class.getName());
		log.warning("servlet data checked");
		//Обработка добавления кредита
		log.warning("addDataRow" + request.getParameter("addDataRow"));
		String temp = request.getParameter("addDataRow");
		if(temp != null){
			if(temp.equals("addData")){
				CreditModel tempModel;
				log.warning("in add row");
				String name, value, percent, date, period;
			
			
				tempModel = new CreditModel();
				
				name = request.getParameter("creditName");
				value = request.getParameter("creditValue");
				percent = request.getParameter("creditPercent");
				period = request.getParameter("creditPeriod");
				date = request.getParameter("creditDate");
				log.warning("got values" + name + " ----- " + value+ "percent");
				tempModel.setCreditName(name);
				tempModel.setCreditValue(Integer.valueOf(value));
				tempModel.setCreditPercent(Double.valueOf(percent));
				log.warning("faking date"+date);
				Date recivDate = Date.valueOf(request.getParameter("dateCredit"));
				tempModel.setReceivingDate(recivDate);
				tempModel.setPayPeriod(Integer.valueOf(period));
				log.warning("model ready");
				dao.addCredit(tempModel, user.getUser());
				log.warning("looks like added");
				
				response.sendRedirect(mainUrl);
			}
			
		}
		temp = request.getParameter("DataRow");
		log.warning("to delete " + temp);
		if(temp != null){
			if(temp.equals("gotData")){
				log.warning("id === " + request.getParameter("del"));
				long id = Long.valueOf(request.getParameter("del"));
				dao.delCredit(id);
				response.sendRedirect(mainUrl);
			}
		}
		
	
	}

	

	

	

	
	
	
	
	
	
}

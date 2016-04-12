package com.netcracker.unc.ejb.servlets;

import java.io.IOException;
import java.util.List;

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
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		user.setUser((EntityUser)request.getSession().getAttribute("user"));
		sendDate(request, response);
		
		
		//Обработка добавления кредита
		String name, value, percent, date, period;
		if(request.getParameter("addDataRow") != null){
				addCredit(request, response);
				response.sendRedirect("ejb/welcome.jsp");
			
		}
		else{
			sendDate(request, response);
			response.sendRedirect("ejb/welcome.jsp");
		}
		//Обработка удаления
		if(request.getParameter("DataRow") !=  null){
			delCredit(request, response);
			response.sendRedirect("ejb/welcome.jsp");
		}
		else{
			sendDate(request, response);
			response.sendRedirect("ejb/welcome.jsp");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void addCredit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CreditModel tempModel;
		String name, value, percent, date, period;
		String result = request.getParameter("addDataRow");
		if(result.equals("gotData")){
			tempModel = new CreditModel();
			
			name = request.getParameter("creditName");
			value = request.getParameter("creditValue");
			percent = request.getParameter("creditPercent");
			period = request.getParameter("creditPeriod");
			date = request.getParameter("creditDate");
			
			tempModel.setCreditName(name);
			tempModel.setCreditValue(Integer.valueOf(value));
			tempModel.setCreditPercent(Double.valueOf(percent));
			tempModel.setReceivingDate(date);
			tempModel.setPayPeriod(Integer.valueOf(period));
			
			dao.addCredit(tempModel, user.getUser());
		}
	}
	
	private void delCredit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String result = request.getParameter("DataRow");
		if(result.equals("gotData")){
			long id = Long.valueOf(request.getParameter("id"));
			dao.delCredit(id);
			response.sendRedirect("ejb/welcome.jsp");
		}
	}
	
	private void sendDate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().setAttribute("userCredits", dao.getAllCredits(user.getUser()));
	}
	
	
	
	
	
}

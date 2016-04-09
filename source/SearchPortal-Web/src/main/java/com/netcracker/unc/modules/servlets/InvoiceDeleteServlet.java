package com.netcracker.unc.modules.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netcracker.unc.newmvc.dao.InvoiceDAO;
import com.netcracker.unc.newmvc.dao.models.UserModel;
import com.netcracker.unc.newmvc.ejb.models.InvoiceModel;

@WebServlet("/InvoiceDeleteServlet")
public class InvoiceDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InvoiceDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserModel user = null;
		InvoiceModel invoice = new InvoiceModel();
		user = (UserModel) request.getSession().getAttribute("user");
		InvoiceDAO invoicedao = new InvoiceDAO(user);
		
		int invoiceNumber = 1;
		if ((request.getParameter("invoice-number") != null) && (!request.getParameter("invoice-number").trim().isEmpty())) {
			invoiceNumber = Integer.valueOf(request.getParameter("invoice-number"));
			invoice.setInvoiceId(Integer.valueOf(invoiceNumber));
		}
		
		invoicedao.deleteInvoice(invoiceNumber);
		response.sendRedirect("welcome.jsp");
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

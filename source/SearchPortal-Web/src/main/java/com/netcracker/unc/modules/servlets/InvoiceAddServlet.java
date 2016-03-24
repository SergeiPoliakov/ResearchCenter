package com.netcracker.unc.modules.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netcracker.unc.newmvc.dao.InvoiceDAO;
import com.netcracker.unc.newmvc.dao.models.InvoiceModel;
import com.netcracker.unc.newmvc.dao.models.UserModel;

@WebServlet("/InvoiceAddServlet")
public class InvoiceAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InvoiceAddServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserModel user = null;
		InvoiceModel invoice = new InvoiceModel();
		user = (UserModel) request.getSession().getAttribute("user");
		InvoiceDAO invoicedao = new InvoiceDAO(user);
		
		Logger log = Logger.getLogger(InvoiceAddServlet.class.getName());
	    log.warning("User ID: " + user.getUserId());

		String invoiceName, invoiceBalance, invoicePercent = null;
		boolean  invoiceCredit = false;
		if ((request.getParameter("invoice-name") != null) && (!request.getParameter("invoice-name").trim().isEmpty())) {
			invoiceName = String.valueOf(request.getParameter("invoice-name"));
			log.warning("New invoice: " + invoiceName);
			invoice.setInvoiceName(invoiceName);
			
			if ((request.getParameter("invoice-balance") != null) && (!request.getParameter("invoice-balance").trim().isEmpty())) {
				invoiceBalance = String.valueOf(request.getParameter("invoice-balance"));
				log.warning("New invoice: " + invoiceBalance);
				invoice.setBalance(Integer.valueOf(invoiceBalance));
			}
			if ((request.getParameter("invoice-credit") != null) && (!request.getParameter("invoice-credit").trim().isEmpty())) {
				invoiceCredit = Boolean.valueOf(request.getParameter("invoice-credit"));
				log.warning("New invoice: " + invoiceCredit);
				invoice.setCredit(invoiceCredit);
			}
			if ((request.getParameter("invoice-percent") != null) && (!request.getParameter("invoice-percent").trim().isEmpty())) {
				invoicePercent = String.valueOf(request.getParameter("invoice-percent"));
				log.warning("New invoice: " + invoicePercent);
				invoice.setPercent(Integer.valueOf(invoicePercent));
			}
			
			invoicedao.addInvoice(invoice);
			//request.setAttribute("currentSum", invoiceName);
			log.warning("Before redirect...");
		}
		response.sendRedirect("welcome.jsp");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

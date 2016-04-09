package com.netcracker.unc.modules.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netcracker.unc.newmvc.dao.InvoiceDAO;
import com.netcracker.unc.newmvc.dao.models.UserModel;
import com.netcracker.unc.newmvc.ejb.models.InvoiceModel;

@WebServlet("/IncomingServlet")
public class IncomingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public IncomingServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserModel user = null;
		InvoiceModel invoice = new InvoiceModel();
		user = (UserModel) request.getSession().getAttribute("user");
		InvoiceDAO invoicedao = new InvoiceDAO(user);
		Logger log = Logger.getLogger(IncomingServlet.class.getName());
		
		int invoiceNumber = 1;
		if ((request.getParameter("invoiceNumber") != null) && (!request.getParameter("invoiceNumber").trim().isEmpty())) {
			invoiceNumber = Integer.valueOf(request.getParameter("invoiceNumber"));
			log.warning("New number: " + invoiceNumber);
			//request.setAttribute("currentSum", invoiceNumber);
			//log.warning("Before redirect...");
		}
		log.warning("invoiceNumber: "+ invoiceNumber );
		invoice = invoicedao.getInvoice(invoiceNumber,user);

		log.warning("User ID: " + user.getUserId());
		log.warning("InvoiceName: " + invoice.getInvoiceName());
		log.warning("InvoiceBalance: " + invoice.getBalance());
		log.warning("InvoiceID: " + invoice.getInvoiceId());

		int addSumVal=0;
		if ((request.getParameter("add-sum-val") != null) && (!request.getParameter("add-sum-val").trim().isEmpty())) {
			addSumVal = invoice.getBalance() + Integer.parseInt(request.getParameter("add-sum-val"));
			log.warning("New sum: " + addSumVal);
			
		}
		log.warning("addSumVal: "+ addSumVal);
		invoice.setBalance(addSumVal);
		invoicedao.updateBalance(invoice);
		request.setAttribute("currentSum", addSumVal);
		log.warning("Before redirect...");
		response.sendRedirect("welcome.jsp");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

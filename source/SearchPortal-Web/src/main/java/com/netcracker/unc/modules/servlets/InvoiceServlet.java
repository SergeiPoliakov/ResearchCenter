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

@WebServlet("/InvoiceServlet")
public class InvoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InvoiceServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserModel user = null;
		InvoiceModel invoice = new InvoiceModel();
		user = (UserModel) request.getSession().getAttribute("user");
		InvoiceDAO invoicedao = new InvoiceDAO(user);
		
		Logger log = Logger.getLogger(InvoiceServlet.class.getName());
		log.warning("User ID: " + user.getUserId());

		String invoiceName;
		if ((request.getParameter("invoice-name") != null) && (!request.getParameter("invoice-name").trim().isEmpty())) {
			invoiceName = String.valueOf(request.getParameter("invoice-name"));
			log.warning("New invoice: " + invoiceName);
			invoice.setInvoiceName(invoiceName);
			invoicedao.addInvoice(invoice);
			request.setAttribute("currentSum", invoiceName);
			log.warning("Before redirect...");
		}
		response.sendRedirect("modules.jsp");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

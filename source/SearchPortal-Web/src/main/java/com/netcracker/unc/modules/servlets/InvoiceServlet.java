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

@WebServlet("/InvoiceServlet")
public class InvoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(InvoiceServlet.class.getName());

	public InvoiceServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserModel user = null;
		InvoiceModel invoice = new InvoiceModel();
		user = (UserModel) request.getSession().getAttribute("user");
		InvoiceDAO invoicedao = new InvoiceDAO(user);

		log.warning("User ID: " + user.getUserId());

		String invoiceName, invoiceBalance, invoicePercent = null;
		boolean invoiceCredit = false;
		if ((request.getParameter("invoice-name") != null)
				&& (!request.getParameter("invoice-name").trim().isEmpty())) {
			invoiceName = String.valueOf(request.getParameter("invoice-name"));
			log.warning("New invoice: " + invoiceName);
			invoice.setInvoiceName(invoiceName);

			if ((request.getParameter("invoice-balance") != null)
					&& (!request.getParameter("invoice-balance").trim().isEmpty())) {
				invoiceBalance = String.valueOf(request.getParameter("invoice-balance"));
				log.warning("New invoice: " + invoiceBalance);
				invoice.setBalance(Integer.valueOf(invoiceBalance));
			}
			if ((request.getParameter("invoice-credit") != null)
					&& (!request.getParameter("invoice-credit").trim().isEmpty())) {
				invoiceCredit = Boolean.valueOf(request.getParameter("invoice-credit"));
				log.warning("New invoice: " + invoiceCredit);
				invoice.setCredit(invoiceCredit);
			}
			if ((request.getParameter("invoice-percent") != null)
					&& (!request.getParameter("invoice-percent").trim().isEmpty())) {
				invoicePercent = String.valueOf(request.getParameter("invoice-percent"));
				log.warning("New invoice: " + invoicePercent);
				invoice.setPercent(Integer.valueOf(invoicePercent));
			}

			invoicedao.addInvoice(invoice);
			// request.setAttribute("currentSum", invoiceName);
			log.warning("Before redirect...");
		} else if ((request.getParameter("invoice-number") != null)
				&& (!request.getParameter("invoice-number").trim().isEmpty())) {
			int invoiceNumber = Integer.valueOf(request.getParameter("invoice-number"));
			log.warning("Invoice to be deleted: " + invoiceNumber);
			invoice.setInvoiceId(Integer.valueOf(invoiceNumber));
			invoicedao.deleteInvoice(invoiceNumber);
		}
		response.sendRedirect("welcome.jsp");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

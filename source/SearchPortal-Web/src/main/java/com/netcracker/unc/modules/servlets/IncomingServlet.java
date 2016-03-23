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
		invoice = (InvoiceModel) invoicedao.getInvoiceByInoiceId(1);
		Logger log = Logger.getLogger(IncomingServlet.class.getName());
		log.warning("User ID: " + user.getUserId());

		int addSumVal;
		if ((request.getParameter("add-sum-val") != null) && (!request.getParameter("add-sum-val").trim().isEmpty())) {
			addSumVal = invoice.getBalance() + Integer.parseInt(request.getParameter("add-sum-val"));
			log.warning("New sum: " + addSumVal);
			invoice.setBalance(addSumVal);
			invoicedao.updateBalance(invoice);
			request.setAttribute("currentSum", addSumVal);
			log.warning("Before redirect...");
		}
		response.sendRedirect("modules.jsp");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

package com.netcracker.unc.ejb.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netcracker.unc.newmvc.ejb.dao.EjbDAO;
import com.netcracker.unc.newmvc.ejb.entities.EntityUser;
import com.netcracker.unc.newmvc.ejb.models.InvoiceModel;
import com.netcracker.unc.newmvc.ejb.models.UserModel;

@WebServlet("/inc")
public class IncomingEjbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	EjbDAO ejb;

	@EJB
	UserModel user;

	public IncomingEjbServlet() {
		super();
	}

	/*public*/ private void createSumBalance(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().setAttribute("invoicesController", ejb.getSumBalance(user.getUser()));
		request.getSession().setAttribute("invoicesControllerItems", ejb.getAllInvoice(user.getUser()));
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("попал в сервлет");
		user.setUser((EntityUser) request.getSession().getAttribute("user"));
		createSumBalance(request, response);

		if (request.getParameter("incoming") != null) {
			System.out.println("попал в сервлет1");
			String incoming = request.getParameter("incoming");
			if (incoming.equals("incomingBalance")) {
				System.out.println("попал в сервлет2");
				EntityUser user = null;
				InvoiceModel invoice = new InvoiceModel();
				user = (EntityUser) request.getSession().getAttribute("user");
				Logger log = Logger.getLogger(IncomingEjbServlet.class.getName());

				int invoiceNumber = 1;
				if ((request.getParameter("invoiceNumber") != null)
						&& (!request.getParameter("invoiceNumber").trim().isEmpty())) {
					System.out.println("попал в сервлет3");
					invoiceNumber = Integer.valueOf(request.getParameter("invoiceNumber"));
					log.warning("New number: " + invoiceNumber);
					// request.setAttribute("currentSum", invoiceNumber);
					// log.warning("Before redirect...");
				}
				log.warning("invoiceNumber: " + invoiceNumber);
				invoice = ejb.getInvoice(invoiceNumber, user);

				log.warning("User ID: " + user.getUserId());
				log.warning("InvoiceName: " + invoice.getInvoiceName());
				log.warning("InvoiceBalance: " + invoice.getBalance());
				log.warning("InvoiceID: " + invoice.getInvoiceId());

				int addSumVal = 0;
				if ((request.getParameter("add-sum-val") != null)
						&& (!request.getParameter("add-sum-val").trim().isEmpty())) {
					addSumVal = invoice.getBalance() + Integer.parseInt(request.getParameter("add-sum-val"));
					log.warning("New sum: " + addSumVal);

				}
				log.warning("addSumVal: " + addSumVal);
				invoice.setBalance(addSumVal);
				ejb.updateBalance(invoice);
				request.setAttribute("currentSum", addSumVal);
				log.warning("Before redirect...");
				response.sendRedirect("ejb/welcome.jsp");
			}
		}
		if (request.getParameter("incomingMinus") != null) {
			System.out.println("попал в сервлет1");
			String incoming = request.getParameter("incomingMinus");
			if (incoming.equals("incomingBalance")) {
				System.out.println("попал в сервлет2");
				EntityUser user = null;
				InvoiceModel invoice = new InvoiceModel();
				user = (EntityUser) request.getSession().getAttribute("user");
				Logger log = Logger.getLogger(IncomingEjbServlet.class.getName());

				int invoiceNumber = 1;
				if ((request.getParameter("invoiceNumber") != null)
						&& (!request.getParameter("invoiceNumber").trim().isEmpty())) {
					System.out.println("попал в сервлет3");
					invoiceNumber = Integer.valueOf(request.getParameter("invoiceNumber"));
					log.warning("New number: " + invoiceNumber);
					// request.setAttribute("currentSum", invoiceNumber);
					// log.warning("Before redirect...");
				}
				log.warning("invoiceNumber: " + invoiceNumber);
				invoice = ejb.getInvoice(invoiceNumber, user);

				log.warning("User ID: " + user.getUserId());
				log.warning("InvoiceName: " + invoice.getInvoiceName());
				log.warning("InvoiceBalance: " + invoice.getBalance());
				log.warning("InvoiceID: " + invoice.getInvoiceId());

				int deleteSumVal = 0;
				if ((request.getParameter("get-sum-val") != null)
						&& (!request.getParameter("get-sum-val").trim().isEmpty())) {
					deleteSumVal = invoice.getBalance() - Integer.parseInt(request.getParameter("get-sum-val"));
					log.warning("New sum: " + deleteSumVal);

				}
				log.warning("deleteSumVal: " + deleteSumVal);
				invoice.setBalance(deleteSumVal);
				ejb.updateBalance(invoice);
				request.setAttribute("currentSum", deleteSumVal);
				log.warning("Before redirect...");
				response.sendRedirect("ejb/welcome.jsp");
			}
		}
	}

}

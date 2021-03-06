package com.netcracker.unc.ejb.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netcracker.unc.newmvc.ejb.models.ConsumptionModel;
import com.netcracker.unc.newmvc.ejb.models.IncomeModel;
import com.netcracker.unc.newmvc.ejb.controllers.ControllerObjects;
import com.netcracker.unc.newmvc.ejb.dao.EjbDAO;
import com.netcracker.unc.newmvc.ejb.entities.EntityObject;
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

	@EJB
	ControllerObjects objContr;

	public IncomingEjbServlet() {
		super();
	}

	/* public */ private void createSumBalance(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().setAttribute("invoicesController", ejb.getSumBalance(user.getUser()));
		request.getSession().setAttribute("invoicesControllerItems", ejb.getAllInvoice(user.getUser()));
		// System.out.println(ejb.getAllInvoice(user.getUser()).size());
	}

	private void createIncome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().setAttribute("allIncome", ejb.getAllIncome(user.getUser()));
	}

	private void createConsumption(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().setAttribute("allConsumption", ejb.getAllConsumption(user.getUser()));
		request.getSession().setAttribute("allCases", ejb.getUserCases(user.getUser().getUserId()));
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		user.setUser((EntityUser) request.getSession().getAttribute("user"));
		createSumBalance(request, response);
		createIncome(request, response);
		createConsumption(request, response);

		if (request.getParameter("incoming") != null) {
			String incoming = request.getParameter("incoming");
			if (incoming.equals("incomingBalance")) {
				EntityUser user = null;
				InvoiceModel invoice = new InvoiceModel();
				user = (EntityUser) request.getSession().getAttribute("user");
				Logger log = Logger.getLogger(IncomingEjbServlet.class.getName());

				int invoiceNumber = 1;
				if ((request.getParameter("invoiceNumber") != null)
						&& (!request.getParameter("invoiceNumber").trim().isEmpty())) {
					invoiceNumber = Integer.valueOf(request.getParameter("invoiceNumber"));
					log.warning("New number: " + invoiceNumber);
					request.setAttribute("currentSum", invoiceNumber);
					log.warning("Before redirect...");
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
			String incoming = request.getParameter("incomingMinus");
			if (incoming.equals("incomingBalance")) {
				EntityUser user = null;
				InvoiceModel invoice = new InvoiceModel();
				user = (EntityUser) request.getSession().getAttribute("user");
				Logger log = Logger.getLogger(IncomingEjbServlet.class.getName());

				int invoiceNumber = 1;
				if ((request.getParameter("invoiceNumber") != null)
						&& (!request.getParameter("invoiceNumber").trim().isEmpty())) {
					invoiceNumber = Integer.valueOf(request.getParameter("invoiceNumber"));
					log.warning("New number: " + invoiceNumber);
					request.setAttribute("currentSum", invoiceNumber);
					log.warning("Before redirect...");
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
		if (request.getParameter("incomes") != null) {
			if (request.getParameter("incomes").equals("addIncomes")) {
				IncomeModel income = new IncomeModel();
				String incomeName = String.valueOf(request.getParameter("income-name"));
				// log.warning("New number: " + incomeNumber);
				request.setAttribute("incomeName", incomeName);
				income.setIncomeName(incomeName);

				int incomeSum = Integer.valueOf(request.getParameter("income-balance"));
				request.setAttribute("incomeBalance", incomeSum);
				income.setIncomeSum(incomeSum);

				String regularSalary = request.getParameter("income-regular");
				request.setAttribute("incomeRegular", regularSalary);
				income.setMonth(Boolean.parseBoolean(regularSalary));

				Date incomeDate = Date.valueOf(request.getParameter("income-date"));
				request.setAttribute("incomeRegular", incomeDate);
				income.setDateIncome(incomeDate);

				InvoiceModel invoice;
				int invoiceId = Integer.valueOf(request.getParameter("income-invoice"));
				// request.setAttribute("incomeRegular", incomeDate);
				invoice = ejb.getInvoice(invoiceId, user.getUser());
				income.setUserId((int) user.getUser().getUserId());
				income.setIncomesInvoice(invoice);

				ejb.addIncome(income, user.getUser());
				response.sendRedirect("ejb/welcome.jsp");
			}
			if (request.getParameter("incomes").equals("deleteIncomes")) {
				int inNumb = Integer.valueOf(request.getParameter("income-number"));
				ejb.deleteInvoice(inNumb);
				response.sendRedirect("ejb/welcome.jsp");
			}
		}

		String invoiceName;
		String invoiceBalance, invoiceCredit, invoicePercent;
		if ((request.getParameter("invoices") != null) && (!request.getParameter("invoices").trim().isEmpty()))

		{
			String invoices = request.getParameter("invoices");
			if (invoices.equals("addInvoices")) {
				EntityUser user = null;
				InvoiceModel invoice = new InvoiceModel();
				user = (EntityUser) request.getSession().getAttribute("user");
				Logger log = Logger.getLogger(IncomingEjbServlet.class.getName());

				if ((request.getParameter("invoice-name") != null)
						&& (!request.getParameter("invoice-name").trim().isEmpty())) {
					invoiceName = String.valueOf(request.getParameter("invoice-name"));
					log.warning("New invoice: " + invoiceName);
					invoice.setInvoiceName(invoiceName);
				}

				if ((request.getParameter("invoice-balance") != null)
						&& (!request.getParameter("invoice-balance").trim().isEmpty())) {
					invoiceBalance = String.valueOf(request.getParameter("invoice-balance"));
					log.warning("New invoice: " + invoiceBalance);
					invoice.setBalance(Integer.valueOf(invoiceBalance));
				}
				if ((request.getParameter("invoice-credit") != null)
						&& (!request.getParameter("invoice-credit").trim().isEmpty())) {
					invoiceCredit = request.getParameter("invoice-credit");
					log.warning("New invoice: " + invoiceCredit);
					invoice.setCredit(Boolean.valueOf(invoiceCredit));
				}
				if ((request.getParameter("invoice-percent") != null)
						&& (!request.getParameter("invoice-percent").trim().isEmpty())) {
					invoicePercent = String.valueOf(request.getParameter("invoice-percent"));
					log.warning("New invoice: " + invoicePercent);
					invoice.setPercent(Integer.valueOf(invoicePercent));
				}

				ejb.addInvoice(invoice, user);
				// request.setAttribute("currentSum", invoiceName);
				log.warning("Before redirect...");
				response.sendRedirect("ejb/welcome.jsp");
			}
		}

		if ((request.getParameter("invoicesDelete") != null))

		{
			String invoices = request.getParameter("invoicesDelete");
			if (invoices.equals("deleteInvoices")) {
				if ((request.getParameter("invoice-number") != null)
						&& (!request.getParameter("invoice-number").trim().isEmpty())) {
					Integer invoiceNumber = Integer.valueOf(request.getParameter("invoice-number"));
					ejb.deleteInvoice(invoiceNumber);
					response.sendRedirect("ejb/welcome.jsp");
				}
			}
		}
		if (request.getParameter("consumptions") != null) {
			if (request.getParameter("consumptions").equals("addConsumptions")) {

				ConsumptionModel consumption = new ConsumptionModel();
				String consumptionName = String.valueOf(request.getParameter("consumption-name"));
				consumption.setConsumptionName(consumptionName);

				int consumptionSum = Integer.valueOf(request.getParameter("consumption-balance"));
				consumption.setConsumptionSum(consumptionSum);

				String regularSalary = request.getParameter("consumption-regular");
				consumption.setMonth(Boolean.parseBoolean(regularSalary));

				Date consumptionDate = Date.valueOf(request.getParameter("consumption-date"));
				consumption.setDateConsumption(consumptionDate);

				EntityObject parent;
				int parentId = Integer.valueOf(request.getParameter("consumption-parent"));
				parent = (EntityObject) ejb.getObject(EntityObject.class, parentId);
				consumption.setUserId((int) user.getUser().getUserId());
				consumption.setParentObj(parent);

				ejb.addConsumption(consumption, user.getUser());
				response.sendRedirect("ejb/welcome.jsp");

			} else if (request.getParameter("consumptions").equals("deleteConsumptions")) {
				if (!request.getParameter("consumption-number").equals("")) {
					Integer consNumber = Integer.valueOf(request.getParameter("consumption-number"));
					try {
						EntityObject obj = (EntityObject) ejb.getObject(EntityObject.class, consNumber);
						ejb.deleteObject(obj);
					} catch (Exception c) {
						c.printStackTrace();
					} finally {
						response.sendRedirect("ejb/welcome.jsp");
					}
				} else
					response.sendRedirect("ejb/welcome.jsp");
			}
		}
	}
}

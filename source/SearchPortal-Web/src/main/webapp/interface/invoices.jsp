<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">	
	
	<jsp:useBean id="invoicesController"
		class="com.netcracker.unc.newmvc.dao.InvoiceDAO" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Invoices</title>
</head>
<body>
<div>
<table border="1">
<thead align="center">
		<tr>
			<th class="title">Номер счёта</th>
			<th class="title">Название</th>
			<th class="title">Баланс</th>
			<th class="title">Кредитность</th>
			<th class="title">Процент</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="invoice"
			items="${invoicesController.getAllInvoice(sessionScope.user)}">
			<tr>
				<td>${invoice.getInvoiceId()}</td>
				<td>${invoice.getInvoiceName()}</td>
				<td>${invoice.getBalance()}</td>
				<td>${invoice.isCredit()}</td>
				<td>${invoice.getPercent()}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
			
<button id="statistic-menu-button" class="button"
			size="40" onclick="addInvoice()">Добавить счет</button>
<button id="statistic-menu-button-delete" class="button"
			size="10" onclick="deleteInvoice()">Удалить счёт</button>
			
<div id="add-invoice" style="visibility: hidden;">
<form class="for,-inline" action="InvoiceServlet" method="get">
		      <input type="text" name="invoice-name"  size="5" placeholder="Название счета"/>
		      <input type="number" name="invoice-balance"  size="5" placeholder="баланс"/>
		      <select><selectname="invoice-credit"  size="2">
		      <option disabled>Кредитный?</option>
        	  <option selected value = "true">да</option>
        	  <option selected value = "false">нет</option></select> 
		      <input type="number" name="invoice-percent"  size="5" placeholder="Процент по кредиту"/>
		      <input type="submit" value="Добавить"/>
		    </form>
	</div>			
	

			
<div id="delete-invoice" style="visibility: hidden;">
<form action="InvoiceServlet" method="delete">
		      <input type="text" name="invoice-number"  size="5" placeholder="Номер счёта"/>
		      <input type="submit" value="Удалить"/>
		    </form>
	</div>					
	</div>

</body>

<script src="interface/js/update_user.js"></script>

</html>
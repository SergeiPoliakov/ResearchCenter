<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
	<jsp:useBean id="invoicesController"
		class="com.netcracker.unc.newmvc.dao.InvoiceDAO" />

<style>
.title {
	font-size: 12pt;
}

.result {
	font-size: 9pt;
}

td {
	width: 7%
}

input[type="text"] {
	width: 70%;
	font-size: 8pt;
}

input[type="date"] {
	width: 100%;
	font-size: 8pt;
}

select {
	width: 100%;
	font-size: 8pt;
}
</style>
	
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
<form action="InvoiceAddServlet" method="get">
		      <input type="text" name="invoice-name"  size="5" placeholder="Название счета"/>
		      <input type="text" name="invoice-balance"  size="5" placeholder="баланс"/>
		      <input type="text" name="invoice-credit"  size="5" placeholder="кредитный?"/>
		      <input type="text" name="invoice-percent"  size="5" placeholder="Процент по кредитуs"/>
		      <input type="submit" value="Добавить"/>
		    </form>
	</div>			
	

			
<div id="delete-invoice" style="visibility: hidden;">
<form action="InvoiceDeleteServlet" method="get">
		      <input type="text" name="invoice-number"  size="5" placeholder="Номер счёта"/>
		      <input type="submit" value="Удалить"/>
		    </form>
	</div>					

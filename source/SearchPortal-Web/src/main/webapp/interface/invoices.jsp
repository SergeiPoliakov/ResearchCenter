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
	width: 100%;
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
			<th class="title">Number</th>
			<th class="title">Name</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="invoice"
			items="${invoicesController.getAllInvoice(sessionScope.user)}">
			<tr>
				<td>${invoice.getInvoiceId()}</td>
				<td>${invoice.getInvoiceName()}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
			
<button id="statistic-menu-button" class="button"
			onclick="addInvoice()">Добавить счет</button>
			
<div id="add-invoice" style="visibility: hidden;">
<form action="InvoiceServlet" method="get">
		      <input type="text" name="invoice-name"  size="5" placeholder="Название счета"/>
		      <input type="submit" value="Добавить" />
		    </form>
	</div>						

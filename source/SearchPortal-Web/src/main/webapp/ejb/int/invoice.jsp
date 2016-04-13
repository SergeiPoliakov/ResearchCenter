<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Invoices</title>
</head>
<body>
<jsp:include page="/inc"></jsp:include>
<div style="width=100%">
			<table border="1" class="table invoice-table" style="width: 100%; padding: 10px;">
				<thead align="center" class="invoice-table-head">
					<tr>
						<th class="title">Номер счёта</th>
						<th class="title">Название</th>
						<th class="title">Баланс</th>
						<th class="title">Кредитность</th>
						<th class="title">Процент</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="invoice" items="${invoicesControllerItems}">
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
</div>
<div style="padding-left: 20px; float: center">
			<button id="statistic-menu-button" class="button"
				onclick="addInvoice()">Добавить счет</button>

			<button id="statistic-menu-button-delete" class="button"
			 onclick="deleteInvoice()">Удалить счёт</button> 

			<form action="../inc" method="get">
			<input type = "hidden" value='addInvoices' name="invoices" >
				<div id="add-invoice" style="visibility: hidden;">
					<!-- <input type="hidden" value="addInvoices" name="invoices" />  -->
					<input  type="text" name="invoice-name" size="5"
						placeholder="Название счета" required/> <input type="number"
						name="invoice-balance" size="5" placeholder="баланс" required /> <select
						name="invoice-credit">
						<option disabled>Кредитный?</option>
						<option selected value="true">да</option>
						<option selected value="false">нет</option>
					</select> <input type="number" name="invoice-percent" size="5"
						placeholder="Процент по кредиту" /> 
						<input type="submit" id='addInvoicesJsp' value="Добавить" />
				</div>
			</form>	

<form action="../inc" method="get">
<input type = "hidden" value='deleteInvoices' name="invoicesDelete">	
<div id="delete-invoice" style="visibility: hidden;">
		      <input required type="number" name="invoice-number"  placeholder="Номер счёта"/>
		      <input type="submit" id='deleteInvoicesJsp' value="Удалить"/> 
	</div>
	</form>	
	</div>	
</body>

<script src="int/js/incoming.js"></script>

</html>
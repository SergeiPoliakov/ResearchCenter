<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">	
	
	 <jsp:useBean id="incomesController"
		class="com.netcracker.unc.newmvc.dao.IncomeDAO" /> 
	 <jsp:useBean id="invoicesController"
		class="com.netcracker.unc.newmvc.dao.InvoiceDAO" /> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Income</title>
</head>
<body>
  <div>
 	<div ><label>Список доходов</label> </div> 
 	<table border="1">
<thead align="center">
		<tr>
			<th class="title">Номер</th>
			<th class="title">Название</th>
			<th class="title">Дата</th>
			<th class="title">Сумма</th>
			<th class="title">Ежемесячный?</th>
			<th class="title">Счет</th>
			<th class="title">Номер счёта</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="incomes"
			items="${incomesController.getAllIncomes(sessionScope.user)}">
			<tr>
				<td>${incomes.getIncomeId()}</td>
				<td>${incomes.getIncomeName()}</td>
				<td>${incomes.getDateIncome()}</td>
				<td>${incomes.getIncomeSum()}</td>
				<td>${incomes.isMonth()}</td>
				<td>${incomes.getIncomesInvoice().getInvoiceName()}</td>
				<td>${incomes.getIncomeId()}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<button id="statistic-menu-button" class="button"
			size="40" onclick="addIncome()">Новый доход</button>
<button id="statistic-menu-button-delete" class="button"
			size="10" onclick="deleteIncome()">Удалить</button>
			
<div id="add-income" style="visibility: hidden;">
<form class="for,-inline" action="IncomeServlet" method="get">
		      <input type="text" name="income-name"  size="5" placeholder="Название дохода"/>
		      <input type="date" name="income-date"  size="5" placeholder="Дата"/>
		      <input type="number" name="income-sum" size="5" placeholder="Сумма"> 
		      <select><selectname="income-month" size="2">
		        <option disabled>Еемесячный</option>
        	    <option selected value = "true">да</option>
        	    <option selected value = "false">нет</option>
        	  </select> 
        	  <select name="invoice-number">
		        <option disabled>Счёт</option>
		          <c:forEach var="invoice"
			        items="${invoicesController.getAllInvoice(sessionScope.user)}">
		            <option value = "${invoice.getInvoiceId()}">${invoice.getInvoiceName()}</option> 
		         </c:forEach>
		      </select>
		      <input type="submit" value="Добавить"/>
		    </form>
</div>			
			
<div id="delete-income" style="visibility: hidden;">
<form action="IncomeServlet" method="delete">
			  <b><label>Выберите доход, который необходимо удалить</label></b>
			  <select name="income-delete-number">
		        <option disabled>Доходы</option>
		          <c:forEach var="invoice"
			        items="${incomesController.getAllIncomes(sessionScope.user)}">
		            <option value = "${invoice.getIncomeId()}">${invoice.getIncomeName()}</option> 
		         </c:forEach>
		      </select>
		      <!-- <input type="text" name="income-number"  size="5" placeholder="Номер дохода"/> -->
		      <input type="submit" value="Удалить"/>
		    </form>
</div>						 
</div> 

</body>

<script src="interface/js/update_user.js"></script>
</html>
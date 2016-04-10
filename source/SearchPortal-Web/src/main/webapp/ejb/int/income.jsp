<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<body>

	<table border="1">
		<thead align="center">
			<tr>
				<th class="title">Номер дохода</th>
				<th class="title">Источник дохода</th>
				<th class="title">Сумма</th>
				<th class="title">Дата</th>
				<th class="title">Ежемесячный</th>
				<th class="title">Счет</th>
				<th class="title">Номер счета</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="income" items="${allIncome}">
				<tr>
					<td>${income.getIncomeId()}</td>
					<td>${income.getIncomeName()}</td>
					<td>${income.getIncomeSum()}</td>
					<td>${income.getDataIncome()}</td>
					<td>${income.isMonth()}</td>
					<td>${income.getIncomesIvoice.getInvoiceName()}</td>
					<td>${income.getIncomesIvoice.getInvoiceId()}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<button id="statistic-menu-button" class="button"
		onclick="addIncome()">Добавить доход</button>

	<button id="statistic-menu-button-delete" class="button"
		onclick="deleteIncome()">Удалить доход</button>

	<form action="../inc" method="get">
		<input type="hidden" value='addIncomes' name="incomes">
		<div id="add-income" style="visibility: hidden;">
			<!-- <input type="hidden" value="addInvoices" name="invoices" />  -->
			<input type="text" name="income-name" size="5"
				placeholder="Название счета" /> <input type="number"
				name="income-balance" size="5" placeholder="баланс" /> <select
				name="income-credit">
				<option disabled>Кредитный?</option>
				<option selected value="true">да</option>
				<option selected value="false">нет</option>
			</select> <input type="number" name="income-percent" size="5"
				placeholder="Процент по кредиту" /> <input type="submit"
				id='addIncomesJsp' value="Добавить" />
		</div>
	</form>
	<form action="../inc" method="get">
		<input type="hidden" value='deleteIncomes' name="incomesDelete">
		<div id="delete-income" style="visibility: hidden;">
			<input type="number" name="income-number" placeholder="Номер счёта" />
			<input type="submit" id='deleteIncomesJsp' value="Удалить" />
		</div>
	</form>

</body>
<script src="int/js/incoming.js"></script>

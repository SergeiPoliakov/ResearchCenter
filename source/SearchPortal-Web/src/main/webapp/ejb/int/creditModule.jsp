<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Credit</title>
</head>
<body>
	<!-- 
	<table border="1">
		<thead align="center">
			<tr>
				<th class="title">Название кредита</th>
				<th class="title">Сумма</th>
				<th class="title">Остаток платежа</th>
				<th class="title">Процентная ставка</th>
				<th class="title">Дата получения</th>
				<th class="title">Период платежа(дней)</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="credit" items="${invoicesControllerItems}">
				<tr>
					<td>${credit.getCreditName()}</td>
					<td>${credit.getCreditValue()}</td>
					<td>${credit.getCreditBalance()}</td>
					<td>${credit.getCreditPercent()()}</td>
					<td>${credit.getReceivingDate()}</td>
					<td>${credit.getPayPeriod()}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<button id="statistic-menu-button" class="button" onclick="addCredit()">Добавить
		кредит</button>

	<button id="statistic-menu-button-delete" class="button"
		onclick="deleteCredit()">Удалить кредит</button>

	<form action="../credit" method="get">
		<input type="hidden" value='addCredit' name="credit">
		<div id="add-credit" style="visibility: hidden;">

			<input type="text" name="credit-name" size="5"
				placeholder="Название кредита" /> <input type="number"
				name="credit-value" size="5" placeholder="Сумма" /> <input
				type="number" name="credit-balance" size="5"
				placeholder="Остаток платежа" /> <input type="number"
				name="credit-percent" size="5" placeholder="Процентная ставка" /> <input
				type="number" name="credit-date" size="5"
				placeholder="Дата получения" /> <input type="number"
				name="credit-period" size="5" placeholder="Период платежа(дней)" />
			<input type="submit" id='addCreditJsp' value="Добавить" />
		</div>
	</form>
	<form action="../credit" method="get">
		<input type="hidden" value='deleteCredit' name="creditDelete">
		<div id="delete-credit" style="visibility: hidden;">
			<input type="text" name="credit-number" placeholder="Номер счёта" />
			<input type="submit" id='deleteCreditJsp' value="Удалить" />
		</div>
	</form>
	-->
</body>
</html>
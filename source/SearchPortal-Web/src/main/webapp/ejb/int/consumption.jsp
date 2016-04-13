<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<body>
	<jsp:include page="/inc"></jsp:include>
	<div style="width: 100%">
	<table border="1" class="table invoice-table">
		<thead align="center">
			<tr>
				<th class="title">Номер Расхода</th>
				<th class="title">Источник расхода</th>
				<th class="title">Сумма</th>
				<th class="title">Дата</th>
				<th class="title">Ежемесячный</th>
				<th class="title">Счет</th>
				<th class="title">Номер счета</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="consumption" items="${sessionScope.allConsumption}">
				<tr>
					<td>${consumption.getConsumptionId()}</td>
					<td>${consumption.getConsumptionName()}</td>
					<td>${consumption.getConsumptionSum()}</td>
					<td>${consumption.getDateConsumption().toString()}</td>
					<td>${consumption.isMonth()}</td>
					<td>${consumption.getParentObj().getObjectName()}</td>
					<td>${consumption.getParentObj().getFinObjectId()}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>

	<button id="statistic-menu-button" class="button"
		onclick="addConsumption()">Добавить расход</button>

	<button id="statistic-menu-button-delete" class="button"
		onclick="deleteConsumption()">Удалить расход</button>

	<form action="../inc" method="get">
		<input type="hidden" value='addConsumptions' name="consumptions">
		<div id="add-consumption" style="visibility: hidden;">
			<!-- <input type="hidden" value="addInvoices" name="invoices" />  -->
			<input type="text" name="consumption-name" size="5"
				placeholder="название" required /> <input type="number"
				name="consumption-balance" size="5" required placeholder="сумма" step="50" />
			<select name="consumption-regular">
				<option disabled>Ежемесячный?</option>
				<option selected value="true">да</option>
				<option selected value="false">нет</option>
			</select> <input required type="date" name="consumption-date" placeholder="Дата" /> <select
				name="consumption-parent">
				<option disabled>Счёт</option>
				<c:forEach var="invoice" items="${allCases}">
					<option value="${invoice.getFinObjectId()}">${invoice.getObjectName()}</option>
				</c:forEach>
			</select> <input type="submit" id='addConsumptionsJsp' value="Добавить" />
		</div>
	</form>
	<form action="../inc" method="get">
		<input type="hidden" value='deleteConsumptions' name="consumptions">
		<div id="delete-consumption" style="visibility: hidden;">
			<input required type="number" name="consumption-number"
				placeholder="Номер счёта" /> <input type="submit"
				id='deleteconsumptionsJsp' value="Удалить" />
		</div>
	</form>

</body>
<script src="int/js/incoming.js"></script>

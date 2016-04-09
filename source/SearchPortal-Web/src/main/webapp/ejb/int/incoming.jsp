<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<jsp:include page="/inc"></jsp:include>
<div id="add-sum">
	<div>Мгновенное увеличение баланса</div>
	<div>
		<label>Текущий баланс:</label>
		${sessionScope.invoicesController}
	</div>

	<form action="../inc" method="get">
		<input type="hidden" value="incomingBalance" name="incoming" /> <select
			name="invoiceNumber">
			<option disabled>Счёт</option>
			<c:forEach var="invoice"
				items="${invoicesControllerItems}">
				<option value="${invoice.getInvoiceId()}">${invoice.getInvoiceName()}</option>
			</c:forEach>
		</select>
		<input type="number" name="add-sum-val" value="0" min="0" size="5"
			step="50" /> <input type="submit" value="добавить" />
	</form>

</div>

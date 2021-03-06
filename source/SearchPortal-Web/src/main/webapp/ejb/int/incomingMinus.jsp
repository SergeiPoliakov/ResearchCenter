<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/inc"></jsp:include>

   <div id="add-sum">
	<div>Мгновенное изъятие денежных средств</div>
	<div>
		<label>Текущий баланс:</label>
		${sessionScope.invoicesController}
	</div>

	<form action="../inc" method="get">
		<input required type="hidden" value="incomingBalance" name="incomingMinus" /> 
		<select	name="invoiceNumber">
			<option disabled>Счёт</option>
			<c:forEach var="invoice"
				items="${invoicesControllerItems}">
				<option value="${invoice.getInvoiceId()}">${invoice.getInvoiceName()}</option>
			</c:forEach>
		</select>
		<input type="number" name="get-sum-val" value="0" min="0" size="5"
			step="50" required/> <input type="submit" value="вычесть" />
	</form>

</div>
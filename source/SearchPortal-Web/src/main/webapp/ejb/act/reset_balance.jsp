<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<label>Изменить баланс?</label> <select id="balSelect"
			onchange="changeBalCost()">
			<c:choose>
				<c:when test="${sessionScope.balanceObjects.size() > 0}">
					<c:forEach var="obj" items="${sessionScope.balanceObjects}">
						<option value="${obj.getFinObjectId()}">${obj.getObjectName() }</option>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<option value="error" disabled="disabled">счета
						отсутствуют</option>
				</c:otherwise>
			</c:choose>
		</select>
		<c:forEach var="obj" items="${sessionScope.balanceObjects}">
			<label id="bal${obj.getFinObjectId()}" class="balLabel"
				style="display: none">текущий счет: <span id="balCost">${sessionScope.balanceParamObjects.get(obj.getObjectName())}</span></label>
		</c:forEach>

		<form action="../cust" method="get"
			onsubmit="return checkBalanceEmpty()">
			<input type="hidden" id="balHidden" name="balObjId" value="" /> <input
				type="hidden" name="custom" value="changeBalance" />
			<div style="float: left; width: 50%">
				<label>изменить на:</label><input type="text" name="balTextCost"
					onkeypress="validate(this)" id="balTextCost"
					style="margin-left: 5px" onfocus="checkBalanceEmptyHelp(this)"
					onblur="checkBalanceEmptyHelp(this)" /><input type="submit"
					value="изменить" />
			</div>
		</form>
		<form action="../cust" method="get" onsubmit="return checkBalReset()">
			<input type="hidden" name="custom" value="resetBalance" />
			<div style="float: left;">
				<input type="submit" value="сброс" id="resetBalanceResetSubmit" />
			</div>
		</form>
	</div>
</body>
<script src="act/js/reset_balance.js"></script>
</html>
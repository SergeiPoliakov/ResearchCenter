<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="">
<title>Insert title here</title>
</head>
<body>
	<div style="float: left" style="margin-top: 5px; font-size: 14px;">
		<label>Изменить баланс?</label> 
		<select id="balSelect"
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
<div>
		<form action="../cust" method="get"
			onsubmit="return checkBalanceEmpty()">
			<input type="hidden" id="balHidden" name="balObjId" value="" /> <input
				type="hidden" name="custom" value="changeBalance" />

				<label style="font-size: 16px;">изменить на:</label>
				<input type="text" name="balTextCost"
					onkeypress="validate(this)" id="balTextCost"
					 onfocus="checkBalanceEmptyHelp(this)"
					onblur="checkBalanceEmptyHelp(this)" /><button type="submit" class="btn btn-reset" style="float: left; margin-left: 50%">Изменить</button>
			
		</form>
		<form action="../cust" method="get" onsubmit="return checkBalReset()">
			<input type="hidden" name="custom" value="resetBalance" />
				<button type="submit" id="resetBalanceResetSubmit" class="btn btn-reset" style="float: right">Сброс</button>

		</form>
		</div>
		</div>

</body>
<script src="act/js/reset_balance.js"></script>
</html>
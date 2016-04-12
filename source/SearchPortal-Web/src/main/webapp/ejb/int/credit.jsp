<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/new-main.css" rel="stylesheet">


</head>
<body>
	<c:set var="jsp" value="credit" scope="request" />
	<jsp:include page="/credit" />
<div style="float: left">
	<form action="/credit" method="get">
		<input type="hidden" value="gotData" name="DataRow">
		<table border="1" class="table table-striped">
			<thead>
				<tr>
					<th class="title">ID</th>
					<th class="title">Название кредита</th>
					<th class="title">Сумма кредита</th>
					<th class="title">Процентная ставка</th>
					<th class="title">Дата получения</th>
					<th class="title">Период платежа</th>
				</tr>
			</thead>
			<tbody id="tableBody">
				
					<c:forEach var="userCredit" items="${userCredits}">
						<tr>
							<td><input type="text" name="id" value="${userCredit.getCreditID()}" disabled></td>
							<td>${userCredit.getCreditName()}</td>
							<td>${userCredit.getCreditValue()}</td>
							<td>${userCredit.getCreditPercent()}</td>
							<td>${userCredit.getReceivingDate()}</td>
							<td>${userCredit.getPayPeriod()}</td>
							<td><input type="submit" name="del" value="Delete"></td>

						</tr>

				</c:forEach>
								
				
			</tbody>
		</table>
		</form>
		</div>
		<div style="padding-left: 20px; float: left">
			<form action="/credit" method="get">
						<input type="hidden" value="addData" name="addDataRow">
						<input type="text" name="creditName" />
						<input type="text" name="creditValue" />
						<input type="text" name="creditPercent"  />
						<input type="date" name="dateCredit">
						<input type="text" name="creditPeriod"  />
						<input type="submit" name="addCredit" value="Submit">
					</form>
				</div>
	
</body>
</html>
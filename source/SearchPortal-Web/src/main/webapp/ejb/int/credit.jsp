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
	<jsp:include page="/credit" />
<div style="float: left">
	<form action="../credit" method="get">
		<input type="hidden" value="gotData" name="DataRow">
		<table border="1" class="table table-striped">
			<thead>
				<tr>
					
					<th class="title">Название кредита</th>
					<th class="title">Сумма кредита</th>
					<th class="title">Процентная ставка</th>
					<th class="title">Дата получения</th>
					<th class="title">Период платежа</th>
					<th class="title">ID</th>
				</tr>
			</thead>
			<tbody id="tableBody">
				
					<c:forEach var="userCredit" items="${userCredits}">
						<tr>
							
							<td>${userCredit.getCreditName()}</td>
							<td>${userCredit.getCreditValue()}</td>
							<td>${userCredit.getCreditPercent()}</td>
							<td>${userCredit.getReceivingDate()}</td>
							<td>${userCredit.getPayPeriod()}</td>
							<td><input type="submit" name="del" value="${userCredit.getCreditID()}"></td>

						</tr>

				</c:forEach>
								
				
			</tbody>
		</table>
		</form>
		</div>
		<div style="padding-left: 0px; float: left">
			<form action="../credit" method="get">
				<div>
						<input type="hidden" value="addData" name="addDataRow">
						<input type="text" name="creditName" placeholder="Название"/>
						<input type="text" name="creditValue" placeholder="Сумма кредита"/>
						<input type="text" name="creditPercent"  placeholder="Процентная ставка"/>
						
						<input type="date" name="dateCredit" >
						<input type="text" name="creditPeriod"  placeholder="Период платежа, дней"/>
						<input type="submit" name="addCredit" value="Добавить кредит">
				</div>
					</form>
				</div>
	
</body>
</html>
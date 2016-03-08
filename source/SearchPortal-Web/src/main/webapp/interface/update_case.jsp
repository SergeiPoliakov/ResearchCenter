<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.title {
	font-size: 12pt;
}

.result {
	font-size: 9pt;
}

td {
	width: 7%
}

input[type="text"] {
	width: 100%;
	font-size: 8pt;
}

input[type="date"] {
	width: 100%;
	font-size: 8pt;
}

select {
	width: 100%;
	font-size: 8pt;
}
</style>
</head>
<body>

	<jsp:useBean id="activeCasesController"
		class="com.netcracker.unc.newmvc.dao.ActiveCasesDAO" />

	<input type="text" onkeypress="help()" onkeydown="backspaceHelp()"
		id="inputUpdateCase" style="margin-bottom: 7px; width: 20%"
		onfocus="refreshHelpInput()" onblur="refreshHelpInput()"
		onsubmit="return false" />
	<br />
	<form id="updateCaseTable">
		<div>
			<table border="1">
				<thead align="center">
					<tr>
						<th class="title">Название</th>
						<th class="title">Выделено денег</th>
						<th class="title">Дата создания</th>
						<th class="title">Дата окончания</th>
						<th class="title">Подзадача для</th>
						<th class="title">Приоритет</th>
						<th class="title">Правка</th>
					</tr>
				</thead>
				<tbody id="tableBody">
					<c:forEach var="activeCase"
						items="${activeCasesController.getActiveCases(sessionScope.user.getUserId())}">
						<c:set var="caseID" value="${activeCase.getObjectId()}"
							scope="session"></c:set>
						<tr>
							<td class="result">${activeCase.getSpace()}<c:if
									test="${activeCase.getSpace() != ''}">
							&#8627&nbsp;
							</c:if> ${activeCase.getCaseName()}
							</td>
							<td class="result">${activeCase.getSpace()}${activeCase.getCaseCost()}</td>
							<td class="result">${activeCase.getSpace()}${activeCase.getStartDate()}</td>
							<td class="result">${activeCase.getSpace()}${activeCase.getEndDate()}</td>
							<td class="result">${activeCase.getSpace()}${activeCase.getParentName()}</td>
							<td class="result">${activeCase.getSpace()}${activeCase.getPriorityStr()}</td>
							<td align="center" valign="middle"><label
								style="font-size: 7pt; color: blue; cursor: pointer; text-decoration: underline;"
								onclick="changeCase(this)">изменить</label><input type="hidden"
								value="${activeCase.getObjectId()}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</form>

</body>

<script src="interface/css/update_case.js"></script>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Bootstrap core CSS -->
<link href="css/new-main.css" rel="stylesheet">
<!-- <style>
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
</style>-->
</head>
<body>

	<c:set var="jsp" value="update-case" scope="request" />
	<jsp:include page="/load" />

	<input class="form-control" type="text" onkeypress="help()"
		onkeydown="backspaceHelp()" id="inputUpdateCase"
		style="margin-bottom: 7px; width: 20%" onfocus="refreshHelpInput()"
		onblur="refreshHelpInput()" onsubmit="return false" />
	<br />
	<form id="updateCaseTable">
		<input type="hidden" name="interfaces" value="updateCase"
			id="upCaseHidden" />
		<div class="table-responsive">
			<table border="1" class="table table-striped">
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
					<c:forEach var="activeCase" items="${userActiveObjects}">
						<c:set var="caseID" value="${activeCase.getObjectId()}"
							scope="session"></c:set>
						<tr>
							<td class="result">${activeCase.getSpaceHierarchy()}<c:if
									test="${activeCase.getSpaceHierarchy() != ''}">
							&#8627&nbsp;
							</c:if> ${activeCase.getCaseName()}
							</td>
							<td class="result">${activeCase.getSpaceHierarchy()}${activeCase.getCaseCost()}</td>
							<td class="result">${activeCase.getSpaceHierarchy()}${activeCase.getStartDate()}</td>
							<td class="result">${activeCase.getSpaceHierarchy()}${activeCase.getEndDate()}</td>
							<td class="result">${activeCase.getSpaceHierarchy()}${activeCase.getParentName()}</td>
							<td class="result">${activeCase.getSpaceHierarchy()}${activeCase.getPriorityStr()}</td>
							<td align="center" valign="middle"><label
								style="font-size: 7pt; color: blue; cursor: pointer; text-decoration: underline;"
								onclick="changeCase(this)">изменить</label><label
								style="font-size: 7pt; color: blue; cursor: pointer; text-decoration: underline; display: none"
								onclick="delFunction()">удалить</label><input type="hidden"
								value="${activeCase.getObjectId()}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</form>

</body>

<script src="act/js/update_case.js"></script>

</html>
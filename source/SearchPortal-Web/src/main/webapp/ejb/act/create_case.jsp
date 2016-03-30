<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create new case</title>
</head>

<body>

	<c:set var="jsp" value="create-case" scope="request" />
	<jsp:include page="/load" />
	<div align="center" id="createCaseDiv" class="form-group">
		<form onsubmit="return checkRegExp()" action="../int"
			id="createCaseForm">
			<input type="hidden" class="form-control" value="createCase"
				name="interfaces" />
			<table class="createCase">
				<tr>
				</tr>
				<tr>
					<td colspan="3"><hr /></td>
				</tr>
				<tr>
					<td><label>Введите название задачи:</label></td>
					<td><input type="text" name="name_case" class="form-control"
						class="caseInput" id="nameCase" />
					<td><label id="nameError"></label></td>
				</tr>
				<tr>
					<td><label>Выберите категорию:</label></td>

					<!--  <td><input type="text" class="form-control" name="type_case" id="type"
					value="Credit" /></td>  -->

					<td><select class="form-control" name="type_case"
						onchange="printText(this)" id="selectCategories">
							<c:forEach var="category" items="${generalCases}">
								<option value="${category.getFinObjectId()}">${category.getObjectName()}</option>
							</c:forEach>
					</select></td>
					<td />
				</tr>
				<tr>
					<td><label>Использовать как подзадачу?</label></td>
					<td><div>
							<input type="checkbox" value="yes" id="yes"
								onclick="changeCheck(this)" /><label>Да</label><br> <input
								type="checkbox" value="no" id="no" onclick="changeCheck(this)"
								checked="checked" /><label>Нет</label>
						</div>
					<td><select name="parentBlock" id="parentBlock"
						style="visibility: hidden">
							<c:forEach var="category" items="${listCases}">
								<option value="${category.getFinObjectId()}">${category.getObjectName()}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td><label>Выберите приоритет:</label></td>
					<td><select name="priority" id="priority" class="form-control">
							<option value="0.75">Высокий</option>
							<option value="0.5">Средний</option>
							<option value="0.35">Низкий</option>
					</select>
				</tr>
				<tr>
					<td><label>Выберите дату окончания:</label></td>
					<td><input type="date" class="form-control" id="date_case"
						name="date_case"></td>
					<td><label id="dateError"></label></td>
				</tr>
				<tr>
					<td><label>Введите стоимость:</label></td>
					<td><input type="text" class="form-control" name="cost_case"
						id="cost_case" onkeypress="validate(this)" class="caseInput" /></td>
					<td><label id="costError"></label></td>
				</tr>
				<tr>
					<td colspan="3"><hr /></td>
				</tr>
				<tr>
					<td colspan="3" align="center"><button class="btn"
							type="submit" value="Создать" id="createCaseSubmit">Создать</button></td>
				</tr>
			</table>
		</form>
	</div>

</body>
<script type="text/javascript" src="act/js/create_case.js"></script>
</html>
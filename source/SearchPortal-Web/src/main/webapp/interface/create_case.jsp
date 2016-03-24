<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<!-- <link type="text/css" rel="stylesheet"
	href="interface/css/interface.css" /> -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Bootstrap core CSS -->
<link href="css/new-main.css" rel="stylesheet">
<!-- Custom styles for this template -->
<title>Create new case</title>
</head>

<body>

	<jsp:useBean id="listCases"
		class="com.netcracker.unc.newmvc.dao.UserDAO"></jsp:useBean>

	<div align="center" id="createCaseDiv" class="form-group">
		<form onsubmit="return checkRegExp()" action="interface" method="get"
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
							<option value="другое">Другое</option>
							<option value="транспорт">Транспорт</option>
							<option value="продукты">Продукты</option>
							<option value="жкх">ЖКХ</option>
							<option value="кредит">Кредит</option>
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
						</div></td>
					<td><select name="parentBlock" id="parentBlock"
						style="visibility: hidden">
							<c:forEach var="category"
								items="${listCases.getUserActiveCases(sessionScope.user.getUserId())}">
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

<script src="interface/js/create_case.js"></script>

</html>
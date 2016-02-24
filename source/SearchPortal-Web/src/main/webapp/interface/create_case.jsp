<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<link type="text/css" rel="stylesheet"
	href="interface/css/interface.css" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create new case</title>
</head>

<body>

	<jsp:useBean id="listCases"
		class="com.netcracker.unc.mvc.ObjectController"></jsp:useBean>

	<div align="center" id="createCaseDiv">
		<form onsubmit="return checkRegExp()" action="CreateCase" method="get"
			id="createCaseForm">
			<table class="createCase">
				<tr>
					<th colspan="3">Создание новой задачи</th>
				</tr>
				<tr>
					<td colspan="3"><hr /></td>
				</tr>
				<tr>
					<td>Введите название задачи:</td>
					<td><input type="text" name="name_case" class="caseInput"
						id="nameCase" />
				</tr>
				<tr>
					<td>Выберите категорию:</td>

					<!--  <td><input type="text" name="type_case" id="type"
					value="Credit" /></td>  -->

					<td><select name="type_case" onchange="printText(this)"
						id="selectCategories">
							<option value="другое">Другое</option>
							<option value="транспорт">Транспорт</option>
							<option value="продукты">Продукты</option>
							<option value="жкх">ЖКХ</option>
							<option value="кредит">Кредит</option>
					</select></td>
				</tr>
				<tr>
					<td>Использовать как подзадачу?</td>
					<td><input type="checkbox" value="yes" id="yes"
						onclick="changeCheck(this)" />yes <input type="checkbox"
						value="no" id="no" onclick="changeCheck(this)" checked="checked" />no
					</td>
					<td><select name="parentBlock" id="parentBlock"
						style="visibility: hidden">
							<c:forEach var="category"
								items="${listCases.getUserActiveCases(sessionScope.user.get_user_id())}">
								<option value="${category.get_fin_object_id()}">${category.get_object_name()}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>Выберите приоритет:</td>
					<td><select name="priority" id="priority">
							<option value="0.75">Высокий</option>
							<option value="0.5">Средний</option>
							<option value="0.35">Низкий</option>
					</select>
				</tr>
				<tr>
					<td>Выберите дату окончания:</td>
					<td><input type="date" id="date_case" name="date_case"></td>
				</tr>
				<tr>
					<td>Введите стоимость:</td>
					<td><input type="text" name="cost_case" id="cost_case"
						onkeypress="validate()" class="caseInput" /></td>
				</tr>
				<tr>
					<td colspan="3"><hr /></td>
				</tr>
				<tr>
					<td colspan="3" align="center"><input class="submit"
						type="submit" value="Создать" id="createCaseSubmit" /></td>
				</tr>
			</table>
		</form>
	</div>

</body>

<script src="interface/css/create_case.js"></script>

</html>
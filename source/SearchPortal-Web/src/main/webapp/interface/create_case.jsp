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

	<form action="CreateCase" method="get">
		<table class="createCase">
			<tr>
				<th colspan="3">Создание новой задачи</th>
			</tr>
			<tr>
				<td colspan="3"><hr /></td>
			</tr>
			<tr>
				<td>Введите название задачи:</td>
				<td><input type="text" name="name_case" class="caseInput" />
			</tr>
			<tr>
				<td>Выберите категорию:</td>

				<!--  <td><input type="text" name="type_case" id="type"
					value="Credit" /></td>  -->

				<td><select name="type_case" onchange="printText(this)"
					id="selectCategories">
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

</body>

<script type="text/javascript"
	src="../javascripts/kol/LimitationNumbers.js"></script>

<script type="text/javascript">
	// check created user cases on empty
	var el = document.getElementById("parentBlock");
	var options = el.options;
	if (options.length == 0) {
		var option = document.createElement("option");
		option.value = '0';
		option.text = 'пусто';
		el.appendChild(option);
	}
</script>


<script type="text/javascript">
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth() + 1;
	var yyyy = today.getFullYear();
	if (dd < 10) {
		dd = "0" + dd;
	}
	if (mm < 10) {
		mm = "0" + mm;
	}
	var currentDate = yyyy + "-" + mm + "-" + dd;
	document.getElementById("date_case").setAttribute("min", currentDate);
</script>

<!--  <script>
	function printText(element) {
		var el = element.value;
		var type = document.getElementById("type");
		if (el.valueOf() != "Other".valueOf()) {
			type.value = el;
		} else
			type.value = "";
	}
</script>	-->

<script>
	var parent = document.getElementById("parentBlock");
	var values = [];
	
	for (var i = 0; i < parent.length; i++) {
		values[i] = parent.options[i].value;
		parent.options[i].value = 0;
	}

	function changeCheck(element) {
		var element = element.value;
		var typeYes = document.getElementById("yes");
		var typeNo = document.getElementById("no");

		if (element.valueOf() == 'yes'.valueOf()) {
			for (var i = 0; i < parent.length; i++) {
				parent.options[i].value = values[i];
			}
			typeNo.checked = undefined;
			typeYes.checked = "checked";
			parent.style.visibility = "visible";
		} else {
			for (var i = 0; i < parent.length; i++) {
				parent.options[i].value = 0;
			}
			typeYes.checked = undefined;
			typeNo.checked = "checked";
			parent.style.visibility = "hidden";
		}
	}
</script>
<!--   <script>
	var categories = document.getElementById("selectCategories");
	var options = categories.options;
	var values = [];
	var parent = document.getElementById("parentBlock");

	for (var i = 0; i < options.length; i++) {
		values.push(options[i].value);
	}

	for (var i = 0; i < values.length; i++) {
		var option = document.createElement("option");
		option.value = values[i];
		option.text = values[i];
		parent.appendChild(option);
	}
</script> -->
</html>
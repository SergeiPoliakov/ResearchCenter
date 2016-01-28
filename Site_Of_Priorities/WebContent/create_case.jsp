<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
	table {}

</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>

	<form action="CreateCaseServlet" method="get" >
	<table>
		<tr>
			<th>Создание задачи</th>
		</tr>
		<tr><td colspan="3"><hr/></td></tr>
		<tr>
			<td>Введите название:</td>
			<td><input type="text" name="name_case"/>
		</tr>
		<tr>
			<td>Введите тип задачи:</td>
			<td><input type="text" name="type_case" id="type" value="Опа"/>
			<td>
				<select name="select" onchange="printText(this)">
					<option value="1" id="credit">Кредит</option>
					<option value="2" id="eat">Квартира</option>
					<option value="3" id="car">Автомобиль</option>
					<option value="4" id="room">ЖКХ</option>
					<option value="5" id="room">Продукты</option>
					<option value="6" id="room">Свой тип</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>Сделать как подзадачу?</td>
			<td>    
				<input type="checkbox" value="да" id="yes" onclick="changeCheck(this)" />да
								<input type="checkbox" value="нет" id="no" onclick="changeCheck(this)" checked="checked"/>нет
			</td>
			<td>
				<select name="select" id="parentBlock" style="visibility: hidden">
					<option value="1" id="credit">Кредит</option>
					<option value="2" id="eat">Квартира</option>
					<option value="3" id="car">Автомобиль</option>
					<option value="4" id="room">ЖКХ</option>
					<option value="5" id="room">Продукты</option>
					<option value="6" id="room">Свой тип</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>Введите приоритет:</td>
			<td><input type="text" name="priority_case"/></td>
		</tr>
		<tr>
			<td>Введите дату:</td>
			<td><input type="text" name="date_case"/></td>
		</tr>
		<tr><td colspan="3"><br/></td></tr>
		<tr>
			<td><input type="submit" value="Ввести"/></td>
		</tr>
	</table>
</form>

</body>

<script>
	function printText(element) {
		var el = parseInt(element.value);
		var type = document.getElementById("type");
		switch(el) {
			case 1: {
				type.value = "Кредит";
				aler("попал");
			}
			break;
			case 2: type.value = "Квартира";
			break;
			case 3: type.value = "Автомобиль";
			break;
			case 4: type.value = "ЖКХ";
			break;
			case 5: type.value = "Продукты";
			break;
			case 6: type.value = "";
			break;
		}	
	}
</script>
<script>
	function changeCheck(element) {
		var el = element.value;
		var sBlock = document.getElementById("parentBlock");
		var typeYes = document.getElementById("yes");
		var typeNo = document.getElementById("no");
		if(el.valueOf() == 'да'.valueOf()){
			typeNo.checked = undefined;
			typeYes.checked = "checked";
			sBlock.style.visibility = "visible";
			}
		else {
			typeYes.checked = undefined;
			typeNo.checked = "checked";
			sBlock.style.visibility = "hidden";
		}
	}
</script>
</html>
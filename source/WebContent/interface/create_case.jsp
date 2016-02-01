<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
table {
	
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create new case</title>
</head>

<body>

	<form action="CreateCaseServlet" method="get">
		<table>
			<tr>
				<th>Create new case</th>
			</tr>
			<tr>
				<td colspan="3"><hr /></td>
			</tr>
			<tr>
				<td>Select case name:</td>
				<td><input type="text" name="name_case" />
			</tr>
			<tr>
				<td>Select type of case:</td>
				<td><input type="text" name="type_case" id="type"
					value="Credit" />
				<td><select name="select" onchange="printText(this)" id="selectCategories">
						<jsp:include page="/interface/CreateCaseCategories" />
				</select></td>
			</tr>
			<tr>
				<td>Create as subcase?</td>
				<td>
					<input type="checkbox" value="yes" id="yes" onclick="changeCheck(this)" />yes 
					<input type="checkbox" value="no" id="no" onclick="changeCheck(this)" checked="checked" />no
				</td>
				<td>
					<select name="select" id="parentBlock" style="visibility: hidden" ></select>
				</td>
			</tr>
			<tr>
				<td>Choose priority:</td>
				<td><input type="text" name="priority_case" /></td>
			</tr>
			<tr>
				<td>Choose date:</td>
				<td><input type="text" name="date_case" /></td>
			</tr>
			<tr>
				<td colspan="3"><br /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form>

</body>

<script>
	function printText(element) {
		var el = element.value;
		var type = document.getElementById("type");
		if(el.valueOf() != "Other".valueOf()) {
			type.value = el;
		}
		else type.value = "";
	}
</script>
<script>
	function changeCheck(element) {
		var element = element.value;
		var typeYes = document.getElementById("yes");
		var typeNo = document.getElementById("no");

		var parent = document.getElementById("parentBlock");
		
		if (element.valueOf() == 'yes'.valueOf()) {
			typeNo.checked = undefined;
			typeYes.checked = "checked";
			parent.style.visibility = "visible";
		} else {
			typeYes.checked = undefined;
			typeNo.checked = "checked";
			parent.style.visibility = "hidden";
		}
	}
</script>
<script>
	var categories = document.getElementById("selectCategories");
	var options = categories.options;
	var values = [];
	var parent = document.getElementById("parentBlock");

	for(var i=0; i<options.length; i++) {
		values.push(options[i].value);
	}

	for(var i=0; i<values.length; i++) {
		var option = document.createElement("option");
		option.value = values[i];
		option.text = values[i];
		parent.appendChild(option);
	}
</script>
</html>
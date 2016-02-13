<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:useBean id="cases" class="com.netcracker.unc.mvc.ObjectController"></jsp:useBean>

	<%@ page import="java.util.List, java.util.ArrayList"%>
	<%!List<String> nameCases = new ArrayList<String>();
	List<Integer> idCases = new ArrayList<Integer>();
	String name = null;
	int id;%>

	<c:forEach var="casee" items="${cases.getUserActiveCases(1)}">
		<c:set var="name" value="${casee.get_object_name()}" scope="request"></c:set>
		<c:set var="id" value="${casee.get_fin_object_id()}" scope="request"></c:set>
		<%
			name = (String) request.getAttribute("name");
			nameCases.add(name.toLowerCase());
			id = (Integer)request.getAttribute("id");
			idCases.add(id);
		%>
	</c:forEach>

	<div>
		<input type="text" name="input" onkeypress="help()" id="input" /> <br>
		<select style="visibility: hidden;" id="select" onclick="record(this)">
		</select>
	</div>

</body>

<!-- this script select value in input text which user choose from help -->
<script>
	function record(element) {
		var select = element.options[element.selectedIndex].text;
		var input = document.getElementById("input");
		input.value = select;
	}

</script>

<!-- this script allow to help to find input cases name which user have to update -->
<script>
	function help() {
		var event = window.event; // get screen page event
		var key = event.which;
		var text = document.getElementById("input");
		var select = document.getElementById("select");
		var casesName = [];
		var casesId = [];

		<%for (int i = 0; i < nameCases.size(); i++) {%>
		casesName.push('<%=nameCases.get(i)%>');
		casesId.push(<%=idCases.get(i)%>);
		<%}%>

		while(select.options.length > 0) {
			select.remove(0);
		}
	
		if (text.value.length >= 3) {
			var regular = new RegExp("^" + text.value.toLowerCase()
					+ String.fromCharCode(key) + ".*");
			for (var i = 0; i < casesName.length; i++) {
				if (regular.test(casesName[i])) {
					var option = document.createElement("option");
					option.value = casesId[i];
					option.text = casesName[i];
					select.appendChild(option);
					select.style.visibility = "visible";
				} else {
					select.style.visibility = "hidden";
				}
			}
		}
	}
</script>

</html>
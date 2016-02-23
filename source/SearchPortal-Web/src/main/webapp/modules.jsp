<%@page import="com.netcracker.unc.mvc.dao.CategoryDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.netcracker.unc.priorityModule.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modules</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script src="javascripts/jquery-2.2.0.min.js"></script>
<script src="javascripts/mainModules.js"></script>
<link href="attitudes/css/attitude.css" rel="stylesheet" type="text/css" />
<link href="css/aspro.css" rel="stylesheet" type="text/css" />
<title>Добро пожаловать на сайт приоритетов</title>
</head>
<body>
	<%@ page
		import="com.netcracker.unc.mvc.dao.UserDAO, com.netcracker.unc.mvc.models.UserModel"%>
	<%!private UserModel user = null;%>

	<%
		response.setContentType("text/html;charset=Windows-1251");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
	%>

	<c:choose>
		<c:when test="${not empty sessionScope.user }">
			<%
				if (request.getSession().getAttribute("user") != null) {
							user = (UserModel) request.getSession().getAttribute("user");
						}
			%>
		</c:when>
		<c:otherwise>
			<c:redirect url="index.jsp" />
		</c:otherwise>
	</c:choose>
	<div class="header">
		<div id="logo">214*59</div>
		<div class="welcome" align="right" style="background-color: #92d36e;">
			Добро пожаловать <label
				style="color: red; font-size: 16pt; background-color: #92d36e; margin-right: 10px"><%=user.get_login()%></label>
		</div>
		<button id="cost-menu-button" class="button">Расходы</button>
		<!--
            
            Now not used
            
            <button id ="fast-add-cost" class="button">+</button>
            <button id ="income-menu-button" class="button">Доходы</button>
            <button id ="fast-add-income" class="button">+</button>-->
		<button id="statistic-menu-button" class="button">Статистика</button>
		<button id="statistic-menu-button" class="button"
			onclick="showCreateCase()">Создать</button>
		<div class="overlayInCons">
			<jsp:include page="/attitudes/IncomeConsumption"></jsp:include>
		</div>
	</div>
	<div class="module" id="select-module1">
		<div class="block-title">
			<%
				String headstring = "Просто селект";
				out.println(headstring);
			%>
		</div>
		<div class="block-information">
			<form action="modules.jsp" method="get">
				<input type="text" name="testquery" /> <input type="submit"
					value="UP" />
			</form>
			<%
				if ((request.getParameter("testquery") != null) && (!request.getParameter("testquery").trim().isEmpty())) {
			%>
			<%=request.getParameter("testquery")%>
			<%
				}
			%>
		</div>
	</div>


	<div id="animationAddCase" style="visibility: hidden"></div>
	<div class="create-case" id="create-case" style="visibility: hidden">
		<jsp:include page="interface/create_case.jsp" />
	</div>


	<div class="module" id="priority-module">
		<div class="block-title">Приоритеты</div>
		<div class="block-information">
			<form action="PriorityMod" method="get">
				<input type="hidden" name="user_id" value="1005" /> <input
					type="submit" value="UP" />
			</form>
			<%
				ReceivingData rd = new ReceivingData();
				com.netcracker.unc.mvc.dao.CategoryDao cd = new CategoryDao();
			%>

			<!--FillHTMLTable.toHTMLString(cd.getAllObjectsDB())-->

			<%=rd.toString()%>

		</div>
	</div>
	<div class="module" id="test1">
		<div class="block-title">Пример модуля 1</div>
		<div class="block-information">
			Как назначить кнопку в меню для модуля? Открываем mainModules.js
			каждой фукции соответствует кнопка меню. Смотрим, и делаем по
			аналогии. В пункте меню, при нажатии которого нужно открывать модуль
			прописываем параметр: block, например:
			$("#priority-module").css("display", "block"); <b>Во всех</b>
			остальных пунктах меню прописываем скрывание модуля. Например:
			$("#priority-module").css("display", "none");

		</div>
	</div>
	<div class="module" id="test2">
		<div class="block-title">Тут очередной пример модуля</div>
		<div class="block-information">Тут какая-то информация</div>
	</div>
	<div class="module" id="test3">
		<div class="block-title">Третий пример модуля</div>
		<div class="block-information">Тут какая-то информация</div>
	</div>
</body>
<script src="javascripts/aspro.js"></script>
</html>

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
	<jsp:useBean id="userDAO" class="com.netcracker.unc.newmvc.dao.UserDAO" />
	<jsp:useBean id="user"
		class="com.netcracker.unc.newmvc.dao.models.UserModel" />
	<c:set var="checkCookie" value="error"></c:set>
	<c:forEach var="userCookie" items="${pageContext.request.cookies}">
		<c:choose>
			<c:when test="${userCookie.name == 'userID'}">
				<!-- check user session -->
				<c:if test="${not empty sessionScope.user}">
					<jsp:include page="/authorization" />
					<c:if test="${sessionScope.user.getUserId() == 0}">
						<c:redirect url="index.jsp"></c:redirect>
					</c:if>
					<c:set var="checkCookie" value="ok"></c:set>
				</c:if>
			</c:when>
		</c:choose>
	</c:forEach>
	<c:if test="${checkCookie == 'error'}">
		<c:redirect url="index.jsp" />
	</c:if>
	<jsp:include page="/custom"></jsp:include>

	<%
		response.setContentType("text/html;charset=Windows-1251");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
	%>

	<div class="header">
		<div id="logo">214*59</div>

		<div class="welcome" align="right" style="background-color: #92d36e;"
			id="welcomeUser">
			Добро пожаловать <label
				style="color: red; font-size: 16pt; background-color: #92d36e; margin-right: 10px"><c:out
					value="${sessionScope.user.getLogin()}" /></label>
			<p />
			<label id="editUserLabel" onclick="showHideUpdateUser()">редактировать
				профиль</label>
			<form action="authorization">
				<input type="hidden" value="logOut" name="authorization" /> <input
					type="submit" value="Выход" id="logOutSubmit" />
			</form>
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

		<button id="statistic-menu-button" class="button"
			onclick="showActiveCases()">Текущие</button>

		<button id="statistic-menu-button" class="button"
			onclick="showIncoming()">+</button>
			
		<button id="statistic-menu-button" class="button"
			onclick="showInvoices()">Счета</button>
			
		<div class="overlayInCons">
			<jsp:include page="/attitudes/income_consumption.jsp"></jsp:include>
		</div>
	</div>

	<div class="controlSalary" id="controlSalaryMain">
		<jsp:include page="attitudes/salary_control.jsp"></jsp:include>
	</div>

	<c:set var="checkSalaryBro" value="${checkSalary}" scope="page" />
	<c:if test="${checkSalaryBro == 'error'}">
		<div id="helloCase">
			<label class="welcomeCase" id="welcomeCase1"><b>Добро
					пожаловать на сайт <span style="margin-left: 40px">приоритетов!</span>
			</b></label>
			<p>
				<label class="welcomeCase" id="welcomeCase2">Пожалуйста,
					введите вашу зарплату:</label>
			</p>
			<form action="custom" onsubmit="return regularAddSalary()">
				<input type="hidden" value="addSalary" name="custom" /> <input
					type="text" id="welcomeCaseInput" name="salary"
					onkeypress="validate(this)" /><label class="welcomeCase"
					style="margin-left: 3px">руб.</label> <input type="submit"
					value="ввести" id="addSalarySubmit" />
			</form>
		</div>
	</c:if>

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

	<div id="updateCase" style="visibility: hidden;">
		<jsp:include page="interface/update_case.jsp" />
	</div>

	<div id="incoming" style="visibility: hidden;">
		<jsp:include page="interface/incoming.jsp" />
	</div>
	
	<div id="invoices" style="visibility: hidden;">
		<jsp:include page="interface/invoices.jsp" />
	</div>
	

	<!-- needPay is test parameter which contain payment object -->
	<c:set var="needPay" value="no" scope="page" />
	<c:if test="${checkSalaryBro == 'ok'}">
		<c:if test="${empty controlSalary}">
			<c:if test="${needPay == 'yes' }">
				<div id="caseInterview">
					<jsp:include page="/control"></jsp:include>
				</div>
			</c:if>
		</c:if>
	</c:if>

	<div id="editUser" style="visibility: hidden">
		<jsp:include page="/interface/update_user.jsp"></jsp:include>
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
<script src="javascripts/incoming.js"></script>
<script src="interface/js/create_case.js"></script>

</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Сайт приоритетов</title>

<!-- Bootstrap core CSS -->
<link href="css/new-main.css" rel="stylesheet">
<script src="javascripts/modulesHide.js"></script>
<!-- Custom styles for this template -->
<link href="css/sticky-footer-navbar.css" rel="stylesheet">
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

	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"><img src="img/logo.png"
					alt="Logo" width="214" height="59" id="Insert_logo"
					style="background-color: #92D36E;" /></a>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<div class="nav" align="right" style="color: #fef8a0 ">
					<h5>Добро пожаловать <label><c:out
							value="${sessionScope.user.getLogin()}" /></label></h5>
				</div>

				<ul class="nav navbar-nav">
					<li><div class="btn-group">
							<button class="btn" onclick="showHideModule(this)"
								id="consumption">Расходы</button>
							<button class="btn">+</button>
						</div></li>
					<li><div class="btn-group">
							<button class="btn" onclick="showIncomes()">Доходы</button>
							<button class="btn" onclick="showIncoming()">+</button>
						</div></li>
					<li>
						<div class="btn-group">
							<button class="btn" onclick="show_consump()">Статистика</button>
						</div>
					</li>
					<li>
						<div class="btn-group">
							<button class="btn" onclick="showInvoices()">Счета</button>
						</div>
					</li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><div class="btn-group">
							<a id="red-btn" href="#"></a>
						</div></li>
					<li><div class="btn-group">
							<button class="btn" id="personalArea"
								onclick="showHideModule(this)">Личный кабинет</button>
							<button class="btn">Выход</button>
						</div></li>
				</ul>
			</div>
		</div>
	</nav>

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

	<!-- Begin page content -->
	<div class="container-fluid" style="height: 100%">
		<div class="container" id="consumptionDiv" style="display: none">
			<div class="module" id="income">
				<div class="block-title">Создание новой задачи</div>
				<div class="block-information">

					<div class="create-case" id="create-case">
						<jsp:include page="interface/create_case.jsp" />
					</div>

				</div>
			</div>

			<div class="module" id="consump">
				<div class="block-title">Редактирование задач</div>
				<div class="block-information">
					<div id="updateCase">
						<jsp:include page="interface/update_case.jsp" />
					</div>
				</div>
			</div>

			<div class="module" id="category-priority">
				<div class="block-title">Приоритеты</div>
				<div class="block-information" id="bi-category-priority">
					<jsp:include page="priority_module.jsp" />
				</div>
			</div>

			<div class="module" id="category-module">
				<div class="block-title">Категории</div>
				<div class="block-information" id="bi-category-module">
					<jsp:include page="category_controller.jsp" />
				</div>
			</div>
		</div>


		<div class="container" id="personalAreaUserDiv" style="display: none">
			<div class="module" id="stat">
				<div class="block-title">Редактирование профиля</div>
				<div class="block-information">
					<div id="editUser">
						<jsp:include page="/interface/update_user.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
		
	</div>

	<div class="container">
		<div class="module-right" id="income">
			<div class="block-information-right">
				<div class="overlayInCons">
					<jsp:include page="/attitudes/income_consumption.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
	<div id="invoices" style="visibility: hidden;">
		<jsp:include page="interface/invoices.jsp" />
	</div>
	<div id="incoming" style="visibility: hidden;">
		<jsp:include page="interface/incoming.jsp" />
    </div>
	<div id="incomes" style="visibility: hidden;">
		<jsp:include page="interface/income.jsp" />
	</div> 
	<footer>
		<div class="container-footer">
			<p class="text-muted">©УНЦ 2016 год.</p>
		</div>
	</footer>
</body>
<script src="javascripts/welcome-aspro.js"></script>
<script src="javascripts/bootstrap.min.js"></script>
<script src="javascripts/incoming.js"></script>
</html>
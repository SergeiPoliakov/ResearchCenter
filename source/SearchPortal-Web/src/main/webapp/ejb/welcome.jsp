<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="description" content="" />
<meta name="author" content="" />
<link rel="icon" href="../../favicon.ico" />
<title>Сайт приоритетов</title>
<link href="css/new-main.css" rel="stylesheet" />
</head>
<body>

	<c:set var="jsp" value="welcome" scope="request"></c:set>
	<jsp:include page="/load" />
	<c:set var="jsp" value="welcome" scope="request" />
	<c:if test="${page != 'page-ok'}">
		<c:redirect url="../" />
	</c:if>

	<c:if test="${bankrupt == 'true'}">
		<li id="bankrupt" />
	</c:if>


	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"><img src="../img/logo.png"
					alt="Logo" width="214" height="59" id="Insert_logo"
					style="background-color: #92D36E;" /></a>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<div class="nav" align="right" style="color: #fef8a0">
					<h5>
						Добро пожаловать <label><c:out
								value="${sessionScope.user.getLogin()}" /></label>
					</h5>
				</div>

				<ul class="nav navbar-nav">
					<li><div class="btn-group">
							<button class="btn" onclick="showHideModule(this)"
								id="consumption">Расходы</button>
							<button class="btn">+</button>
						</div></li>
					<li><div class="btn-group">
							<button class="btn" onclick="show_consump()">Доходы</button>
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
							<a id="red-btn" onclick="resetBalance()"></a>
						</div></li>
					<li><div class="btn-group">
							<button class="btn" id="personalArea"
								onclick="showHideModule(this)">Личный кабинет</button>
							<form action="../auth" method="get">
								<input type="hidden" name="authorization" value="logOut"><input
									type="submit" value="Выход" class="btn" />
							</form>
						</div></li>
				</ul>
			</div>
		</div>
	</nav>

	<div id="resetBalanceDiv" style="display: none">
		<jsp:include page="act/reset_balance.jsp" />
	</div>

	<div class="container-fluid main-container" style="height: 100%">
		<div class="container" id="consumptionDiv" style="display: none">
			<div class="module" id="income">
				<div class="block-title">Создание новой задачи</div>
				<div class="block-information">
					<div class="create-case" id="create-case">
						<jsp:include page="act/create_case.jsp" />
					</div>
				</div>
			</div>

			<div class="module" id="consump">
				<div class="block-title">Редактирование задач</div>
				<div class="block-information">
					<div id="updateCase">
						<jsp:include page="act/update_case.jsp" />
					</div>
				</div>
			</div>

		</div>

		<div class="container" id="personalAreaUserDiv" style="display: none">
			<div class="module" id="stat">
				<div class="block-title">Редактирование профиля</div>
				<div class="block-information">
					<div id="editUser">
						<jsp:include page="act/update_user.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div class="module-right" id="income">
		<div class="overlayInCons">
			<jsp:include page="vis/income_consumption.jsp"></jsp:include>
		</div>
	</div>


	<div class="controlSalary" id="controlSalaryMain">
		<jsp:include page="int/salary_control.jsp"></jsp:include>
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
			<form action="../cust" onsubmit="return regularAddSalary()">
				<input type="hidden" value="addSalary" name="custom" /> <input
					type="text" id="welcomeCaseInput" name="salary"
					onkeypress="validate(this)" /><label class="welcomeCase"
					style="margin-left: 3px">руб.</label> <input type="submit"
					value="ввести" id="addSalarySubmit" />
			</form>
		</div>
	</c:if>
	<footer>
		<div class="container-footer">
			<p class="text-muted">©УНЦ 2016 год.</p>
		</div>
	</footer>

</body>

<script src="js/actions.js"></script>
</html>
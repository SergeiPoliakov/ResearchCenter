<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page
	import="com.netcracker.unc.newmvc.ejb.controllers.ControllerCategories"%>
<%@page import="com.netcracker.unc.newmvc.ejb.entities.EntityUser"%>
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
<title>Менеджер расходов</title>
<link href="css/new-main.css" rel="stylesheet" />
</head>
<body>

	<c:set var="jsp" value="welcome" scope="request" />
	<jsp:include page="/load" />
	<c:if test="${page != 'page-ok'}">
		<c:redirect url="../" />
	</c:if>

	<c:if test="${bankrupt == 'true'}">
		<li id="bankrupt" />
	</c:if>


	<nav class="navbar navbar-default">
		<div class="container-fluid" style="height: 10%">
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<div class="navbar-header">
					<a class="navbar-brand" href="#"><img src="../img/logo.png"
						alt="Logo" width="214" height="59" id="Insert_logo"
						style="background-color: #92D36E;" /></a>
				</div>
				<div class="nav" align="right" style="color: #fef8a0"
					style="height :25px">
					<h5>
						Добро пожаловать <label><c:out
								value="${sessionScope.user.getLogin()}" /></label>
					</h5>
				</div>


				<ul class="nav navbar-nav">
					<li><div class="btn-group">
							<button class="btn" onclick="showHideModule(this)"
								id="consumption">Расходы</button>
							<button class="btn" onclick="showHideModule(this)"
								id="incomeMinus">-</button>
						</div></li>
					<li><div class="btn-group">
							<button class="btn" onclick="showHideModule(this)" id="incomes">Доходы</button>
							<button class="btn" onclick="showHideModule(this)"
								id="incomePlus">+</button>
						</div></li>
					<li>
						<div class="btn-group">
							<button class="btn" onclick="showHideModule(this)" id="statistic">Статистика</button>
						</div>
					</li>
					<li>
						<div class="btn-group">
							<button class="btn" id="resource" onclick="showHideModule(this)">Ресурсы</button>
						</div>
					</li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li style="padding-right: 50px">
						<div class="btn-group">
							<a id="red-btn" onclick="resetBalance()"></a>
						</div>
					</li>
					<li>
						<div class="btn-group" style="padding-right: 0px">
							<button class="btn" id="personalArea"
								onclick="showHideModule(this)">Личный кабинет</button>
						</div>
					</li>
					<li>
						<div class="btn-group">
							<form action="../auth" method="get">
								<input type="hidden" name="authorization" value="logOut" />
								<button type="submit" class="btn">Выход</button>
							</form>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container-fluid main-container">
		<div id="leftMenuDiv" style="display: none;">
			<jsp:include page="vis/left_menu.jsp" />
		</div>

		<div id="resetBalanceDiv" style="display: none"
			class="generalModule reset-div">
			<jsp:include page="act/reset_balance.jsp" />
		</div>

		<div class="module generalModule" id="createCase"
			style="display: none">
			<div class="block-title">Создание новой задачи</div>
			<div class="block-information">
				<div class="create-case" id="create-case">
					<jsp:include page="act/create_case.jsp" />
				</div>
			</div>
		</div>

		<div class="module generalModule" id="editCase" style="display: none">
			<div class="block-title">Редактирование задач</div>
			<div class="block-information">
				<div id="updateCase">
					<jsp:include page="act/update_case.jsp" />
				</div>
			</div>
		</div>

		<div class="module generalModule" id="category-module"
			style="display: none">
			<div class="block-title">Категории</div>
			<div class="block-information" id="bi-category-module">
				<jsp:include page="module-categories/module.jsp" />
			</div>
		</div>

		<div class="module generalModule" id="credit-module"
			style="display: none">
			<div class="block-title">Кредиты</div>
			<div class="block-information" id="credit-module"></div>
		</div>


		<div class="module generalModule" id="invoices" style="display: none">
			<div class="block-title">Управление счетами</div>
			<div class="block-information" id="bi-category-module">
				<jsp:include page="int/invoice.jsp" />
			</div>
		</div>

		<div class="module generalModule" id="incomeA" style="display: none">
			<div class="block-title">Управление доходами</div>
			<div class="block-information">
				<jsp:include page="int/income.jsp" />
			</div>
		</div>

		<div class="module generalModule" id="consumptionA"
			style="display: none;">
			<div class="block-title">Управление расходами</div>
			<div class="block-information">
				<jsp:include page="int/consumption.jsp" />
			</div>
		</div>


		

	<div class="module generalModule" id="incoming" style="display: none">
		<div class="block-title">Мгновенное увеличение баланса</div>
		<div class="block-information">
			<jsp:include page="int/incoming.jsp" />
		</div>
	</div>
	<div class="module generalModule" id="incomingMinus"
		style="display: none">
		<div class="block-title">Мгновенное списание денежных средств</div>
		<div class="block-information">
			<jsp:include page="int/incomingMinus.jsp" />
		</div>
	</div>

	<div class="module generalModule" id="editUser" style="display: none">
		<div class="block-title">Редактирование профиля</div>
		<div class="block-information">
			<div id="editUser">
				<jsp:include page="act/update_user.jsp"></jsp:include>
			</div>
		</div>
	</div>

	<div class="module generalModule" id="statPie" style="display: none">
		<div class="block-title">Статистика бюджета</div>
		<div class="block-information">
			<jsp:include page="vis/statistic.jsp"></jsp:include>
		</div>
	</div>
	<div class="module-right" id="income">
		<div class="overlayInCons">
			<jsp:include page="vis/income_consumption.jsp"></jsp:include>
		</div>
	</div>
	</div>

	<div class="controlSalary" id="controlSalaryMain">
		<jsp:include page="int/salary_control.jsp"></jsp:include>
	</div>
	<c:set var="checkSalaryBro" value="${checkSalary}" scope="page" />
	<c:if test="${checkSalaryBro == 'error'}">
		<div id="helloCase">
			<div style="margin-left: 5px;">
				<label class="welcomeCase" id="welcomeCase1"><span
					style="margin-left: 40px" /><b>Добро пожаловать на сайт <span
						style="margin-left: 20px">приоритетов!</span>
				</b></label>
				<p>
					<label class="welcomeCase" id="welcomeCase2">Пожалуйста,
						введите вашу зарплату:</label>
				</p>
				<form action="../cust" onsubmit="return regularAddSalary()">
					<input type="hidden" value="addSalary" name="custom" /> <input
						type="text" id="welcomeCaseInput" name="salary"
						onkeypress="validate(this)" /><label class="welcomeCase"
						style="margin-left: 3px">руб.</label>
					<button class="btn btn-reset" type="submit" id="addSalarySubmit">Ввести</button>
				</form>
			</div>
		</div>
	</c:if>
	<div class="container-footer">
		<p class="text-muted">©УНЦ 2016 год.</p>
	</div>
</body>

<script src="js/actions.js"></script>
</html>
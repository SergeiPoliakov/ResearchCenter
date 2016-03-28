<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">
<title>Сайт приоритетов</title>
<link href="css/new-main.css" rel="stylesheet">
</head>
<body>

	<c:set var="page" value="loading" scope="request" />
	<jsp:include page="/load" />
	<c:if test="${page != 'page-ok'}">
		<c:redirect url="../" />
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



	<footer>
		<div class="container-footer">
			<p class="text-muted">©УНЦ 2016 год.</p>
		</div>
	</footer>

</body>
</html>
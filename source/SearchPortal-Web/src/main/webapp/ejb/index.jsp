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
<link rel="shortcut icon" href="../../assets/ico/favicon.ico">
<link href="ejb/css/new-main.css" rel="stylesheet">
</head>

<body>

	<c:set var="page" value="loading" scope="request" />
	<jsp:include page="/load" />
	<c:if test="${page == 'page-ok'}">
		<c:redirect url="welcome.jsp" />
	</c:if>


	<div id="authorization" class="container">
		<div id="authorizationUser">
			<form class="form-signin" action="auth" method="post"
				onsubmit="return checkLogEmpty()">
				<input class="form-control" type="hidden" value="userLogin"
					name="authorization" />
				<h3 class="form-signin-heading">Пожалуйста, авторизуйтесь</h3>
				<input class="form-control login" type="text" name="login"
					width="15px" id="logLogin" placeholder="Логин" required /><label>${errorMessage}</label><input
					class="form-control password" type="password" name="password"
					width="15px" id="logPas" placeholder="Пароль" required />
				<button class="btn btn-lg btn-primary btn-block" name="submit"
					type="submit">Войти</button>
				<label onclick="changeToReg()"
					style="cursor: pointer; text-decoration: underline;">Зарегистрироваться!</label>
				<label onclick="changeToReg()"
					style="cursor: pointer; text-decoration: underline;">Забыли
					пароль?</label>
			</form>
		</div>
		<!-- Forget password -->
		<div id="restoreUser" style="display: none">
			<form class="form-signin" action="authorization" method="post"
				onsubmit="return checkEmptyRestoreInput()">
				<input class="form-control" type="hidden" name="authorization"
					value="restorePassword" />
				<h3 class="form-signin-heading">Восстановление пароля</h3>
				<input class="form-control login" type="text" name="login"
					id="restorePasLogin" placeholder="Логин" required /> <input
					class="form-control login" type="text" name="email"
					id="restorePasEmail" placeholder="E-mail" required />
				<button class="btn btn-lg btn-primary btn-block" name="submit"
					type="submit">Восстановить</button>
				<label onclick="changeToAuth()"
					style="cursor: pointer; text-decoration: underline;">Уже
					вспомнил!</label>
			</form>
		</div>
	</div>

	<div id="registrationUser" style="display: none">
		<form class="form-signin" action="auth" method="post"
			onsubmit="return checkRegistrRegular()">
			<input type="hidden" value="userRegistration" name="authorization" />
			<h3 class="form-signin-heading">Регистрация нового пользователя:</h3>
			<input type="text" class="form-control login" name="login" id="login"
				placeholder="Введите логин" required><input type="password"
				class="form-control password" name="password" id="password"
				placeholder="Введите пароль" required><input type="text"
				class="form-control name" name="name" id="name"
				placeholder="Введите имя"><input class="form-control name"
				type="text" name="email" id="email" placeholder="Введите e-mail"
				required>
			<button class="btn btn-lg btn-primary btn-block" name="submit"
				type="submit">Зарегистрироваться</button>
			<button name="submit" class="btn btn-lg btn-primary btn-block"
				type="submit" onclick="changeToAuth()">Назад</button>
		</form>
	</div>

</body>
<script src="ejb/js/index.js"></script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Регистрация нового пользователя</title>
<!-- Bootstrap core CSS -->
<link href="css/new-main.css" rel="stylesheet">
</head>
<body>

	<div id="authorization" class="container">
			<form class="form-signin" action="auth" method="post"
				onsubmit="return checkLogEmpty()">
				<input type="hidden" value="userLogin" name="authorization" />
				<table rules="groups" align="center" border="2px" width="50%"
					cellspacing="3" cellpadding="3" id="loginTable">
					<thead>
						<tr>
							<th align="center" colspan="2"><label class="title">Авторизуйтесь:</label></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><label class="info">Логин:</label></td>
							<td align="center"><input type="text" class="login"
								name="login" width="15px" id="logLogin" /></td>
						</tr>
						<tr>
							<td><label class="info">Пароль:</label></td>
							<td align="center"><input type="password" class="password"
								name="password" width="15px" id="logPas" /></td>
						</tr>
						<tr>
							<td style="padding-left: 60px"><input name="submit"
								type="submit" value="Ввести" class="submit" /></td>
							<td align="center"><label onclick="createAccount()"
								style="cursor: pointer; text-decoration: underline;">Зарегистрироваться!</label>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2"><label
								onclick="restorePassword()"
								style="cursor: pointer; text-decoration: underline;">Забыли
									пароль?</label></td>
						</tr>
						<tr>
							<td align="center" colspan="2"><label class="registr"
								style="color: red;" id="logErrorLabel"><c:if
										test="${not empty errorMessage}">
										<c:out value="${errorMessage}" />
									</c:if></label></td>
						</tr>
					</tbody>
				</table>
			</form>

			<form action="authorization" method="post"
				onsubmit="return checkEmptyRestoreInput()">
				<input type="hidden" name="authorization" value="restorePassword" />
				<table style="display: none;" width="50%" id="restoreTable"
					rules="groups" align="center" cellspacing="3" cellpadding="3"
					class="table">
					<thead align="center">
						<tr>
							<th colspan="2"><label class="title">Восстановление
									пароля:</label></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><label class="info">Введите логин:</label></td>
							<td><input type="text" class="login" name="login"
								id="restorePasLogin" /></td>
						</tr>
						<tr>
							<td><label class="info">Введите почту:</label></td>
							<td><input type="text" class="login" name="email"
								width="15px" id="restorePasEmail" /></td>
						</tr>
						<tr>
							<td style="padding-left: 80px"><input name="submit"
								type="submit" value="Ввести" class="submit" /></td>
							<td><label onclick="restorePassword()"
								style="cursor: pointer; text-decoration: underline;">Уже
									вспомнил!</label></td>
						</tr>
					</tbody>
				</table>
				<label style="color: red; display: none" id="errorRestorePasLasbel">Поля
					не могут быть пустыми!</label>
			</form>
		</center>
		<label id="restorePasswordLabel"> ${restorePassword}</label>
	</div>

</body>

<script type="text/javascript">
	function checkLogEmpty() {
		var login = document.getElementById("logLogin");
		var pas = document.getElementById("logPas");
		var errorLabel = document.getElementById('logErrorLabel');
		errorLabel.innerHTML = "";

		if (login.value.valueOf() == "".valueOf()
				|| pas.value.valueOf() == "".valueOf()) {
			errorLabel.innerHTML = 'Поля логин и пароль не могут быть пустыми';
			return false;
		}
		return true;
	}

	function restorePassword() {
		var errorLabel = document.getElementById("errorRestorePasLasbel");
		var restorePasswordLabel = document
				.getElementById('restorePasswordLabel');
		restorePasswordLabel.style.display = 'none';

		var loginTable = document.getElementById('loginTable');
		var restoreTable = document.getElementById('restoreTable');
		if (restoreTable.style.display.valueOf() == 'none'.valueOf()) {
			loginTable.style.display = 'none';
			restoreTable.style.display = '';
		} else {
			loginTable.style.display = '';
			restoreTable.style.display = 'none';
			errorLabel.style.display = 'none';
		}

	}

	function checkEmptyRestoreInput() {
		var login = document.getElementById("restorePasLogin");
		var email = document.getElementById("restorePasEmail");
		var errorLabel = document.getElementById("errorRestorePasLasbel");
		if (login.value.valueOf() == "".valueOf()
				|| email.value.valueOf() == "".valueOf()) {
			errorLabel.style.display = 'block';
			return false;
		} else
			return true;
	}
</script>

</html>
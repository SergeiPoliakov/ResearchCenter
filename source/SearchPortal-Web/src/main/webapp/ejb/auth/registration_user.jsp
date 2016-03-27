<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Регистрация нового пользователя</title>
</head>
<body>
	<form action="auth" method="post"
		onsubmit="return checkRegistrRegular()">
		<input type="hidden" value="userRegistration" name="authorization" />
		<table rules="groups" align="center" border="2px" cellspacing="3"
			cellpadding="3" width="50%">
			<thead>
				<tr>
					<th align="center" colspan="3"><label class="title">Регистрация
							нового пользователя:</label></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="column1">Логин:</td>
					<td><input type="text" class="login" name="login" id="login"></td>
					<td><c:choose>
							<c:when test="${not empty multiName}">
								<label id="errorRegLogLabel" style="display: block;">${multiName}</label>
							</c:when>
							<c:otherwise>
								<label id="errorRegLogLabel" style="display: none;"></label>
							</c:otherwise>
						</c:choose></td>
				</tr>
				<tr>
					<td class="column1">Пароль:</td>
					<td><input type="password" class="password" name="password"
						id="password"></td>
					<td><label class="errorEnt" id="errorRegPasLabel"></label></td>
				</tr>
				<tr>
					<td class="column1">Имя:</td>
					<td><input type="text" class="name" name="name" id="name"></td>
					<td><label class="errorEnt" id="errorRegNameLabel"></label></td>
				</tr>
				<tr>
					<td class="column1">Почта:</td>
					<td><input type="text" class="name" name="email" id="email"></td>
					<td><label class="errorEnt" id="errorRegEmailLabel"></label></td>
				</tr>
				<tr>
					<td><input type="submit" class="submit" name="button"
						value="Ввести" /></td>
					<td colspan="2" align="center"><input type="button"
						name="button" class="submit" value="Назад"
						onclick="createAccount()" /></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>

<script>
	function checkRegistrRegular() {
		var loginReg = /^[A-Za-z0-9]{1,15}$/;
		var passwordReg = /^[^ ]{4,10}$/;
		var emailReg = /^[-a-z0-9~!$%^&*_=+}{\'?]+(\.[-a-z0-9~!$%^&*_=+}{\'?]+)*@([a-z0-9_][-a-z0-9_]*(\.[-a-z0-9_]+)*\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(:[0-9]{1,5})?$/i;
		var nameReg = new RegExp("^[^ ][A-Za-zА-Яа-я' ]{1,15}[^ ]$");
		var login = document.getElementById("login");
		var password = document.getElementById("password");
		var name = document.getElementById("name");
		var email = document.getElementById("email");
		var errorLogLabel = document.getElementById('errorRegLogLabel');
		var errorPasLabel = document.getElementById('errorRegPasLabel');
		var errorNameLabel = document.getElementById('errorRegNameLabel');
		var errorRegEmailLabel = document.getElementById("errorRegEmailLabel");
		var check = true;
		errorLogLabel.innerHTML = "";
		errorLogLabel.style.display = 'none';
		errorPasLabel.innerHTML = "";
		errorPasLabel.style.display = 'none';
		errorNameLabel.innerHTML = "";
		errorNameLabel.style.display = 'none';
		errorRegEmailLabel.innerHTML = "";
		errorRegEmailLabel.style.display = 'none';
		if (!login.value.match(loginReg)) {
			errorLogLabel.innerHTML = 'Логин должен состоять только из латинских букв и цифр (не более 15 символов без пробелов)';
			errorLogLabel.style.display = 'block';
			check = false;
		}
		if (!password.value.match(passwordReg)) {
			errorPasLabel.innerHTML = 'Пароль должен состоять только из латинских букв, цифр и символов (от 4 до 10)';
			errorPasLabel.style.display = 'block';
			check = false;
		}
		if (name.value.valueOf() != "".valueOf()) {
			if (!name.value.match(nameReg)) {
				errorNameLabel.innerHTML = 'Имя должно состоять только из букв (не более 15 символов)';
				errorNameLabel.style.display = 'block';
				check = false
			}
		}
		if (!email.value.match(emailReg)) {
			errorRegEmailLabel.innerHTML = 'Введите корректную почту';
			errorRegEmailLabel.style.display = 'block';
			check = false;
		}
		return check;
	}
</script>

</html>
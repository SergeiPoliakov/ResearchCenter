<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Регистрация нового пользователя</title>
<style type="text/css">
.errorEnt {
	font-size: 8pt;
	color: red;
}

</style>
</head>
<body>
	<form action="RegistrationUser" method="get">
		<table rules="groups" align="center" border="2px" cellspacing="3" cellpadding="3" width="50%">
			<thead>
				<tr>
					<th align="center" colspan="3"><label class="title">Регистрация нового пользователя:</label></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="column1">Логин:</td>
					<td><input type="text" class="login" name="login"></td>
					<td><c:choose>
							<c:when test="${not empty incorrectLogin}">
								<label class="errorEnt">${incorrectLogin}</label>
							</c:when>
							<c:when test="${not empty multiName}">
								<label class="errorEnt">${multiName}</label>
							</c:when>
						</c:choose></td>
				</tr>
				<tr>
					<td class="column1">Пароль:</td>
					<td><input type="password" class="password" name="password">
					</td>
					<td><c:if test="${not empty incorrectPassword}">
							<label class="errorEnt">${incorrectPassword}</label>
						</c:if></td>
				</tr>
				<tr>
					<td class="column1">Имя:</td>
					<td><input type="text" class="name" name="name"></td>
					<td><c:if test="${not empty incorrectName}">
							<label class="errorEnt">${incorrectName}</label>
						</c:if></td>
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

</html>
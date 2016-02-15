<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Регистрация нового пользователя</title>
<style type="text/css">
.login {
	color: black
}

.password {
	color: black
}

.registr {
	font-size: 10pt;
	cursor: pointer
}
</style>
<link type="text/css" rel="stylesheet"
	href="authorization/css/authorization.css" />
</head>
<body>

	<div id="authorization">
		<center>
			<form action="LoginUser" method="get">
				<table rules="groups" align="center" border="2px" width="50%" cellspacing="3" cellpadding="3">
					<thead>
						<tr>
							<th align="center" colspan="2"><label class="title">Авторизуйтесь:</label></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><label class="info">Логин:</label></td>
							<td align="center"><input type="text" class="login"
								name="login" width="15px" /></td>
						</tr>
						<tr>
							<td><label class="info">Пароль:</label></td>
							<td align="center"><input type="password" class="password"
								name="password" width="15px" /></td>
						</tr>
						<tr>
							<td style="padding-left: 60px"><input name="submit"
								type="submit" value="Ввести" class="submit" /></td>
							<td align="center"><label onclick="createAccount()"
								style="cursor: pointer; text-decoration: underline;">Зарегистрироваться!</label>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2"><c:if
									test="${not empty errorMessage}">
									<label class="registr" style="color: red;"> <c:out
											value="${errorMessage}" />
									</label>
								</c:if></td>
						</tr>
					</tbody>
				</table>
			</form>
		</center>
	</div>

</body>

</html>
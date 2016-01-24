<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration User Page</title>
<style type="text/css">
	table {	width: auto;
    		height: auto; }
    td {    padding: 15px;
    		padding-right: 10px; 
    		width: auto;}	
    .errorEnt {font-size: 8pt;
    			color: red; }
</style>
</head>
<body>
	<form action="RegistrationUserServlet" method="get" >
		<table rules="none">
			<tr>
				<td>Введите логин:</td>
				<td>
					<input type="text" class="login" name="login">
				</td>
				<td>
					<c:choose>
						<c:when test="${not empty incorrectLogin}">
							<label class="errorEnt">${incorrectLogin}</label>
						</c:when>
						<c:when test="${not empty multiName}">
							<label class="errorEnt">${multiName}</label>
						</c:when>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>Введите пароль:</td>
				<td>
					<input type="password" class="password" name="password">
				</td>
				<td>
					<c:if test="${not empty incorrectPassword}">
						<label class="errorEnt">${incorrectPassword}</label>
					</c:if>
				</td>
			</tr>
				<tr>
				<td>Введите имя:</td>
				<td>
					<input type="text" class="name" name="name">
				</td>
				<td>
					<c:if test="${not empty incorrectName}">
						<label class="errorEnt">${incorrectName}</label>
					</c:if>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" name="button" value="Зарегистрироваться"/>
				</td>
				<td>
					<input type="submit" name="button" value="Назад"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
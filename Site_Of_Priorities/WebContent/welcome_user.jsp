<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome user page</title>
<style type="text/css">
	table {	width: auto;
    		height: auto; 
    		border: none }
    td {    padding: 15px;
    		padding-right: 10px; 
    		width: auto}	
    .thx {font-size: 10pt;
    		height: 20pt}
</style>
</head>
<body>
	<c:if test="${not empty welcomeNewUser}">
		<label class="thx">Благодарим за регистрацию !</label>
	</c:if>
	<form action="authorization_user.jsp">
		<table  border="2" cellpadding="3" rules="none">
			<tr>
				<td>Добро пожаловать </td>
				<td>
					<c:out value="${loginComplete}"></c:out>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="Выйти">
				</td>
			</tr>
		</table>
	</form>
	<c:redirect url="authorization_user.jsp"></c:redirect>
</body>
</html>
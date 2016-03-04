<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="updateUser">
		<form action="UpdateUser">
			<table cellspacing="10px">
				<thead>
					<tr>
						<th>Редактировать свой профиль:</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Смена имени:</td>
						<td><input type="text" name="name" /></td>
					</tr>
					<tr>
						<td>Смена пароля:</td>
						<td><input type="password" name="oldPas" /></td>
						<td><input type="password" name="newPas" /></td>
						<td><c:if test="${not empty errorPassword}">
							${errorPassword}
						</c:if></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td><input type="submit" value="Изменить" /></td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>




</body>
</html>
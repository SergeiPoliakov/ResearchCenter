<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Authorization user</title>
<style type="text/css">
	.login { color: black}
	.password { color: black}
	.registr {font-size: 10pt; cursor: pointer}
</style>
</head>
<body>


	<h1 align="center">Тут типа какой то будет сайт</h1>


	<div id="authorization">
			<center>
			
				<form action="LoginUserServlet" method="get">
					<table border="2" width="20%" cellpadding="3" rules="groups">
						<thead>
							<tr>
								<th align="center" colspan="2">Авторизуйтесь:</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>логин:</td>
								<td>
									<input type="text" class="login" name="login" width="15px"/>
								</td>
							</tr>
							<tr>
								<td>пароль:</td>
								<td>
									<input type="password"  class="password" name="password" width="15px"/>
								</td>
							</tr>
							<tr align="center">
								<td> 
									<input name="submit" type="submit" value = "Ввести" />
								</td>
								<td>
									<a href="registration_user.jsp"><label class="registr">Зарегистрироваться</label></a>
								</td>
							</tr>
							<tr>
								<td align="center" colspan="2"> 
									<c:if test="${not empty errorMessage}">
										<c:out value="Неправильно введен логин или пароль!"/>
									</c:if>
							    </td>
							</tr>
						</tbody>
					</table>
				</form>
			</center>
	</div>
	
</body>
</html>
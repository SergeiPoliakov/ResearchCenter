<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Authorization user</title>
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
				<table border="2" width="60%" height="300px" cellpadding="3"
					rules="groups" class="table" cellspacing="10">
					<thead>
						<tr>
							<th align="center" colspan="2"><label class="title">Please
									Log In:</label></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><label class="info">Login:</label></td>
							<td align="center"><input type="text" class="login"
								name="login" width="15px" /></td>
						</tr>
						<tr>
							<td><label class="info">Password:</label></td>
							<td align="center"><input type="password" class="password"
								name="password" width="15px" /></td>
						</tr>
						<tr>
							<td style="padding-left: 60px"><input name="submit" type="submit" value="Submit" class="submit"/></td>
							<td align="center"><a
								href="<c:url value="/authorization/registration_user.jsp"/>"><label
									class="registr">Create a new account !</label></a></td>
						</tr>
						<tr>
							<td align="center" colspan="2"><c:if
									test="${not empty errorMessage}"><label class="registr">
									<c:out value="Incorrect login or password! Please try again!" />
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
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

	<div id="authorization">
			<center>
				<form action="LoginUserServlet" method="get">
					<table border="2" width="20%" cellpadding="3" rules="groups">
						<thead>
							<tr>
								<th align="center" colspan="2">Please Log In:</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>login:</td>
								<td>
									<input type="text" class="login" name="login" width="15px"/>
								</td>
							</tr>
							<tr>
								<td>password:</td>
								<td>
									<input type="password"  class="password" name="password" width="15px"/>
								</td>
							</tr>
							<tr align="center">
								<td> 
									<input name="submit" type="submit" value = "Submit" />
								</td>
								<td>
									<a href="registration_user.jsp"><label class="registr">Create new account</label></a>
								</td>
							</tr>
							<tr>
								<td align="center" colspan="2"> 
									<c:if test="${not empty errorMessage}">
										<c:out value="Incorrect login or password! Please try again!"/>
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
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="css/index.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Добро пожаловать на сайт приоритетов</title>
</head>
<body>

	<%@ page
		import="com.netcracker.unc.mvc.dao.UserDAO, com.netcracker.unc.mvc.models.UserModel"%>
	<%!private UserModel user = new UserModel();
	private UserDAO userDAO = new UserDAO(); //for save user in session%>

	<!-- for change authorization and registration -->
	<c:set var="check" value="false" />
	<!-- for save session when reload -->
	<c:set var="loginComplete" value="false" />

	<c:forEach var="userCookie" items="${pageContext.request.cookies}">
		<c:choose>
			<c:when test="${userCookie.name == 'userID'}">
				<!-- check user session -->
				<c:choose>
					<c:when test="${not empty sessionScope.user}">
						<!-- set attribute for other jsp pages -->
						<c:redirect url="modules.jsp"></c:redirect>
						<c:set var="user" value="${sessionScope.user}" scope="session" />
					</c:when>
					<c:otherwise>
						<c:set var="id" value="${userCookie.value}" scope="request" />
						<%
							user.set_user_id(Integer.valueOf((String) request.getAttribute("id")));
												user = (UserModel) userDAO.getObject(user);
												request.getSession().setAttribute("user", user);
						%>
						<c:redirect url="modules.jsp"></c:redirect>
					</c:otherwise>
				</c:choose>
				<!-- that user already login -->
			</c:when>
			<c:when test="${userCookie.name == 'page'}">
				<c:if test="${userCookie.value == 'first'}">
					<c:set var="check" value="true" />
				</c:if>
			</c:when>
		</c:choose>
	</c:forEach>

	<div class="main">

		<div class="header"></div>

		<div class="module" id="select-module1" align="center">
			<div class="label" align="center">
				<label class="welcomeText"> Добро пожаловать на сайт
					приоритетов!</label> <br>
			</div>
			<c:choose>
				<c:when test="${check ne true}">
					<div class="authInclude" id="authInclude"
						style="visibility: visible">
						<jsp:include page="/authorization/authorization_user.jsp" />
					</div>
					<div class="registrInclude" id="registrInclude"
						style="visibility: hidden">
						<jsp:include page="/authorization/registration_user.jsp" />
					</div>
				</c:when>
				<c:otherwise>
					<div class="authInclude" id="authInclude"
						style="visibility: hidden">
						<jsp:include page="/authorization/authorization_user.jsp"/>
					</div>
					<div class="registrInclude" id="registrInclude"
						style="visibility: visible">
						<jsp:include page="/authorization/registration_user.jsp" />
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

</body>
<script type="text/javascript">
	document.cookie = 'page=second'; // for
</script>
<script type="text/javascript">
	function createAccount() {
		var auth = document.getElementById("authInclude");
		var registr = document.getElementById("registrInclude");
		if (auth.style.visibility.valueOf() == 'visible'.valueOf()) {
			auth.style.visibility = 'hidden';
			registr.style.visibility = 'visible';
		} else {
			if (window.location.pathname.valueOf() != '/SearchPortal-Web/'
					.valueOf()) {
				window.location.replace(/SearchPortal-Web/);
			}
			document.cookie = 'page=second'; // for check open registration window
			auth.style.visibility = 'visible';
			registr.style.visibility = 'hidden';
		}
	}
</script>
</html>

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

	<jsp:useBean id="userController"
		class="com.netcracker.unc.newmvc.dao.UserDAO" />
	<jsp:useBean id="user"
		class="com.netcracker.unc.newmvc.dao.models.UserModel" />

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
					</c:when>
					<c:otherwise>
						<c:set var="checkCookie" value="ok" scope="request" />
						<jsp:include page="/authorization" />
						<c:choose>
							<c:when test="${sessionScope.user.getUserId() != 0}">
								<c:redirect url="welcome.jsp"></c:redirect>
							</c:when>
							<c:otherwise>
								<script type="text/javascript">
									document.cookie = 'userID'
											+ '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
								</script>
							</c:otherwise>
						</c:choose>
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
						<jsp:include page="/authorization/authorization_user.jsp" />
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
		var restorePasswordLabel = document
				.getElementById('restorePasswordLabel');
		restorePasswordLabel.style.display = 'none';

		var errorLogLabel = document.getElementById('errorRegLogLabel');
		var errorPasLabel = document.getElementById('errorRegPasLabel');
		var errorNameLabel = document.getElementById('errorRegNameLabel');
		var errorEmailLabel = document.getElementById('errorRegEmailLabel');
		var logErrorLabel = document.getElementById('logErrorLabel');
		var logLogin = document.getElementById("logLogin");
		var logPas = document.getElementById("logPas");
		var login = document.getElementById("login");
		var password = document.getElementById("password");
		var name = document.getElementById("name");
		var email = document.getElementById("email");

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

			errorLogLabel.innerHTML = "";
			errorLogLabel.style.display = 'none';
			errorPasLabel.innerHTML = "";
			errorPasLabel.style.display = 'none';
			errorNameLabel.innerHTML = "";
			errorNameLabel.style.display = 'none';
			errorEmailLabel.innerHTML = "";
			errorEmailLabel.style.display = 'none';
			login.value = '';
			password.value = '';
			name.value = '';
			logErrorLabel.innerHTML = '';
			logLogin.value = '';
			logPas.value = '';
			email.value = '';
		}
	}
</script>
</html>

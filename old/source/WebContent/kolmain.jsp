<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link type="text/css" rel="stylesheet" href="kolmain.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>

	<%@ page
		import="com.unc2016.mvc.dao.UserDAO, com.unc2016.mvc.models.UserModel"%>
	<%!private UserModel user = new UserModel();
	private UserDAO userDAO = new UserDAO(); //for save user in session%>


	<c:set var="check" value="false" />

	<!-- check user session -->

	<!--  
			<c:forEach var="userCookie" items="${pageContext.request.cookies}">
				<c:choose>
					<c:when test="${userCookie.name == 'userID'}">
						<c:if test="${userCookie.value == userSession.get_user_id()}">
						
						///////////////////////
						input code here 
						//////////////////////
						       
						<label>${userSession.get_user_id()}</label>
						</c:if>
					</c:when>
					<c:otherwise>
						////////////
					</c:otherwise>
				</c:choose>
			</c:forEach>
		//////////////// SESSION SAVE ///////////   -->

	<c:forEach var="userCookie" items="${pageContext.request.cookies}">
		<c:choose>
			<c:when test="${userCookie.name == 'userID'}">
				<!-- check user session -->
				<c:choose>
					<c:when test="${not empty sessionScope.user}">
						<c:set var="check" value="true" />
						<c:set var="user" value="${sessionScope.user}" scope="session" />   <!-- set attribute for other jsp pages -->
					</c:when>
					<c:otherwise>
						<c:set var="id" value="${userCookie.value}" scope="request" />
						<%
							user.set_user_id(Integer.valueOf((String) request.getAttribute("id")));
												user = (UserModel) userDAO.getObject(user);
												request.getSession().setAttribute("user", user);
						%>
					</c:otherwise>
				</c:choose>
				<c:set var="check" value="true" />  <!-- that user already login -->
			</c:when>
		</c:choose>
	</c:forEach>



	<div class="main" align="center">


		<div class="top">
			<c:if test="${check eq true}">
				<div class="divLogin" align="right">
					<label class="loginMain"> <jsp:include
							page="/authorization/welcome_user.jsp" />
					</label>
				</div>
			</c:if>
		</div>

		<c:if test="${check eq false}">
			<div class="welcome" align="center">
				<div class="label">
					<label class="welcomeText"> Dear User! Welcome to Site Of
						Priorities !</label>
					<hr class="delimiter" />
				</div>
				<DIV class="authInclude">
					<jsp:include page="/authorization/authorization_user.jsp"></jsp:include>
				</DIV>
			</div>
		</c:if>



	</div>
</body>
</html>
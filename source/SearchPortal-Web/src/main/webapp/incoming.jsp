<%@page import="com.netcracker.unc.mvc.dao.CategoryDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.netcracker.unc.priorityModule.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modules</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script src="javascripts/jquery-2.2.0.min.js"></script>
<script src="javascripts/mainModules.js"></script>
<link href="attitudes/css/attitude.css" rel="stylesheet" type="text/css" />
<link href="css/aspro.css" rel="stylesheet" type="text/css" />
<title>Добро пожаловать на сайт приоритетов</title>
</head>
<body>
	<%@ page
		import="com.netcracker.unc.mvc.dao.UserDAO, com.netcracker.unc.mvc.models.UserModel"%>
	<%!private UserModel user = null;%>

	<%
		response.setContentType("text/html;charset=Windows-1251");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
	%>

	<c:choose>
		<c:when test="${not empty sessionScope.user }">
			<%
				if (request.getSession().getAttribute("user") != null) {
							user = (UserModel) request.getSession().getAttribute("user");
						}
			%>
		</c:when>
		<c:otherwise>
			<c:redirect url="index.jsp" />
		</c:otherwise>
	</c:choose>
	<div class="header">

		<div class="welcome" align="right" style="background-color: #92d36e;"
			id="welcomeUser">
			Добро пожаловать <label
				style="color: red; font-size: 16pt; background-color: #92d36e; margin-right: 10px"><%=user.get_login()%></label>
			<form action="LogoutUser">
				<input type="submit" value="Выход" id="logOutSubmit" />
			</form>
  
		</div>
		<button id="cost-menu-button" class="button">Расходы</button>
		<button id="statistic-menu-button" class="button">Статистика</button>
		<button id="statistic-menu-button" class="button" onclick="showCreateCase()">Создать</button>
		<button id="add-sum-button" class="button">+</button>
		<div class="overlayInCons">
			<jsp:include page="/attitudes/IncomeConsumption"></jsp:include>
		</div>
	</div>
		<div class="module" id="add-sum">
		<div class="block-title">Мгновенное попонение счёта</div>
		<div class="block-information">
		    <div>Текущий баланс: <label><%=user.get_account_type()%></label></div>
		    <p>Укажите вносимую сумму (кратную 50-ти)</p>
		    <form action="incoming.jsp" method="get">
		      <input type="number" name="add-sum-val" value="0" min="0" size="5" step="50"/>
		      <input type="submit" value="добавить" />
		    </form>
            <%String addSumVal;
            if ((request.getParameter("add-sum-val") != null) && (!request.getParameter("add-sum-val").trim().isEmpty())) {
            	addSumVal = user.get_account_type()+(String)request.getParameter("add-sum-val");
            	user.set_account_type(addSumVal);
            	UserDAO userdao = new UserDAO();
            	userdao.updateObject(user);
            	
    				}
    		%>
    		<p>Обновленный баланс: <lable><%=user.get_account_type()%></lable></p>
		</div>
	</div>
</body>

<script src="javascripts/aspro.js"></script>
<script src="interface/css/create_case.js"></script>

</html>
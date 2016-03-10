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
<script src="javascripts/Chart.js"></script>
<link href="attitudes/css/attitude.css" rel="stylesheet" type="text/css" />
<link href="css/aspro.css" rel="stylesheet" type="text/css" />
<title>Добро пожаловать на сайт приоритетов</title>
</head>
<body>
	<%@ page
		import="com.netcracker.unc.mvc.dao.UserDAO, com.netcracker.unc.mvc.models.UserModel"
		import="com.netcracker.unc.mvc.dao.InvoiceDAO, com.netcracker.unc.mvc.models.InvoiceModel"%>
	<%!private UserModel user = null;%>
	<%!private InvoiceModel invoice = new InvoiceModel(); 
	   InvoiceDAO invoicedao = new InvoiceDAO();
	  //UserDAO userdao = new UserDAO();%>
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
							invoice = (InvoiceModel)invoicedao.getInvoiceById(1, user);
							
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
		<div class="module" id="diagram">
		<div class="block-title">Статистика</div>
		<div class="block-information">
		    <div>Текущий баланс: <label><%=invoicedao.getSumBalance()%></label></div>
		    <div>Зарезервированно средств: <label><%=invoicedao.getConsumptionSum()%></label></div>
		    <div>Свободные средства: <label><%=invoicedao.getSumBalance()-invoicedao.getConsumptionSum()%></label></div>
		</div>
		<script type="text/javascript">
		var pieData = [
						{
							value: invoicedao.getConsumptionSum(),
							color:"#F7464A",
							highlight: "#FF5A5E",
							label: "Зарезервировано"
						},
						{
							value: invoicedao.getSumBalance()-invoicedao.getConsumptionSum(),
							color: "#46BFBD",
							highlight: "#5A D3D1",
							label: "Свободно"
						},
						
					];
					window.onload = function(){
						var ctx = document.getElementById("chart-area").getContext("2d");
						window.myPie = new Chart(ctx).Pie(pieData);
					};
		</script>
	</div>
</body>

<script src="javascripts/aspro.js"></script>
<script src="interface/css/create_case.js"></script>

</html>

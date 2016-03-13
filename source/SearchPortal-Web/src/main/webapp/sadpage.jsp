<%@page import="com.netcracker.unc.priorityModule.CalculationPriority"%>
<%@page import="com.netcracker.unc.priorityModule.FillHTMLTable"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.netcracker.unc.newmvc.dao.CategoryModel"%>
<%@page import="com.netcracker.unc.newmvc.dao.CategoryController"%>
<%@page import="com.netcracker.unc.newmvc.dao.UserModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sad page</title>
<?xml version="1.0" encoding="UTF-8" ?>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script src="javascripts/jquery-2.2.0.min.js"></script>
<script src="javascripts/mainModules.js"></script>
<script src="javascripts/googleApiCharts.js"></script>
<script src="javascripts/priorityHistogram.js"></script>
</head>
<body>
	<div class="header">
		<div id="logo">214*59</div>
		<button id="cost-menu-button" class="button">Расходы</button>
		<button id="statistic-menu-button" class="button">Статистика</button>
	</div>
	<div id="top_x_div" style="width: 900px; height: 500px;"></div>
	<div class="module" id="priority-module">
		<div class="block-title">Приоритеты</div>
		<div class="block-information">
			<%
				UserModel user = (UserModel) request.getSession().getAttribute("user");
				CategoryController categoryController = new CategoryController(user);
			%>
			<%=FillHTMLTable.toHtmlString(categoryController.getCategoriesWithPriorities())%>
			<%=user.getUserId()%>
		</div>
	</div>
</body>
</html>

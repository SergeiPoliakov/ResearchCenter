<%-- 
    Document   : sadpage
    Created on : 16.02.2016, 16:39:31
    Author     : Dmitry

    For tests.
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.netcracker.unc.mvc.dao.CategoryDao"%>
<%@page import="com.netcracker.unc.mvc.models.CategoryModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sad page</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script src="javascripts/jquery-2.2.0.min.js"></script>
<script src="javascripts/mainModules.js"></script>
</head>
<body>
	<div class="header">
		<div id="logo">214*59</div>
		<button id="cost-menu-button" class="button">Расходы</button>
		<button id="statistic-menu-button" class="button">Статистика</button>
	</div>
	<div class="module" id="priority-module">
		<div class="block-title">Приоритеты</div>
		<div class="block-information">
			<%
				CategoryDao cd = new CategoryDao();
			%>
			<%=cd.testdb()%>
		</div>
	</div>
</body>
</html>

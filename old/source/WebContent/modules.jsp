<?xml version="1.0" encoding="UTF-8" ?>

<%@page import="com.unc2016.forDataBase.htmlHelper"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">


<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Modules</title>
	<link rel="stylesheet" href="main.css"/>
</head>
<body>
<% response.setContentType("text/html;charset=Windows-1251"); %>

<!-- header который скорее свего будет перенесен в самостоятельную jsp страницу -->
	<div class="header">
		<div class="logo">214*59</div>
		<form action="#">
			<input type="submit" class="menu-button" value="Расходы"/>
		</form>
		<form action="#">
			<input type="submit" class="fast-button" value="+" /><!-- идентифицировать -->
		</form>
		<form action="#">
			<input type="submit" class="menu-button" value="Доходы"/>
		</form>
		<form action="#">
			<input type="submit" class="fast-button" value="+" /><!-- идентифицировать -->
		</form>
		<form action="#">
			<input type="submit" class="menu-button" value="Статистика"/>
		</form>
	</div>
<!-- header закончился -->	
	<div class="module">
		<div class="block-title">
			<%
				String headstring="Просто селект";
				out.println(headstring);
			%>
		</div>
		<div class="block-information">
			<form action="modules.jsp" method="get">
				<input type="text" name="testquery" />
				<input type="submit" value="UP" />
			</form>
			<%if((request.getParameter("testquery") != null)&&
				(!request.getParameter("testquery").trim().isEmpty())) {%>
			<%=request.getParameter("testquery") %>
			<%
		}%>
		</div>
	</div>
	
	<div class="module"> 
		<div class ="block-title">Запрашивай</div>
		<div class="block-information">
			<%request.setAttribute("select", request.getParameter("testquery")); %>
			<form action="generateTable" method="get">
				<input type="hidden" name="select"
					 value="<%= request.getParameter("testquery") %>"/>
        		<input type="submit" value="UP"/>
    		</form>
			<%= request.getAttribute("table") %>
		</div>
	</div>
	<div class="module">
		<div class="block-title">Приоритеты</div>
		<div class="block-information">
		</div>
	</div>
</body>
</html>
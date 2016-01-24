<?xml version="1.0" encoding="UTF-8" ?>

<%@page import="java.sql.*"%>
<%@page import="java.util.Locale"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Крутое название</title>
	<link rel="stylesheet" href="main.css"/>
</head>
<body>
	<h1>Hello world. Это h1 html</h1>
	<div class="module-site">
		<div class="block-title">
			<%
				String headstring="Просто селект";
				out.println(headstring);
			%>
		</div>
		<div class="block-information">
			<form action="hello.jsp" method="get">
		<input type="text" name="testquery"/>
		<input type="submit" value="UP" />
	</form>

<%if((request.getParameter("testquery") != null)&&
		(!request.getParameter("testquery").trim().isEmpty())) {%>
	<%=request.getParameter("testquery") %>
	<!--работа с бд --> 
	<%
	try{
		Locale.setDefault(Locale.ENGLISH);
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection(
				"jdbc:oracle:thin:@127.0.0.1:1521:XE",
				"sys as sysdba", "1234");
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM PJ_USERS");
		
		ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
		out.println(resultSetMetaData.getColumnCount());
		
		resultSet.next();
		out.println( resultSet.getString("login") );
		for(int i=0; i<42; i++)
			out.println("42");
		
	}
	catch(Exception e){
		out.println("<br/> Ошибка: <br/>");
		out.println(e.getMessage());
	}
	%>
	<!-- работа с бд закончена -->
	<%}else{
		String a = "Всем привет!";
		out.println(a);
	}%>
	

		</div>
	
	</div>
</body>
</html>
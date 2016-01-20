<?xml version="1.0" encoding="UTF-8" ?>

<%@page import="javax.sql.DataSource"%>
<%@page import="java.sql.Connection" %>
<%@page import="com.test.jdbc.htmlHelper"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.naming.InitialContext"%>




<%@ page import = "java.sql.Connection,
java.sql.DriverManager,
java.sql.ResultSet,
java.sql.SQLException,
java.sql.Statement"
%>

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
		out.println("<br/> Тут прошлfffffffffо0");
		
		Context initContext = new InitialContext();
		//Context webContext = (Context)initContext.lookup("java:/comp/env");

		DataSource ds = (DataSource) initContext.lookup("TestDB");
		Connection dbCon = ds.getConnection();
		
		out.println("<br/> Тут прошло1");
		
		/*Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
		Connection connection = ds.getConnection();
		out.println("<br/> Тут прошло");
		
		
		/*Context context = new InitialContext();
		DataSource dataSource =
			(DataSource)context.lookup("java:comp/env/jdbc/TestDB");
		Connection conn = dataSource.getConnection();*/
		
	
		for(int i=0; i<42; i++)
			out.println("42");
		
	}
	catch(Exception e){
		out.println("<br/> Ошибка: <br/>");
		out.println(htmlHelper.printErr(e.getMessage()));
	}
	%>
	<!-- работа с бд закончена -->
	<%}else{
		String a = "Всем привет!";
		out.println(a);
	}%>
	
	
	<!--  -->
	<% 
	/*Connection connect = null; 
	Statement st = null;
	ResultSet result = null;
	
	try{

		Class.forName("oracle.jdbc.driver.OracleDriver");
		connect =
				DriverManager.getConnection(
						"jdbc:oracle:thin:@127.0.0.1:1521:XE",
						"sys as sysdba", "1234"); 
		out.println("<br/> Тут прошло01");
		
	}catch(Exception e){
		out.println("<br/> Ошибка: <br/>");
		out.println(htmlHelper.printErr(e.getMessage()));
	}
	*/
%>

	<!--  -->
		</div>
	
	</div>
</body>
</html>
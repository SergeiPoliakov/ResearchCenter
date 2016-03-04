<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
		table {	width: auto;
    			height: auto; 
    			text-align: center;}
   		 td {    padding: 15px;
    		padding-right: 10px; 
    		width: auto;}
    	caption {width: auto}
</style>
</head>
<body>
	<c:if test="${not empty noResults}">
		<label>${noResults}</label>
	</c:if>
	<form action="CreateOracleTable">
	<input type="text" name="select"/><input type="submit" value="Ввести"/></form>
	<table cellpadding="3" >
		<c:if test="${not empty createTableHeaders}">
				<caption>Oracle Database:</caption>${createTableHeaders}</c:if>
		<c:if test="${not empty viewResults}">${viewResults}</c:if>
	</table>
</body>
</html>
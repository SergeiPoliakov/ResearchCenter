<%@page import="com.netcracker.unc.newmvc.dao.models.UserModel"%>
<%@page import="com.netcracker.unc.priorityModule.FillHTMLTable"%>
<%@page import="com.netcracker.unc.newmvc.dao.controllers.CategoryController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<?xml version="1.0" encoding="UTF-8" ?>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
</head>
<body>
<%	CategoryController categoryController = new CategoryController((UserModel) request.getSession().getAttribute("user")); %>
			<%=FillHTMLTable.toHtmlString(categoryController.getCategoriesWithPriorities())%>
</body>
</html>
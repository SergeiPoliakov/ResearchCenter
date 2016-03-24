<%@page import="com.netcracker.unc.newmvc.dao.controllers.CategoryController"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.netcracker.unc.newmvc.dao.models.UserModel"%>
<%@page import="com.netcracker.unc.newmvc.dao.models.CategoryModel"%>
<%@page import="java.util.List"%>
<%@page import="com.netcracker.unc.htmltable.UserCategoryTable"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<?xml version="1.0" encoding="UTF-8" ?>
</head>
<body>
	<%
			UserModel user = (UserModel) request.getSession().getAttribute("user");
	CategoryController categoryController = new CategoryController((UserModel) request.getSession().getAttribute("user"));
				List<CategoryModel> categoryList = new ArrayList<CategoryModel>(categoryController.getCategories());
			%>
			<%=UserCategoryTable.toHtmlTable(categoryList)%>

			<!-- добавление категории -->
			<br /> <br />
			<h2>название, коэффициент, мин%, макс%</h2>
			<form action="CategoryServlet" method="get">
				<input type="hidden" name="action" value="add" /> <input
					type="hidden" name="objectid" value="0" /> <input type="text"
					name="categoryname" value="Name" /> <input type="text"
					name="coefficient" value="coef" /> <input type="text"
					name="minpercent" value="min" /> <input type="text"
					name="maxpercent" value="max" /> <input type="hidden"
					name="userid" value='<%=user.getUserId()%>' /> <input
					type="submit" value="Добавить категорию" />
			</form>
			<br /> <br />
			<!-- /добавление категории -->
</body>
</html>
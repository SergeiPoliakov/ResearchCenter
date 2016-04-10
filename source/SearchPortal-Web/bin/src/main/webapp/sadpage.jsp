<%@page import="com.netcracker.unc.htmltable.UserCategoryTable"%>
<%@page import="com.netcracker.unc.newmvc.dao.CategoryDAO"%>
<%@page import="com.netcracker.unc.newmvc.dao.models.ObjectModel"%>
<%@page import="com.netcracker.unc.priorityModule.CalculationPriority"%>
<%@page import="com.netcracker.unc.priorityModule.FillHTMLTable"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.netcracker.unc.newmvc.dao.models.CategoryModel"%>
<%@page
	import="com.netcracker.unc.newmvc.dao.controllers.CategoryController"%>
<%@page import="com.netcracker.unc.newmvc.dao.models.UserModel"%>
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
<script src="javascripts/updateCategoryRow.js"></script>
<script src="javascripts/addCategoryRow.js"></script>
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
				CategoryController categoryController = new CategoryController(
						(UserModel) request.getSession().getAttribute("user"));
			%>
			<%=FillHTMLTable.toHtmlString(categoryController.getCategoriesWithPriorities())%>
		</div>
	</div>
	<h1>t3</h1>
	<%
		String test = "testString";
	%>
	<div class="module" id="category-module">
		<div class="block-title">Категории</div>
		<div class="block-information">
			<%
				UserModel user = (UserModel) request.getSession().getAttribute("user");
				List<CategoryModel> categoryList = new ArrayList<CategoryModel>(categoryController.getCategories());
			%>
			<%=UserCategoryTable.toHtmlTable(categoryList)%>


			<!-- добавление категории --> <br /> <br />
			<h2>название, коэффициент, мин%, макс%</h2>
			<form action="CategoryServlet" method="get">
				<input type="hidden" name="action" value="add" /> <input
					type="hidden" name="objectid" value="0" /> <input type="text"
					name="categoryname" value="Name" /> <input type="text"
					name="coefficient" value="coef" /> <input type="text"
					name="minpercent" value="min" /> <input type="text"
					name="maxpercent" value="max" /> <input type="hidden"
					name="userid" value='<%=user.getUserId()%>' />
				<!--  -->
				<input type="submit" value="Добавить категорию" />
			</form>
			<br /> <br />
			<!-- /добавление категории -->

			<input type="button" id="start-add-categoty" value="Добавить" />
		</div>
	</div>
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<div id="top_x_div" style="width: 900px; height: 500px;"></div>

	<%=test%>
	<button id="start-new-row">Привет мир</button>
	<h1>t4</h1>
</body>
</html>

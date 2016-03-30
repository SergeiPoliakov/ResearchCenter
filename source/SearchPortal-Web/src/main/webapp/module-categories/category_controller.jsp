<%@page
	import="com.netcracker.unc.newmvc.dao.controllers.CategoryController"%>
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

<script src="javascripts/jquery-2.2.0.min.js"></script>
<script src="module-categories/js/view-update-row.js"></script>
<link rel="stylesheet" href="module-categories/css/cat-module.css">


<!--  -->
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<script>
	$(function() {
		$("#slider-range").slider({
			range : true,
			min : 0,
			max : 500,
			values : [ 75, 300 ],
			slide : function(event, ui) {
				$("#amount").val("$" + ui.values[0] + " - $" + ui.values[1]);
			}
		});
		$("#amount").val(
				"$" + $("#slider-range").slider("values", 0) + " - $"
						+ $("#slider-range").slider("values", 1));
	});
</script>
</head>
<body>
	<%
		UserModel user = (UserModel) request.getSession().getAttribute("user");
		CategoryController categoryController = new CategoryController(
				(UserModel) request.getSession().getAttribute("user"));
		List<CategoryModel> categoryList = new ArrayList<CategoryModel>(categoryController.getCategories());
	%>
	<%=UserCategoryTable.toHtmlTable(categoryList)%>

	<!-- добавление категории -->
	<br />
	<br />
	<h2>название, коэффициент, мин%, макс%</h2>
	<form action="CategoryServlet" method="get">
		<input type="hidden" name="action" value="add" /> <input
			type="hidden" name="objectid" value="0" /> <input type="text"
			name="categoryname" value="Name" /> <input type="text"
			name="coefficient" value="coef" /> <input type="text"
			name="minpercent" value="min" /> <input type="text"
			name="maxpercent" value="max" /> <input type="hidden" name="userid"
			value='<%=user.getUserId()%>' /> <input type="submit"
			value="Добавить категорию" />
	</form>
	<br />
	<br />
	<!-- /добавление категории -->
	<p>
		<label for="amount">Price range:</label> <input type="text"
			id="amount" readonly
			style="border: 0; color: #f6931f; font-weight: bold;">
	</p>

	<div id="slider-range"></div>

</body>
</html>
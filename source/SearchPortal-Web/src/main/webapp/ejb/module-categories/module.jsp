<%@page import="com.netcracker.unc.newmvc.ejb.models.UserModel"%>
<%@page import="com.netcracker.unc.ejb.htmltable.HtmlConverter"%>
<%@page import="com.netcracker.unc.newmvc.ejb.models.CategoryModel"%>
<%@page
	import="com.netcracker.unc.newmvc.ejb.controllers.ControllerCategories"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.netcracker.unc.newmvc.ejb.entities.EntityUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Categories module</title>
<?xml version="1.0" encoding="UTF-8" ?>

<script src="javascripts/jquery-2.2.0.min.js"></script>
<script src="module-categories/js/view-update-row.js"></script>
<script src="module-categories/js/percent-slider.js"></script>
<link rel="stylesheet" href="module-categories/css/cat-module.css">





<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
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
	<jsp:include page="/CategoriesEjbServlet" />
	<%
		List<CategoryModel> categoryList = new ArrayList<CategoryModel>();
		if (request.getSession().getAttribute("categoryList") != null) {
			categoryList = (List<CategoryModel>) request.getSession().getAttribute("categoryList");
			EntityUser user = (EntityUser) request.getSession().getAttribute("user");
	%>
	<%=HtmlConverter.toHtmlTableWithUpdateForms(CategoryModel.class, categoryList, user.getUserId())%>
	<%
		}
	%>
</body>
</html>
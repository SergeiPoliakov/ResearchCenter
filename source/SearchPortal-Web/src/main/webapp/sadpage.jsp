<%-- 
    Document   : sadpage
    Created on : 16.02.2016, 16:39:31
    Author     : Dmitry

    For tests.
--%>

<%@page import="com.netcracker.unc.priorityModule.FillHTMLTable"%>
<%@page import="com.netcracker.unc.priorityModule.CalculationPriority"%>
<%@page import="com.netcracker.unc.priorityModule.CategoryModelForTable"%>
<%@page import="java.util.List"%>
<%@page import="com.netcracker.unc.mvc.CategoryController"%>
<%@page import="com.netcracker.unc.mvc.models.UserModel"%>
<%@page import="com.netcracker.unc.mvc.ObjectController"%>
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
				/*
				Алгоритм:
					1.Передаю userDao в Controller, возвращаю сюда list categoryModel
					2.CategoryModule закидываю в Calkulation, получаю list categoryModelForTable
					3. categoryModuleForTable закидываю в StringHtml
					4. вывожу на экран stringHtml
				
				
				CalculationPriority calculationPriority = new CalculationPriority();
				FillHTMLTable fillHTMLTable = new FillHTMLTable();
				List userCategoryList = new ArrayList<CategoryModel>();
				List userCategoryListForTable = new ArrayList<CategoryModelForTable>();

				String htmlUserCategoriesTable = "";

				try {
					if ((UserModel) request.getSession().getAttribute("user") != null) {
						CategoryController categoryController = new CategoryController(
								(UserModel) request.getSession().getAttribute("user"));

						userCategoryList = categoryController.getAllUserCategories();
						if (userCategoryList != null && !userCategoryList.isEmpty()) {

							userCategoryListForTable = calculationPriority.convertToTableView(userCategoryList);

							if (userCategoryListForTable != null && !userCategoryListForTable.isEmpty()) {
								out.print(userCategoryListForTable.size());
								for (Object o : userCategoryListForTable) {*/
			%>
			 <%/*=o.toString()*/%>
			<%/*
				}
							} else {
								out.print("массив userCategoryListForTable пуст");
							}

						} else {
							out.print("массив userCategoryList пуст");
							if (userCategoryList != null) {
								out.print("все плохо, массив вообще null");
							}
						}

					}

					if (userCategoryListForTable != null && !userCategoryListForTable.isEmpty()) {
						htmlUserCategoriesTable = fillHTMLTable.toHTMLString(userCategoryListForTable);
			*/%>
			<%/*=htmlUserCategoriesTable*/%>
			<%/*
				}

				} catch (Exception e) {
					e.printStackTrace();
				}
				
				UserModel userModel = (UserModel) request.getSession().getAttribute("user");
				request.getSession().getAttribute("user");
				userModel.get_user_id();
			*/%>
			<%
				/*=cd.testdb()*/
			%>
		</div>
	</div>
</body>
</html>

<%@page import="com.netcracker.unc.priorityModule.CalculationPriority"%>
<%@page import="com.netcracker.unc.priorityModule.FillHTMLTable"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.netcracker.unc.newmvc.dao.CategoryModel"%>
<%@page import="com.netcracker.unc.newmvc.dao.CategoryController"%>
<%@page import="com.netcracker.unc.newmvc.dao.UserModel"%>
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

<!--  -->

<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	google.charts.load('current', {
		'packages' : [ 'bar' ]
	});
	google.charts.setOnLoadCallback(drawStuff);

	function drawStuff() {
		var data = new google.visualization.arrayToDataTable([
				[ 'Opening Move', 'Percentage' ], [ "King's pawn (e4)", 44 ],
				[ "Queen's pawn (d4)", 31 ], [ "Knight to King 3 (Nf3)", 12 ],
				[ "Queen's bishop pawn (c4)", 10 ], [ 'Other', 3 ] ]);

		var options = {
			title : 'Chess opening moves',
			width : 900,
			legend : {
				position : 'none'
			},
			chart : {
				title : 'Chess opening moves',
				subtitle : 'popularity by percentage'
			},
			bars : 'horizontal', // Required for Material Bar Charts.
			axes : {
				x : {
					0 : {
						side : 'top',
						label : 'Percentage'
					}
				// Top x-axis.
				}
			},
			bar : {
				groupWidth : "90%"
			}
		};

		var chart = new google.charts.Bar(document.getElementById('top_x_div'));
		chart.draw(data, options);
	};
</script>

<!--  -->

</head>
<body>
	<div class="header">
		<div id="logo">214*59</div>
		<button id="cost-menu-button" class="button">Расходы</button>
		<button id="statistic-menu-button" class="button">Статистика</button>
	</div>
	<div id="top_x_div" style="width: 900px; height: 500px;"></div>
	<div class="module" id="priority-module">
		<div class="block-title">Приоритеты</div>
		<div class="block-information">
			<%
				UserModel user = (UserModel) request.getSession().getAttribute("user");
				CategoryController categoryController = new CategoryController(user);
			%>
			<%=FillHTMLTable.toHtmlString(categoryController.getCategoriesWithPriorities())%>
			<%=user.getUserId()%>
		</div>
	</div>
</body>
</html>

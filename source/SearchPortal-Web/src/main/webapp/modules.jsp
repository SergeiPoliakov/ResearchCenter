<%-- 
    Document   : Modules
    Created on : 07.02.2016, 19:04:11
    Author     : Dmitry
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modules</title>
        <link href="css/main.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%
		response.setContentType("text/html;charset=Windows-1251");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
	%>
        <!-- header который скорее свего будет перенесен в самостоятельную jsp страницу -->
        <div class="header">
            <div class="logo">214*59</div>
            <form action="#">
                <input type="submit" class="menu-button" value="Расходы" />
            </form>
            <form action="#">
                <input type="submit" class="fast-button" value="+" />
                <!-- идентифицировать -->
            </form>
            <form action="#">
                <input type="submit" class="menu-button" value="Доходы" />
            </form>
            <form action="#">
                <input type="submit" class="fast-button" value="+" />
                <!-- идентифицировать -->
            </form>
            <form action="#">
                <input type="submit" class="menu-button" value="Статистика" />
            </form>
        </div>
        <div class="module">
		<div class="block-title">
			<%
				String headstring = "Просто селект";
				out.println(headstring);
			%>
		</div>
		<div class="block-information">
			<form action="modules.jsp" method="get">
				<input type="text" name="testquery" /> <input type="submit"
					value="UP" />
			</form>
			<%
				if ((request.getParameter("testquery") != null) && (!request.getParameter("testquery").trim().isEmpty())) {
			%>
			<%=request.getParameter("testquery")%>
			<%
				}
			%>
		</div>
	</div>

	<div class="module">
		<div class="block-title">Запрашивай</div>
		<div class="block-information">
			<%
				request.setAttribute("select", request.getParameter("testquery"));
			%>
			<form action="generateTable" method="get">
				<input type="hidden" name="select"
					value="<%=request.getParameter("testquery")%>" /> <input
					type="submit" value="UP" />
			</form>
			<%=request.getAttribute("table")%>
		</div>
	</div>
	<div class="module">
		<div class="block-title">Приоритеты</div>
		<div class="block-information">
			<form action="PriorityMod" method="get">
				<input type="hidden" name="user_id" value="1005" /> <input
					type="submit" value="UP" />
			</form>
		</div>
	</div>
    </body>
</html>

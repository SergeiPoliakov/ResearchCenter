<%@page import="com.netcracker.unc.priorityModule.ReceivingData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modules</title>
        <link href="css/main.css" rel="stylesheet" type="text/css"/>
        <script src="javascripts/jquery-2.2.0.min.js"></script>
        <script src ="javascripts/mainModules.js"></script>
    </head>
    <body>
        <%
                response.setContentType("text/html;charset=Windows-1251");
                request.setCharacterEncoding("UTF-8");
                response.setCharacterEncoding("UTF-8");
        %>
        <div class="header">
            <div id="logo" >214*59</div>
            <button id ="cost-menu-button" class="button">Расходы</button>
            <!--
            
            Now not used
            
            <button id ="fast-add-cost" class="button">+</button>
            <button id ="income-menu-button" class="button">Доходы</button>
            <button id ="fast-add-income" class="button">+</button>-->
            <button id ="statistic-menu-button" class="button">Статистика</button>
        </div>
        <div class="module"  id="select-module1">
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
        <div class="module" id="priority-module">
            <div class="block-title">Приоритеты</div>
            <div class="block-information">
                <form action="PriorityMod" method="get">
                    <input type="hidden" name="user_id" value="1005" /> <input
                        type="submit" value="UP" />
                </form>
                <% com.netcracker.unc.priorityModule.ReceivingData rd = new ReceivingData();%>
                <%= rd.toString() %>
            </div>
        </div>
        <div class="module" id="test1">
            <div class="block-title">Пример модуля 1</div>
            <div class="block-information">
                Как назначить кнопку в меню для модуля?
                Открываем mainModules.js каждой фукции соответствует кнопка меню.
                Смотрим, и делаем по аналогии. В пункте меню, при нажатии которого
                нужно открывать модуль прописываем параметр: block, например:
                $("#priority-module").css("display", "block");
                <b>Во всех</b> остальных пунктах меню прописываем скрывание модуля.
                Например:
                $("#priority-module").css("display", "none");
                
            </div>
        </div>
        <div class="module" id="test2">
            <div class="block-title">Тут очередной пример модуля</div>
            <div class="block-information">
                Тут какая-то информация
            </div>
        </div>
        <div class="module" id="test3">
            <div class="block-title">Третий пример модуля</div>
            <div class="block-information">
                Тут какая-то информация
            </div>
        </div>      
    </body>
</html>

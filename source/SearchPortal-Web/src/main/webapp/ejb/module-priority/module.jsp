<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Categories module</title>
<?xml version="1.0" encoding="UTF-8" ?>
</head>
<body>
	<jsp:include page="/PriorityCategoriesEjbServlet" />

	<%
		if (request.getSession().getAttribute("categoryNameMass") != null) {
			String categoryNameMass[] = (String[]) request.getSession().getAttribute("categoryNameMass");
			double categoryPriorityMass[] = (double[]) request.getSession().getAttribute("categoryPriorityMass");
			for (int i = 0; i < categoryNameMass.length; i++) {
	%>
	<%=categoryNameMass[i]%>
	<%=categoryPriorityMass[i]%>
	<%
		}
		}
	%>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="ejb/js/Chart.js"></script>
</head>
<body>
	<canvas id="skills" width="300" height="300"></canvas>
	<jsp:include page="/stat" />
	<div>${statisticModel.getSum() }</div>
	<script type="text/javascript">
	var data = [
	            {
	                value: 300,
	                color:"#F7464A",
	                highlight: "#FF5A5E",
	                label: "Red"
	            },
	            {
	                value: 50,
	                color: "#46BFBD",
	                highlight: "#5AD3D1",
	                label: "Green"
	            }
	        ]
    var pieOptions = {
            segmentShowStroke : false,
            animateScale : true
       }
	var context = document.getElementById('skills').getContext('2d');
	var skillsChart = new Chart(context).Pie(pieData);
	new Chart(skillsChart).Pie(data, pieOptions);
	</script>

</body>
</html>
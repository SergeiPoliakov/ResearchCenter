<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>

	<div style="margin-left: 10px; position: relative;" id="divProgress">
		<label class="percent" onmouseover="createOverlay()"
			onmouseout="deleteOverlay()">${percentString}</label>
		<progress value="${progress}" max="100" id="consumptionOverlay"
			class="inoutProgress" onmouseover="createOverlay()"
			onmouseout="deleteOverlay()"></progress>
	</div>

</body>
<script>
	var posX;
	var posY;
	var divProgress = document.getElementById("divProgress");

	document.onmousemove = function(e) {
		posX = e.pageX;
		posY = e.pageY;
	}

	function deleteOverlay() {
		var div = document.getElementById('prog');
		if (div != null) {
			divProgress.removeChild(div);
		}
	}

	function createOverlay() {

		var div = document.createElement('div');
		div.id = "prog";
		div.className = 'progressOverlay';

		var label = document.createElement('label');
		label.id = "progressOverlaylabel";
		label.innerHTML = '<b style=\"color:#006400\">На текущий месяц:</b> <p style=\"margin-top: 3px\"><b>Наибольший доход от:</b> '
				+ "${maxIncomeCost} "
				+ "(${maxIncomeCostInt} руб.)"
				+ '<p><b>Наибольший расход от:</b> '
				+ "${maxConsumptionCost} "
				+ "(${maxConsumptionCostInt} руб.)"
				+ "<p><b>Средний доход:</b> "
				+ "${avgIncome} руб."
				+ "<p><b>Средний расход:</b> " + "${avgConsumption} руб.";
		div.appendChild(label);
		div.style.marginLeft = (posX-1700) + 'px';
		divProgress.appendChild(div);
	}
</script>

</html>
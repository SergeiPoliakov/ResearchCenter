<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/attitudes/css/case_interview.css" />
<title>Insert title here</title>
</head>
<body>

	<form action="control">
		<input type="hidden" value="interviewCase" name="control">
		<div id="caseIntDiv">
			<label class="caseIntLabel"> У вас отслеживается задача: </label>
			<p />
			<label id="caseIntObjectName">${caseInterviewObject.getObjectName()}</label>
			<p />
			<label class="caseIntLabel">сумма к оплате: <span
				class="caseIntAte">${caseInterviewSalary}</span>
			</label>
			<p />
			<label class="caseIntLabel">от: <span class="caseIntAte">${caseInterviewDate}</span></label>
			<p />
			<label class="caseIntLabel">Вы оплатили сумму?</label> <input
				type="radio" id="interviewCaseRadioYes"
				onclick="changeRadioCaseInterview(this)" checked="checked"><label
				class="caseIntLabel">да</label> <input type="radio"
				id="interviewCaseRadioNo" onclick="changeRadioCaseInterview(this)"><label
				class="caseIntLabel">нет</label>
			<div style="display: none;" id="caseIntAdd">
				<label class="caseIntLabel">Оплачено:</label> <input type="text"
					name="caseIntText" id="caseIntText" value="0"
					onclick="this.value = ''" onblur="setZero(this)" />
			</div>
			<input id="interviewCaseButton" type="submit" value="подтвердить" />
		</div>
	</form>

</body>

<script type="text/javascript">
	// set zero to value
	function setZero(element) {
		if (element.value.valueOf() == "".valueOf())
			element.value = 0;
	}

	//change radio buttons in case interview
	function changeRadioCaseInterview(element) {
		var radio = element;
		var radioYes = document.getElementById("interviewCaseRadioYes");
		var radioNo = document.getElementById("interviewCaseRadioNo");
		var caseIntAdd = document.getElementById('caseIntAdd');
		var caseIntText = document.getElementById('caseIntText');
		if (radio.getAttribute("id").valueOf() == 'interviewCaseRadioYes'
				.valueOf()) {
			caseIntAdd.style.display = 'none';
			radioNo.checked = false;
			caseIntText.value = '${caseInterviewSalary}';
		} else {
			caseIntAdd.style.display = 'block';
			radioYes.checked = false;
			caseIntText.value = 0;
		}
	}
</script>

</html>
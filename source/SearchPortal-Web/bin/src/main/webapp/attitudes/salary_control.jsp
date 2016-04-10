<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet"
	href="attitudes/css/salary_control.css" />
<title>Insert title here</title>
</head>
<body>

	<c:if test="${not empty controlSalary }">
		<form action="custom">
			<input type="hidden" name="custom" value="controlSalary">
			<div id="divMounthCheck">
				<div class="controlSalaryBorder">
					<label id="controlSalaryLabel">Рады вас снова видеть на
						сайте!</label> <label class="controlSalaryLabel">Последняя
						зарплата была зафиксирована ${controlSalary.getDateCount()}
						месяц(ев) назад</label> <label class="controlSalaryLabel">Пожалуйста,
						обновите данные по зарплате:</label>
					<p />
					<input type="text" id="controlSalaryInput"
						name="controlSalaryInput" value="${controlSalary.getValue()}" /><label
						class="controlSalaryLabel">за последние
						${controlSalary.getDateCount()} месяц(ев)</label>
					<p />
					<input type="radio" id="controlSalaryRadioYes"
						onclick="changeRadioControlSalary(this)" checked="checked"><label
						class="controlSalaryLabel">да</label><input type="radio"
						id="controlSalaryRadioNo" onclick="changeRadioControlSalary(this)"><label
						class="controlSalaryLabel">нет</label> <input
						id="controlSalaryButton" type="submit" value="подтвердить" />

				</div>
				<div class="controlSalaryTableDiv" id="controlSalaryTableDiv">
					<table class="controlSalaryTable" rules="all">
						<c:forEach var="i" begin="1" end="${controlSalary.getDateCount()}">
							<tr align="center">
								<td class="controlSalaryTableLabel">${i}&nbsp;месяц:&nbsp;</td>
								<td id="controlSalaryInputTd"><input type="text"
									value="${controlSalary.getValue()}"
									id="controlSalaryInputTable" name="controlSalaryInputTable" /></td>
							</tr>
						</c:forEach>

					</table>
				</div>
			</div>
		</form>
	</c:if>



</body>
<script type="text/javascript">
	function changeRadioControlSalary(element) {
		radio = element;
		divMounths = document.getElementById("controlSalaryTableDiv");
		radioYes = document.getElementById("controlSalaryRadioYes");
		radioNo = document.getElementById("controlSalaryRadioNo");
		if (radio.getAttribute("id").valueOf() == 'controlSalaryRadioYes'
				.valueOf()) {
			radioNo.checked = false;
			divMounths.style.visibility = 'hidden';
		} else {
			divMounths.style.visibility = 'visible';
			radioYes.checked = false;
		}
	}
</script>


</html>
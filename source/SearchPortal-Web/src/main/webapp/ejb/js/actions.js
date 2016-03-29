/**
 * 
 */

// add regular check for input button of salary add
function regularAddSalary() {
	var welcomeCaseInput = document.getElementById("welcomeCaseInput");
	var matcherCostCase = new RegExp("^[0-9]{1,12}$");
	if (!welcomeCaseInput.value.match(matcherCostCase))
		return false;
}

// add animation for check-salary module
var helloCase = document.getElementById("helloCase");
var addSalaryPlace = -250;
helloCase.style.right = addSalaryPlace + 'px';
var animationAddCase = setInterval(function() {
	if (addSalaryPlace != 0) {
		addSalaryPlace += 2;
		helloCase.style.right = addSalaryPlace + 'px';
	} else {
		clearInterval(animationAddCase);
	}

}, 5);
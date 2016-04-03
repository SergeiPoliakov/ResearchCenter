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
if (document.getElementById("helloCase") != null) {
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
}

// animation for control salary
if (document.getElementById("controlSalaryMain") != null) {
	var controlSal = document.getElementById("controlSalaryMain");
	var addSalaryControlPlace = -25;
	controlSal.style.right = addSalaryControlPlace + '%';
	var animationControlSalary = setInterval(function() {
		if (addSalaryControlPlace < 0) {
			addSalaryControlPlace += 1;
			controlSal.style.right = addSalaryControlPlace + '%';
		} else {
			clearInterval(animationControlSalary);
		}
	}, 20);
}

// show-hide general modules
function showHideModule(element) {
	var consumptionDiv = document.getElementById('consumptionDiv');
	var personalAreaUserDiv = document.getElementById('personalAreaUserDiv');
	var button = element;
	switch (button.id.valueOf()) {
	case 'consumption'.valueOf():
		if (consumptionDiv.style.display.valueOf() == 'none'.valueOf()) {
			consumptionDiv.style.display = '';
		} else
			consumptionDiv.style.display = 'none';
		break;
	case 'personalArea'.valueOf():
		if (personalAreaUserDiv.style.display.valueOf() == 'none'.valueOf()) {
			personalAreaUserDiv.style.display = '';
		} else
			personalAreaUserDiv.style.display = 'none';
		break;

	}
}
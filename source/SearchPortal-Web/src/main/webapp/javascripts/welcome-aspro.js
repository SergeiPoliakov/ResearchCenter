/**
 * 
 */

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

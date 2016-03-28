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
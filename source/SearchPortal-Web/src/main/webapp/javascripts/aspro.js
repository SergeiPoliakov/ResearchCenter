/**
 * @author Kolesnikov
 */

// to save show edit user module
var matches = document.cookie.match(new RegExp('errorUpdateUser=1'));
var divEditUser = document.getElementById("editUser");
var errorPasswordLabel = document.getElementById("errorPasswordLabel");
if (matches) {
	errorPasswordLabel.innerHTML = 'старый пароль введен неверно';
	errorPasswordLabel.style.visibility = 'visible';
	divEditUser.style.visibility = 'visible';
	// for delete cookie
	document.cookie = 'errorUpdateUser'
			+ '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
} else {
	errorPasswordLabel.innerHTML = "";
	errorPasswordLabel.style.visibility = 'hidden';
	divEditUser.style.visibility = 'hidden';
}

// show-hide edit user module
function showHideUpdateUser() {
	var div = document.getElementById("editUser");
	var errorPasswordLabel = document.getElementById("errorPasswordLabel");
	var errorNameLabel = document.getElementById("errorNameLabel");
	if (div.style.visibility.valueOf() == 'hidden'.valueOf()) {
		div.style.visibility = 'visible';
	} else {
		errorPasswordLabel.style.visibility = 'hidden';
		errorNameLabel.style.visibility = 'hidden';
		div.style.visibility = 'hidden';
	}
}

// animation for case interview
if (document.getElementById("caseInterview") != null) {
	var caseInterviewDiv = document.getElementById("caseInterview");
	var addSalaryControlPlace = -25;
	caseInterviewDiv.style.right = addSalaryControlPlace + '%';
	var animationCaseInterview = setInterval(function() {
		if (addSalaryControlPlace < 0) {
			addSalaryControlPlace += 1;
			caseInterviewDiv.style.right = addSalaryControlPlace + '%';
		} else {
			clearInterval(animationCaseInterview);
		}
	}, 20);
}

// animation for control salary
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

// animation for create case
var cookie = document.cookie;
var matches = document.cookie.match(new RegExp('caseAdd=1'));
if (matches) {
	var divCreateCase = document.getElementById('animationAddCase');
	var div = document.createElement('div');
	var label = document.createElement('label');
	divCreateCase.style.visibility = 'visible';
	div.id = 'animationCreateCase';
	label.innerHTML = 'задача создана';
	label.id = 'animationCCLabel';
	div.style.background = 'green';
	div.style.opacity = 0.0;
	div.appendChild(label);
	divCreateCase.appendChild(div);
	var pause = 0;
	var show = setInterval(function() {
		var op = parseFloat(div.style.opacity);
		if (op != 1.0) {
			div.style.opacity = op + 0.1;
		} else {
			clearInterval(show);
			show = setInterval(function() {
				var op = parseFloat(div.style.opacity);
				if (pause > 20) {
					if (op != 0.0) {
						div.style.opacity = op - 0.1;
					} else {
						clearInterval(show);
						divCreateCase.remove();
					}
				} else
					pause++;
			}, 50);
		}
	}, 50);
	// for delete cookie
	document.cookie = 'caseAdd' + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}

// use letters limitation as only numbers input
function validate(input) {
	var input1 = input;
	var element = window.event; // get screen page event
	var key = element.which;
	key = String.fromCharCode(key);
	var regex = /[0-9]/; // regular
	if (!regex.test(key) || input1.value.length > 9) {
		if (element.preventDefault) { // cancel change by event screen
			element.preventDefault();
		} else {
			element.returnValue = false; // //cancel change by event screen
			// for ie8
		}
	}
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

// visible and hidden create case border
function showCreateCase() {
	var parent = document.getElementById("parentBlock");
	var no = document.getElementById('no');
	var typeYes = document.getElementById("yes");
	var typeNo = document.getElementById("no");
	var createCase = document.getElementById("create-case");

	if (createCase.style.visibility.valueOf() == 'hidden'.valueOf()) {
		createCase.style.visibility = 'visible';
		changeCheck(no);
	}

	else {
		createCase.style.visibility = 'hidden';
		parent.style.visibility = "hidden";
		typeYes.checked = undefined;
		typeNo.checked = "checked";
	}
}

// hide-show update cases
function showActiveCases() {
	var activeCases = document.getElementById('updateCase');
	if (activeCases.style.visibility.valueOf() == 'hidden'.valueOf()) {
		activeCases.style.visibility = 'visible';
	} else {
		activeCases.style.visibility = 'hidden';
		refreshActiveCases();
		var helpInput = document.getElementById('inputUpdateCase');
		helpInput.value = 'Быстрый поиск';
		helpInput.style.color = 'grey';
	}

}

function showIncoming() {
	var activeCases = document.getElementById('incoming');
	if (activeCases.style.visibility.valueOf() == 'hidden'.valueOf()) {
		activeCases.style.visibility = 'visible';
	} else {
		activeCases.style.visibility = 'hidden';
	}
}
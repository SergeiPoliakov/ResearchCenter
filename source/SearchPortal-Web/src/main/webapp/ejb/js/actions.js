/**
 * 
 */

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

// to change background when user is bankrupt
if (document.getElementById('bankrupt') != null) {
	var footer = document.querySelector('.container-footer');
	var header1 = document.querySelector('.navbar-collapse');
	var header2 = document.querySelector('.navbar-header');
	var header3 = document.querySelector('#Insert_logo');
	header1.style.backgroundColor = 'red';
	header2.style.backgroundColor = 'red';
	header3.style.backgroundColor = 'red';
	footer.style.backgroundColor = 'red';
}

// action for red big button
function resetBalance() {
	var resetDiv = document.getElementById('resetBalanceDiv');

	if (resetDiv.style.display.valueOf() == 'none'.valueOf()) {
		resetDiv.style.display = '';
	} else
		resetDiv.style.display = 'none';
}

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
	var leftMenuButtons = document.getElementsByClassName('leftMenuButton');
	var personalAreaUserDiv = document.getElementById('personalAreaUserDiv');
	var button = element;
	switch (button.id.valueOf()) {
	case 'consumption'.valueOf():
		document.getElementById('createCase').style.display = '';
		personalAreaUserDiv.style.display = 'none';
		showLeftMenuDiv();
		break;
	case 'personalArea'.valueOf():
		if (personalAreaUserDiv.style.display.valueOf() == 'none'.valueOf()) {
			personalAreaUserDiv.style.display = '';
			document.getElementById('createCase').style.display = 'none';
			document.getElementById('updateCase').style.display = 'none';
			document.getElementById('leftMenuDiv').style.marginLeft = '-10px';
			document.getElementById('leftMenuDiv').style.display = 'none';
		} else
			personalAreaUserDiv.style.display = 'none';
		break;
	case 'incomePlus'.valueOf():
		if (document.getElementById('incoming').style.display.valueOf() == 'none'
				.valueOf()) {
			document.getElementById('incoming').style.display = '';
			leftMenuButtons[0].setAttribute("onclick",
					'showIncomePlusDivs(this)');
			document.getElementById('createCase').style.display = 'none';
			document.getElementById('updateCase').style.display = 'none';
			document.getElementById('incomingMinus').style.display = 'none';
		}
		break;
		
	case 'incomeMinus'.valueOf():
		if (document.getElementById('incomingMinus').style.display.valueOf() == 'none'
				.valueOf()) {
			document.getElementById('incomingMinus').style.display = '';
			leftMenuButtons[0].setAttribute("onclick",
					'showIncomeMinusDivs(this)');
			document.getElementById('createCase').style.display = 'none';
			document.getElementById('updateCase').style.display = 'none';
			document.getElementById('incoming').style.display = 'none';
		}
		break;
	}
}

function showIncomePlusDivs(element) {
	switch (element.innerHTML.valueOf()) {
	case '1'.valueOf():
		document.getElementById('incoming').style.display = '';
	}
}

function showIncomeMinusDivs(element) {
	switch (element.innerHTML.valueOf()) {
	case '1'.valueOf():
		document.getElementById('incomingMinus').style.display = '';
	}
}

// animation for left menu buttons
function showLeftMenuDiv() {
	var leftMenuDiv = document.getElementById('leftMenuDiv');
	leftMenuDiv.style.display = '';
	var posLeftMenu = -10;
	var animationLeftMenu = setInterval(function() {
		if (posLeftMenu < 10) {
			posLeftMenu += 1;
			leftMenuDiv.style.marginLeft = posLeftMenu + 'px';
		} else {
			clearInterval(animationLeftMenu);
		}
	}, 50);
}

// left menu button choose
function leftMenuChoose(element) {
	switch (element.innerHTML.valueOf()) {
	case '1'.valueOf():
		document.getElementById('createCase').style.display = '';
		document.getElementById('editCase').style.display = 'none';
		break;
	case '2'.valueOf():
		document.getElementById('editCase').style.display = '';
		document.getElementById('createCase').style.display = 'none';
		break;
	}
}